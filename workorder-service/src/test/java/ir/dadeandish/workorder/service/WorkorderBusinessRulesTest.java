package ir.dadeandish.workorder.service;

import ir.dadeandish.application.EmployeeService;
import ir.dadeandish.application.WorkOrderService;
import ir.dadeandish.application.WorkorderAssignmentProcessor;
import ir.dadeandish.client.EquipmentClient;
import ir.dadeandish.domain.WorkOrderModel;
import ir.dadeandish.domain.WorkOrderRepository;
import ir.dadeandish.dto.EmployeeDto;
import ir.dadeandish.dto.EquipmentDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.Optional;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WorkorderBusinessRulesTest {

    @Mock
    EmployeeService employeeService;

    @Mock
    WorkOrderRepository workOrderRepository;

    @Mock
    EquipmentClient equipmentClient;

    @InjectMocks
    WorkOrderService service;

    @Mock
    WorkorderAssignmentProcessor processor;

    @Test
    void assignWorkOrderToEmployee_shouldCallProcessor() throws Exception {

        EmployeeDto employee = new EmployeeDto();
        employee.setId(2);

        WorkOrderModel workOrder = new WorkOrderModel();
        workOrder.setId(10);
        workOrder.setEquipmentId(5);

        EquipmentDTO equipment = new EquipmentDTO();
        equipment.setId(5);

        when(employeeService.getEmployeeById(2))
                .thenReturn(employee);

        when(workOrderRepository.findById(10))
                .thenReturn(Optional.of(workOrder));

        when(equipmentClient.getEquipment(5))
                .thenReturn(equipment);

        service.assignWorkOrderToEmployee(10, 2);

        verify(processor)
                .assign(employee, equipment, workOrder);
    }
}