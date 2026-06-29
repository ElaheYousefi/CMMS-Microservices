package ir.dadeandish.listener;

import ir.dadeandish.application.WorkOrderService;
import ir.dadeandish.event.ReadyAssignTasksEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ReadyTaskListener {

    WorkOrderService workOrderService;

    public ReadyTaskListener(WorkOrderService workOrderService) {
        this.workOrderService = workOrderService;
    }

    @KafkaListener(topics="ready-task-topic",
            groupId="workorder-group")
    public void consume(ReadyAssignTasksEvent event){
        workOrderService.addWorkOrder(event.getTaskId(),
                event.getEquipId());
    }
}
