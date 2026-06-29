package ir.dadeandish.domain;

import ir.dadeandish.domain.AssignTask;
import ir.dadeandish.domain.AssignTaskDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AssignedTaskRepository extends JpaRepository<AssignTask, Integer> {

    @Query(value="select new ir.dadeandish.domain.AssignTaskDTO(at.id, at.equipId, dt.periodDay) from AssignTask at inner join DefinedTask dt" +
            " where at.active= 1 and at.nextExecutionDate<= current_date")
    List<AssignTaskDTO> findTop100ReadyTasks(Pageable pageable);

    @Transactional
    @Modifying
    @Query(value="update AssignTask d set d.nextExecutionDate=?2 where d.id= ?1")
    void updateAssignTaskNextExecutionDate(Integer id, LocalDateTime nextExecutionDate);

}
