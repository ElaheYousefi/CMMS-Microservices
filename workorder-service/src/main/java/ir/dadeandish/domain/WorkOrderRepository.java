package ir.dadeandish.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface WorkOrderRepository extends JpaRepository<WorkOrderModel, Integer> {

    @Transactional
    @Modifying
    @Query("update WorkOrderModel w set w.employeeId= ?2 where w.id= ?1")
    void assignWorkOrderToEmployee(int workOrderId, int employeeId);
}
