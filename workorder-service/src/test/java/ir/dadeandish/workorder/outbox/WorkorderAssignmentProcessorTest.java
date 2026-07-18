//package ir.dadeandish.workorder.outbox;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import ir.dadeandish.application.WorkorderAssignmentProcessor;
//import ir.dadeandish.domain.OutboxEvent;
//import ir.dadeandish.domain.OutboxRepository;
//import ir.dadeandish.domain.WorkOrderModel;
//import ir.dadeandish.domain.WorkOrderRepository;
//import ir.dadeandish.dto.EmployeeDto;
//import ir.dadeandish.dto.EquipmentDTO;
//import ir.dadeandish.enums.EventType;
//import ir.dadeandish.enums.OutboxStatus;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class WorkorderAssignmentProcessorTest {
//
//    @Mock
//    private WorkOrderRepository workOrderRepository;
//
//    @Mock
//    private OutboxRepository outboxRepository;
//
//    @Mock
//    private ObjectMapper objectMapper;
//
//    @InjectMocks
//    private WorkorderAssignmentProcessor processor;
//
//    @Test
//    @DisplayName("Should assign employee and create a pending outbox event")
//    void shouldAssignEmployeeAndCreatePendingOutboxEvent() throws Exception {
//
//        // ---------- Arrange ----------
//
//        WorkOrderModel workOrder = new WorkOrderModel();
//        workOrder.setId(100);
//        workOrder.setAssignTaskId(200);
//        workOrder.setEquipmentId(5);
//
//        EmployeeDto employee = new EmployeeDto();
//        employee.setId(10);
//        employee.setName("Ali");
//        employee.setEmail("ali@test.com");
//        employee.setMobile("09121234567");
//
//        EquipmentDTO equipment = new EquipmentDTO(5, "Pump");
//
//        when(objectMapper.writeValueAsString(any()))
//                .thenReturn("{\"event\":\"payload\"}");
//
//        // ---------- Act ----------
//
//        processor.assign(employee, equipment, workOrder);
//
//        // ---------- Assert WorkOrder ----------
//
//        ArgumentCaptor<WorkOrderModel> workOrderCaptor =
//                ArgumentCaptor.forClass(WorkOrderModel.class);
//
//        verify(workOrderRepository).save(workOrderCaptor.capture());
//
//        WorkOrderModel savedWorkOrder = workOrderCaptor.getValue();
//
//        assertEquals(employee.getId(), savedWorkOrder.getEmployeeId());
//        assertEquals(5, savedWorkOrder.getEquipmentId());
//
//        // ---------- Assert Outbox ----------
//
//        ArgumentCaptor<OutboxEvent> outboxCaptor =
//                ArgumentCaptor.forClass(OutboxEvent.class);
//
//        verify(outboxRepository).save(outboxCaptor.capture());
//
//        OutboxEvent outboxEvent = outboxCaptor.getValue();
//
//        assertEquals(EventType.WORKORDER_ASSIGNED,
//                outboxEvent.getEventType());
//
//        assertEquals(OutboxStatus.PENDING,
//                outboxEvent.getStatus());
//
//        assertEquals("{\"event\":\"payload\"}",
//                outboxEvent.getPayload());
//
//        assertNotNull(outboxEvent.getCreatedAt());
//
//        // Verify serialization happened exactly once
//        verify(objectMapper, times(1))
//                .writeValueAsString(any());
//
//        verifyNoMoreInteractions(workOrderRepository, outboxRepository);
//    }
//
////    @Test
////    @DisplayName("Should not create outbox event when payload serialization fails")
////    void shouldRollbackWhenSerializationFails() throws Exception {
////
////        // ---------- Arrange ----------
////
////        WorkOrderModel workOrder = new WorkOrderModel();
////        workOrder.setId(100);
////        workOrder.setAssignTaskId(200);
////        workOrder.setEquipmentId(5);
////
////        EmployeeDto employee = new EmployeeDto();
////        employee.setId(10);
////        employee.setName("Ali");
////        employee.setEmail("ali@test.com");
////        employee.setMobile("09121234567");
////
////        EquipmentDTO equipment = new EquipmentDTO(5, "Pump");
////
////        when(objectMapper.writeValueAsString(any()))
////                .thenThrow(new JsonProcessingException("Serialization failed") {});
////
////        // ---------- Act & Assert ----------
////
////        assertThrows(JsonProcessingException.class,
////                () -> processor.assign(employee, equipment, workOrder));
////
////        verify(workOrderRepository).save(any(WorkOrderModel.class));
////
////        verify(outboxRepository, never())
////                .save(any(OutboxEvent.class));
////    }
//}