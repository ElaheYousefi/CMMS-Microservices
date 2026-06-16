package ir.dadeandish.domain;

import java.time.LocalDate;

public class DefineTaskDto {

    private int id;
    private String taskName;
    private LocalDate endDate;
    private int periodDay;
    private LocalDate startDate;

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

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getPeriodDay() {
        return periodDay;
    }

    public void setPeriodDay(int periodDay) {
        this.periodDay = periodDay;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
