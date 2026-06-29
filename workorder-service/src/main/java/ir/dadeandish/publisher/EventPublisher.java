package ir.dadeandish.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import ir.dadeandish.domain.OutboxRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class EventPublisher {

    OutboxRepository outboxRepository;
    OutboxPublisher outboxPublisher;

    @Scheduled(fixedDelay = 100000)
    public void publishEvent() throws ExecutionException, JsonProcessingException, InterruptedException {
        List<Integer> ids= outboxRepository.findTop100Pending(PageRequest.of(0, 100));
        for(Integer id: ids){
            outboxPublisher.publishSingleEvent(id);
        }
    }
}
