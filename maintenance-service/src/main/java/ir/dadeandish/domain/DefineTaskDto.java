package ir.dadeandish.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DefineTaskDto {

    private int id;
    private String taskName;
    private LocalDateTime endDate;
    private int periodDay;
    private LocalDateTime startDate;

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

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public int getPeriodDay() {
        return periodDay;
    }

    public void setPeriodDay(int periodDay) {
        this.periodDay = periodDay;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }
}
