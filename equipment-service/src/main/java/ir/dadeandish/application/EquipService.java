package ir.dadeandish.application;

import ir.dadeandish.EquipmentMapper;
import ir.dadeandish.domain.EquipModel;
import ir.dadeandish.domain.EquipRepository;
import ir.dadeandish.dto.EquipmentDTO;
import ir.dadeandish.dto.EquipmentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EquipService {

    EquipRepository equipRepository;
    EquipmentMapper mapper;

    @Autowired
    public EquipService(EquipRepository equipRepository, EquipmentMapper equipmentMapper) {
        this.mapper= equipmentMapper;
        this.equipRepository = equipRepository;
    }

    @Transactional
    public void updateStatus(
            Integer equipmentId,
            EquipmentStatus status) {

        EquipModel equipment =
                equipRepository.findById(equipmentId)
                        .orElseThrow();

        equipment.setEquipmentStatus(status);
    }

    public EquipmentDTO getEquipByID(int id){
        EquipModel equipModel= equipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipment with this id didn't find"));
        return convertEquipModelToDTO(equipModel);
    }

    private EquipmentDTO convertEquipModelToDTO(EquipModel model){
        EquipmentDTO dto = new EquipmentDTO(model.getId(), model.getName());
        return dto;
    }

    private EquipModel convertEquipdtoToEquipModel(EquipmentDTO equipmentDTO){
        EquipModel equipModel= new EquipModel();
        equipModel.setName(equipmentDTO.getName());
        equipModel.setId(equipmentDTO.getId());
        return equipModel;
    }

    private void validate(EquipmentDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
    }

    public EquipModel findById(Integer id) {
        return equipRepository.findById(id)
                .orElseThrow();
    }
}
