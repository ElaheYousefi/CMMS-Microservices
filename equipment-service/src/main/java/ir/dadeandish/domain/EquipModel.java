package ir.dadeandish.domain;

import ir.dadeandish.dto.EquipmentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="equipment")
public class EquipModel {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private EquipmentStatus equipmentStatus;

    public EquipmentStatus getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(EquipmentStatus equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    public EquipModel(int id, EquipmentStatus equipmentStatus) {
        this.id = id;
        this.equipmentStatus = equipmentStatus;
    }

    public EquipModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
