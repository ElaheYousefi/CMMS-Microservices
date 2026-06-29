package ir.dadeandish.event;

public class WorkOrderCreatedEvent {

    private Long assignTaskId;

    public WorkOrderCreatedEvent(Long assignTaskId) {
        this.assignTaskId = assignTaskId;
    }

    public Long getAssignTaskId() {
        return assignTaskId;
    }

    public void setAssignTaskId(Long assignTaskId) {
        this.assignTaskId = assignTaskId;
    }
}