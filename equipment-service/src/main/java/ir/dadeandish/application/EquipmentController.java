package ir.dadeandish.application;

import ir.dadeandish.domain.EquipModel;
import ir.dadeandish.dto.EquipmentDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    EquipService equipService;

    public EquipmentController(EquipService equipService) {
        this.equipService = equipService;
    }

    @GetMapping("/{id}")
    public EquipmentDTO getEquipment(@PathVariable Integer id) {
        EquipModel equipment= equipService.findById(id);
        return new EquipmentDTO(
                equipment.getId(),
                equipment.getName()
        );
    }
}