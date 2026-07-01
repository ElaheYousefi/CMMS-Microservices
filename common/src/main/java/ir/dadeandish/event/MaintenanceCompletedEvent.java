package ir.dadeandish.event;

import ir.dadeandish.dto.EquipmentStatus;

public class MaintenanceCompletedEvent {

    private int equipmentId;
    private EquipmentStatus equipmentStatus;

    public MaintenanceCompletedEvent(int equipmentId, EquipmentStatus equipmentStatus) {
        this.equipmentId = equipmentId;
        this.equipmentStatus = equipmentStatus;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public EquipmentStatus getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(EquipmentStatus equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }
}
