package ir.dadeandish;

import ir.dadeandish.domain.EquipModel;
import ir.dadeandish.dto.EquipmentDTO;
import org.springframework.stereotype.Component;

@Component
public class EquipmentMapper {

    public EquipModel toEntity(EquipmentDTO dto) {
        EquipModel model = new EquipModel();
        model.setName(dto.getName());
        return model;
    }

    public EquipmentDTO toDTO(EquipModel model) {
        EquipmentDTO dto = new EquipmentDTO(model.getId(), model.getName());
        return dto;
    }
}