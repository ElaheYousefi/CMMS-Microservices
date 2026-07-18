package ir.dadeandish.listener;

import ir.dadeandish.application.EquipService;
import ir.dadeandish.event.MaintenanceCompletedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WorkOrderCompletedListener {

    private final EquipService equipService;

    @Autowired
    public WorkOrderCompletedListener(EquipService equipService) {
        this.equipService = equipService;
    }

    @KafkaListener(
            topics = "maintenance-completed-topic",
            groupId = "equipment-group")
    public void consume(
            MaintenanceCompletedEvent event) {

        equipService.updateStatus(
                event.getEquipmentId(),
                event.getEquipmentStatus());
    }
}
