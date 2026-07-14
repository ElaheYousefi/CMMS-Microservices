package ir.dadeandish.domain;

import jakarta.persistence.*;
import org.springframework.lang.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AssignTask {

    @Id
    @GeneratedValue
    private int id;

    private int equipId;

    private int definedTaskId;

    private LocalDateTime nextExecutionDate;

    @Nullable
    private Integer active;

    public LocalDateTime getNextExecutionDate() {
        return nextExecutionDate;
    }

    public void setNextExecutionDate(LocalDateTime nextExecutionDate) {
        this.nextExecutionDate = nextExecutionDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
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
}
