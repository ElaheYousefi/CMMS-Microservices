package ir.dadeandish.publish;

import ir.dadeandish.application.AssignTaskService;
import ir.dadeandish.domain.OutboxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PublishEvents {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final OutboxRepository outboxRepository;
    private static final Logger log =
            LoggerFactory.getLogger(AssignTaskService.class);
    private final OutboxPublisher outboxPublisher;

    public PublishEvents(KafkaTemplate<String, Object> kafkaTemplate, OutboxRepository outboxRepository, OutboxPublisher outboxPublisher)
    {
        this.kafkaTemplate = kafkaTemplate;
        this.outboxRepository= outboxRepository;
        this.outboxPublisher= outboxPublisher;
    }

    @Scheduled(fixedDelay = 10000)
    public void publishPendingEvents() {

        List<Integer> ids = outboxRepository.findPendingIds();
        for (Integer id : ids) {
            try {
                outboxPublisher.publishSingleEvent(id);
            } catch (Exception ex) {
                log.error("Failed to publish {}", id, ex);
            }
        }
    }
}
