package ir.dadeandish.domain;

import ir.dadeandish.publish.OutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OutboxRepository extends JpaRepository<OutboxEvent, Integer> {

    @Query("select o from OutboxEvent o where o.eventType= 'READY_ASSIGN_TASK' and o.status= 'PENDING'")
    List<OutboxEvent> findPending();

    @Query("select o.id from OutboxEvent o where o.eventType= 'READY_ASSIGN_TASK' and o.status= 'PENDING'")
    List<Integer> findPendingIds();
}
