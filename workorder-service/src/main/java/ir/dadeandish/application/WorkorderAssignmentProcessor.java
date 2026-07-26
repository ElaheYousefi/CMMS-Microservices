package ir.dadeandish.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.dadeandish.domain.*;
import ir.dadeandish.dto.EmployeeDto;
import ir.dadeandish.dto.EquipmentDTO;
import ir.dadeandish.enums.EventType;
import ir.dadeandish.enums.OutboxStatus;
import ir.dadeandish.event.WorkOrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class WorkorderAssignmentProcessor {

    private WorkOrderRepository workOrderRepository;
    private  final ObjectMapper objectMapper;
    private final OutboxRepository outboxRepository;
    private static final Logger logger= LoggerFactory.getLogger(WorkOrderService.class);

    public WorkorderAssignmentProcessor(WorkOrderRepository workOrderRepository, ObjectMapper objectMapper, OutboxRepository outboxRepository) {
        this.workOrderRepository = workOrderRepository;
        this.objectMapper = objectMapper;
        this.outboxRepository = outboxRepository;
    }

    @Transactional
    public void assign(
            EmployeeDto employeeDto,
            EquipmentDTO equipmentDTO,
            WorkOrderModel workOrderModel)
            throws JsonProcessingException {

        workOrderModel.setEmployeeId(employeeDto.getId());
        workOrderModel.setEquipmentId(workOrderModel.getEquipmentId());

        workOrderModel= workOrderRepository.save(workOrderModel);
        WorkOrderCreatedEvent workOrderCreatedEvent= new WorkOrderCreatedEvent(workOrderModel.getAssignTaskId(),
                workOrderModel.getId(), employeeDto.getId(), employeeDto.getMobile(), employeeDto.getEmail(), employeeDto.getName(), equipmentDTO.getName());
        String payload = objectMapper.writeValueAsString(workOrderCreatedEvent);

        OutboxEvent outboxEvent = new OutboxEvent(LocalDateTime.now(), EventType.WORKORDER_ASSIGNED, OutboxStatus.PENDING, payload);
        outboxRepository.save(outboxEvent);
    }

    @Transactional
    public void assignAndFail(
            EmployeeDto employee,
            EquipmentDTO equipment,
            WorkOrderModel workOrder)
            throws Exception {

        assign(employee, equipment, workOrder);

        throw new RuntimeException("Rollback Test");
    }
}