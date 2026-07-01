package ir.dadeandish.application;

import ir.dadeandish.dto.AssignWorkOrderDTO;
import ir.dadeandish.dto.ObservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workOrders")
public class WorkOrderController {

    WorkOrderService workOrderService;

    @Autowired
    public WorkOrderController(WorkOrderService workOrderService) {
        this.workOrderService = workOrderService;
    }

    @GetMapping("/assignWorkOrder")
    ResponseEntity<String> AssignWorkOrderToEmployee(@RequestBody AssignWorkOrderDTO assignWorkOrderDTO){
        try {
            workOrderService.assignWorkOrderToEmployee(assignWorkOrderDTO.getWorkOrderId(), assignWorkOrderDTO.getEmployeeId());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("Error in assigning task");
        }
        return ResponseEntity.ok("Assigning task was successful");
    }

    @PostMapping("/addObservation")
    public String addObservationByEmployee(@RequestBody ObservationDTO observationDTO){
       try {
           workOrderService.addObservation(observationDTO.getWorkOrderId(), observationDTO.getObservResult(), observationDTO.getEquipStatus(), "Elahe Yousefi");
       }catch (Exception e){
           return "Error in saving result";
       }
       return "Saving observation result was successful";
    }
}
