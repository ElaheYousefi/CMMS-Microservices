package ir.dadeandish;

import ir.dadeandish.domain.EquipModel;
import ir.dadeandish.domain.EquipmentDTO;
import org.springframework.stereotype.Component;

@Component
public class EquipmentMapper {

    public EquipModel toEntity(EquipmentDTO dto) {
        EquipModel model = new EquipModel();
        model.setName(dto.getName());
        return model;
    }

    public EquipmentDTO toDTO(EquipModel model) {
        EquipmentDTO dto = new EquipmentDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        return dto;
    }
}