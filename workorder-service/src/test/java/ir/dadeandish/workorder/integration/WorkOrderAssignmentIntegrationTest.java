package ir.dadeandish.workorder.integration;
import ir.dadeandish.application.WorkorderAssignmentProcessor;
import ir.dadeandish.domain.OutboxEvent;
import ir.dadeandish.domain.OutboxRepository;
import ir.dadeandish.domain.WorkOrderModel;
import ir.dadeandish.domain.WorkOrderRepository;
import ir.dadeandish.dto.EmployeeDto;
import ir.dadeandish.dto.EquipmentDTO;
import ir.dadeandish.enums.EventType;
import ir.dadeandish.enums.OutboxStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class WorkorderAssignmentProcessorIT {

    @Autowired
    private WorkorderAssignmentProcessor processor;

    @Autowired
    private WorkOrderRepository workOrderRepository;

    @Autowired
    private OutboxRepository outboxRepository;

    @Test
    void assign_shouldCommitBothWorkOrderAndOutbox() throws Exception {

        // ---------- Arrange ----------

        EmployeeDto employee = new EmployeeDto();
        employee.setId(5);
        employee.setName("John");
        employee.setEmail("john@test.com");
        employee.setMobile("123");

        EquipmentDTO equipment = new EquipmentDTO();
        equipment.setName("Pump");

        WorkOrderModel workOrder = new WorkOrderModel();
        workOrder.setAssignTaskId(3);
        workOrder.setEquipmentId(5);

        long workOrdersBefore = workOrderRepository.count();
        long outboxBefore = outboxRepository.count();

        // ---------- Act ----------

        processor.assign(employee, equipment, workOrder);

        // ---------- Assert ----------

        assertEquals(workOrdersBefore + 1,
                workOrderRepository.count());

        assertEquals(outboxBefore + 1,
                outboxRepository.count());

        // Verify saved WorkOrder

        WorkOrderModel savedWorkOrder =
                workOrderRepository.findById(workOrder.getId())
                        .orElseThrow();

        assertEquals(employee.getId(),
                savedWorkOrder.getEmployeeId());

        assertEquals(3,
                savedWorkOrder.getAssignTaskId());

        assertEquals(5,
                savedWorkOrder.getEquipmentId());

        // Verify Outbox

        OutboxEvent outbox =
                outboxRepository.findAll()
                        .stream()
                        .max(Comparator.comparing(OutboxEvent::getId))
                        .orElseThrow();

        assertEquals(EventType.WORKORDER_ASSIGNED,
                outbox.getEventType());

        assertEquals(OutboxStatus.PENDING,
                outbox.getStatus());

        assertNotNull(outbox.getPayload());

        assertTrue(
                outbox.getPayload().contains("\"employeeId\":5")
        );
    }



    @Test
    void assign_shouldRollbackWhenExceptionOccurs() {

        EmployeeDto employee = new EmployeeDto();
        employee.setId(5);
        employee.setName("John");

        EquipmentDTO equipment = new EquipmentDTO();
        equipment.setName("Pump");

        WorkOrderModel workOrder = new WorkOrderModel();
        workOrder.setAssignTaskId(3);
        workOrder.setEquipmentId(5);

        long workOrdersBefore = workOrderRepository.count();
        long outboxBefore = outboxRepository.count();

        assertThrows(RuntimeException.class,
                () -> processor.assignAndFail(
                        employee,
                        equipment,
                        workOrder));

        assertEquals(workOrdersBefore,
                workOrderRepository.count());

        assertEquals(outboxBefore,
                outboxRepository.count());
    }
}