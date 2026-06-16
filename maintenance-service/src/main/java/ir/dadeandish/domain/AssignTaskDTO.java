package ir.dadeandish.domain;

import org.springframework.lang.Nullable;

import java.time.LocalDate;

public class AssignTaskDTO {

    private int id;
    private int equipId;
    private int definedTaskId;
    private LocalDate nextExecutionDate;

    @Nullable
    private Integer active;

    public AssignTaskDTO(int id, int equipId) {
        this.id = id;
        this.equipId = equipId;
    }

    public AssignTaskDTO() {
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

    public LocalDate getNextExecutionDate() {
        return nextExecutionDate;
    }

    public void setNextExecutionDate(LocalDate nextExecutionDate) {
        this.nextExecutionDate = nextExecutionDate;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

}
