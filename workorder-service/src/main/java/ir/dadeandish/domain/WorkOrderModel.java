package ir.dadeandish.domain;

import ir.dadeandish.dto.EquipmentStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="workorder")
public class WorkOrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int assignTaskId;

    @Column(nullable=true)
    private Integer employeeId;
    private LocalDate dueDate;

    @Column(nullable=true)
    private String observResult;

    @Column(nullable = true)
    private WorkOrderStatus workOrderStatus;

    @Column(nullable = true)
    private LocalDate doneDate;

    @Column(nullable = true)
    private String priority;

    @Column(nullable = true)
    private String employeeName;

    @Column(nullable = true)
    private EquipmentStatus equipmentStatus;

    @Column(nullable = true)
    private String equipmentName;

    @Column(nullable = true)
    private Integer equipmentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAssignTaskId() {
        return assignTaskId;
    }

    public void setAssignTaskId(int assignTaskId) {
        this.assignTaskId = assignTaskId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getObservResult() {
        return observResult;
    }

    public void setObservResult(String observResult) {
        this.observResult = observResult;
    }

    public WorkOrderStatus getWorkOrderStatus() {
        return workOrderStatus;
    }

    public void setWorkOrderStatus(WorkOrderStatus workOrderStatus) {
        this.workOrderStatus = workOrderStatus;
    }

    public LocalDate getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(LocalDate doneDate) {
        this.doneDate = doneDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public EquipmentStatus getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(EquipmentStatus equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }
}
