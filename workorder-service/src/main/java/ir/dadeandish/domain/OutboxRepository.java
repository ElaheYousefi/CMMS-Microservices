package ir.dadeandish.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OutboxRepository extends JpaRepository<OutboxEvent, Integer> {

    @Query("select o.id from OutboxEvent o where o.status= 'PENDING'")
    List<Integer> findTop100Pending(Pageable pageable);
}
