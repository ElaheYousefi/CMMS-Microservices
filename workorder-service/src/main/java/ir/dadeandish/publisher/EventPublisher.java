package ir.dadeandish.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import ir.dadeandish.domain.OutboxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class EventPublisher {

    OutboxRepository outboxRepository;
    OutboxPublisher outboxPublisher;
    private static final Logger logger= LoggerFactory.getLogger(OutboxPublisher.class);

    @Autowired
    public EventPublisher(OutboxRepository outboxRepository, OutboxPublisher outboxPublisher) {
        this.outboxRepository = outboxRepository;
        this.outboxPublisher = outboxPublisher;
    }

    @Scheduled(fixedDelay = 5000)
    public void publishEvent() throws ExecutionException, JsonProcessingException, InterruptedException {
        List<Integer> ids= outboxRepository.findTop100Pending(PageRequest.of(0, 100));
        for(Integer id: ids){
            outboxPublisher.publishSingleEvent(id);
        }
    }
}
