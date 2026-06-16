package ir.dadeandish.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class DefinedTask {

    @Id
    @GeneratedValue
    private int id;
    private String taskName;
    private LocalDate endDate;
    private int periodDay;
    private LocalDate startDate;

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
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

    public LocalDate getEndDate() {
        return endDate;
    }
}
