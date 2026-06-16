package ir.dadeandish.application;

import ir.dadeandish.domain.WorkOrderModel;
import ir.dadeandish.domain.WorkorderDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public WorkorderDTO convertWorkOrderToDTO(WorkOrderModel workOrderModel){

        WorkorderDTO workorderDTO= new WorkorderDTO();
        workorderDTO.setId(workOrderModel.getId());
        workorderDTO.setTaskId(workOrderModel.getAssignTaskId());
        workorderDTO.setDueDate(workOrderModel.getDueDate());
        return workorderDTO;
    }
}
