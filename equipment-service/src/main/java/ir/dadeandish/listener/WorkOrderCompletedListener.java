package ir.dadeandish.listener;

import ir.dadeandish.application.EquipService;
import ir.dadeandish.domain.EquipModel;
import ir.dadeandish.event.WorkOrderCompletedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class WorkOrderCompletedListener {

    private final EquipService equipService;

    @Autowired
    public WorkOrderCompletedListener(EquipService equipService) {
        this.equipService = equipService;
    }

    @KafkaListener(
            topics = "workorder-completed-topic",
            groupId = "equipment-service")
    public void consume(
            WorkOrderCompletedEvent event) {

        equipService.updateStatus(
                event.getEquipmentId(),
                event.getEquipmentStatus());
    }
}
