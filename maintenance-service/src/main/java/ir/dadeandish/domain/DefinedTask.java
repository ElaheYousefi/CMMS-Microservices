package ir.dadeandish.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class DefinedTask {

    @Id
    @GeneratedValue
    private int id;
    private String taskName;
    private LocalDateTime endDate;
    private int periodDay;
    private LocalDateTime startDate;

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getPeriodDay() {
        return periodDay;
    }

    public void setPeriodDay(int periodDay) {
        this.periodDay = periodDay;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
