package ir.dadeandish.event;

import java.util.List;

public class ReadyAssignTasksEvent{
    private int taskId;
    private int equipId;

    public ReadyAssignTasksEvent(int taskId, int equipId) {
        this.taskId = taskId;
        this.equipId = equipId;
    }

    public ReadyAssignTasksEvent() {
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getEquipId() {
        return equipId;
    }

    public void setEquipId(int equipId) {
        this.equipId = equipId;
    }
}
