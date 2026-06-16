package ir.dadeandish.application;

import ir.dadeandish.domain.AssignTask;
import ir.dadeandish.domain.AssignTaskDTO;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

   public AssignTaskDTO convertAssignTaskToDTO(AssignTask assignTask){
        AssignTaskDTO assignTaskDTO= new AssignTaskDTO();
        assignTaskDTO.setId(assignTask.getId());
        assignTaskDTO.setNextExecutionDate(assignTask.getNextExecutionDate());
        assignTaskDTO.setActive(assignTask.getActive());
        assignTaskDTO.setDefinedTaskId(assignTask.getDefinedTaskId());
        assignTaskDTO.setEquipId(assignTask.getEquipId());
        return assignTaskDTO;
    }

    public AssignTask convertDTOToAssignTask(AssignTaskDTO dto){
        AssignTask assignTask= new AssignTask();
        assignTask.setId(dto.getId());
        assignTask.setNextExecutionDate(dto.getNextExecutionDate());
        assignTask.setActive(dto.getActive());
        assignTask.setDefinedTaskId(dto.getDefinedTaskId());
        assignTask.setEquipId(dto.getEquipId());
        return assignTask;
    }
}
