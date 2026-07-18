package ir.dadeandish.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.dadeandish.domain.OutboxEvent;
import ir.dadeandish.domain.OutboxRepository;
import ir.dadeandish.enums.OutboxStatus;
import ir.dadeandish.event.MaintenanceCompletedEvent;
import ir.dadeandish.event.WorkOrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.concurrent.ExecutionException;

@Service
public class OutboxPublisher {

    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final Logger logger= LoggerFactory.getLogger(OutboxPublisher.class);

    @Autowired
    public OutboxPublisher(OutboxRepository outboxRepository, ObjectMapper objectMapper, KafkaTemplate<String, Object> kafkaTemplate) {
        this.outboxRepository = outboxRepository;
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    public void publishSingleEvent(Integer id) throws JsonProcessingException, ExecutionException, InterruptedException {

        OutboxEvent outboxEvent= outboxRepository.findById(id)
                .orElseThrow();

        try {
            switch (outboxEvent.getEventType()) {

                case WORKORDER_ASSIGNED:
                    publishWorkOrderAssigned(outboxEvent);
                    break;

                case WORKORDER_COMPLETED:
                    publishWorkOrderCompleted(outboxEvent);
                    break;

                default:
                    throw new IllegalArgumentException(
                            "Unknown event type: " +
                                    outboxEvent.getEventType()
                    );
            }
            outboxEvent.setStatus(OutboxStatus.PUBLISHED);
        }catch(Exception ex) {

            outboxEvent.setStatus(
                    OutboxStatus.PENDING);

            throw ex;
        }
    }

    public void publishWorkOrderAssigned(OutboxEvent outboxEvent) throws ExecutionException, JsonProcessingException, InterruptedException {

        WorkOrderCreatedEvent kafkaEvent = objectMapper.readValue(
                outboxEvent.getPayload(),
                WorkOrderCreatedEvent.class
        );
        logger.debug("send work order assign task");
        kafkaTemplate.send("workorder-created-topic", kafkaEvent).get();//wait for broker ack
    }

    public void publishWorkOrderCompleted(OutboxEvent outboxEvent) throws ExecutionException, JsonProcessingException, InterruptedException {
        MaintenanceCompletedEvent kafkaEvent = objectMapper.readValue (
            outboxEvent.getPayload(),
                MaintenanceCompletedEvent.class
        );
        kafkaTemplate.send("maintenance-completed-topic", kafkaEvent).get();//wait for broker ack
    }
}
