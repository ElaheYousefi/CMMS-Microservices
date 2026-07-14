package ir.dadeandish.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AssignTaskDTO {

    private int id;
    private int equipId;
    private int definedTaskId;
    private LocalDateTime nextExecutionDate;
    private Integer active;
    private int periodDay;

    public AssignTaskDTO(int id, int equipId, int periodDay) {
        this.id = id;
        this.equipId = equipId;
        this.periodDay= periodDay;
    }

    public AssignTaskDTO() {
    }

    public int getPeriodDay() {
        return periodDay;
    }

    public void setPeriodDay(int periodDay) {
        this.periodDay = periodDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEquipId() {
        return equipId;
    }

    public void setEquipId(int equipId) {
        this.equipId = equipId;
    }

    public int getDefinedTaskId() {
        return definedTaskId;
    }

    public void setDefinedTaskId(int definedTaskId) {
        this.definedTaskId = definedTaskId;
    }

    public LocalDateTime getNextExecutionDate() {
        return nextExecutionDate;
    }

    public void setNextExecutionDate(LocalDateTime nextExecutionDate) {
        this.nextExecutionDate = nextExecutionDate;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}
