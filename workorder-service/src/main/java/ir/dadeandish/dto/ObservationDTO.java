package ir.dadeandish.dto;
import ir.dadeandish.domain.WorkOrderStatus;

public class ObservationDTO {

    private int workOrderId;
    private String observResult;
    private WorkOrderStatus workOrderStatus;
    private EquipmentStatus equipStatus;
//    doneDate;


    public ObservationDTO() {
    }

    public ObservationDTO(int workOrderId, String observResult, WorkOrderStatus workOrderStatus, EquipmentStatus equipStatus) {
        this.workOrderId = workOrderId;
        this.observResult = observResult;
        this.workOrderStatus = workOrderStatus;
        this.equipStatus = equipStatus;
    }

    public int getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(int workOrderId) {
        this.workOrderId = workOrderId;
    }

    public String getObservResult() {
        return observResult;
    }

    public void setObservResult(String observResult) {
        this.observResult = observResult;
    }

    public WorkOrderStatus getWorkOrderStatus() {
        return workOrderStatus;
    }

    public void setWorkOrderStatus(WorkOrderStatus workOrderStatus) {
        this.workOrderStatus = workOrderStatus;
    }

    public EquipmentStatus getEquipStatus() {
        return equipStatus;
    }

    public void setEquipStatus(EquipmentStatus equipStatus) {
        this.equipStatus = equipStatus;
    }
}
