package ir.dadeandish.event;

public class WorkOrderCompletedEvent {

    private int equipmentId;
    private Enum equipmentStatus;

    public WorkOrderCompletedEvent(int equipmentId, Enum equipmentStatus) {
        this.equipmentId = equipmentId;
        this.equipmentStatus = equipmentStatus;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Enum getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(Enum equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }
}
