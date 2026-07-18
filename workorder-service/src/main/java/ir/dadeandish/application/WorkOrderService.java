package ir.dadeandish.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.dadeandish.client.EquipmentClient;
import ir.dadeandish.domain.*;
import ir.dadeandish.dto.EmployeeDto;
import ir.dadeandish.dto.EquipmentDTO;
import ir.dadeandish.dto.EquipmentStatus;
import ir.dadeandish.dto.WorkorderDTO;
import ir.dadeandish.enums.EventType;
import ir.dadeandish.enums.OutboxStatus;
import ir.dadeandish.event.MaintenanceCompletedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Transactional
public class WorkOrderService{

    private WorkOrderRepository workOrderRepository;
    private static final Logger logger= LoggerFactory.getLogger(WorkOrderService.class);
    private final Mapper mapper;
    private final EmployeeService employeeService;
    private  final ObjectMapper objectMapper;
    private final OutboxRepository outboxRepository;
    private final EquipmentClient equipmentClient;
    private final WorkorderAssignmentProcessor workorderAssignmentProcessor;

    @Autowired
    public WorkOrderService(WorkOrderRepository workOrderRepository,
                            Mapper mapper, EmployeeService employeeService, ObjectMapper objectMapper, OutboxRepository outboxRepository,
                            EquipmentClient equipmentClient, WorkorderAssignmentProcessor workorderAssignmentProcessor) {
        this.workOrderRepository= workOrderRepository;
        this.mapper= mapper;
        this.employeeService= employeeService;
        this.objectMapper= objectMapper;
        this.outboxRepository= outboxRepository;
        this.equipmentClient= equipmentClient;
        this.workorderAssignmentProcessor= workorderAssignmentProcessor;
    }
    
    public WorkOrderModel addWorkOrder(int taskId, int equipId){
        WorkOrderModel wo= new WorkOrderModel();
        logger.info("Received event : task={}, equipment={}",
                taskId,
                equipId);

        wo.setAssignTaskId(taskId);
        wo.setEquipmentId(equipId);
        wo.setDueDate(LocalDate.now().plusDays(1));
        wo.setWorkOrderStatus(WorkOrderStatus.New);
        return workOrderRepository.save(wo);
    }

    public void assignWorkOrderToEmployee(int workOrderId, int employeeId) throws JsonProcessingException {
        logger.debug("employeeId in service="+ employeeId);
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        logger.debug("employeeI after get from db="+ employeeDto.getId());
        WorkOrderModel workOrderModel = workOrderRepository.findById(workOrderId)
                .orElseThrow(() -> new RuntimeException("work order with this id doesn't exist"));
        EquipmentDTO equipmentDTO = equipmentClient.getEquipment(workOrderModel.getEquipmentId());
        workorderAssignmentProcessor.assign(employeeDto, equipmentDTO, workOrderModel);
    }

    @Transactional
    public void addObservation(
            int workOrderId, String observResult, EquipmentStatus equipmentStatus, String employeeName) throws JsonProcessingException {

        WorkOrderModel workOrder =
                workOrderRepository.findById(workOrderId)
                        .orElseThrow(() ->
                                new RuntimeException("Work order not found"));

        // business validation
        if (workOrder.getWorkOrderStatus() == WorkOrderStatus.Done) {
            throw new RuntimeException(
                    "Observation already completed");
        }
        workOrder.setWorkOrderStatus(WorkOrderStatus.Done);
        workOrder.setDoneDate(LocalDate.now());
        workOrder.setObservResult(observResult);
        workOrder.setEquipmentStatus(equipmentStatus);
        workOrder.setEmployeeName(employeeName);
        workOrderRepository.save(workOrder);
        MaintenanceCompletedEvent completedEvent= new MaintenanceCompletedEvent(workOrder.getEquipmentId(), workOrder.getEquipmentStatus());
        String payload= objectMapper.writeValueAsString(completedEvent);
        OutboxEvent outboxEvent= new OutboxEvent(LocalDateTime.now(), EventType.WORKORDER_COMPLETED, OutboxStatus.PENDING, payload);
        outboxRepository.save(outboxEvent);
    }

    public WorkorderDTO getWorkOrderByID(int workOrderId){
        WorkOrderModel workOrderModel= workOrderRepository.findById(workOrderId)
                .orElseThrow(() -> new RuntimeException("this workorder doesn't exist"));
        return mapper.convertWorkOrderToDTO(workOrderModel);
    }
}
