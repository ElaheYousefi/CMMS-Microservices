package ir.dadeandish.event;

public class WorkOrderCreatedEvent {

    private Integer assignTaskId;
    private Integer workOrderId;
    private Integer employeeId;
    private String mobile;
    private String email;
    private String employeeName;
    private String equipmentName;

    public WorkOrderCreatedEvent(Integer assignTaskId, Integer workOrderId, Integer employeeId, String mobile, String email, String employeeName, String equipmentName) {
        this.assignTaskId = assignTaskId;
        this.workOrderId = workOrderId;
        this.employeeId = employeeId;
        this.mobile = mobile;
        this.email = email;
        this.employeeName= employeeName;
        this.equipmentName= equipmentName;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAssignTaskId() {
        return assignTaskId;
    }

    public void setAssignTaskId(Integer assignTaskId) {
        this.assignTaskId = assignTaskId;
    }

    public Integer getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(Integer workOrderId) {
        this.workOrderId = workOrderId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}