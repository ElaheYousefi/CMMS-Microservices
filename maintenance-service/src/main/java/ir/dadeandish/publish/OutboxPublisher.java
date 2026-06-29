package ir.dadeandish.publish;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.dadeandish.application.AssignTaskService;
import ir.dadeandish.domain.OutboxRepository;
import ir.dadeandish.enums.OutboxStatus;
import ir.dadeandish.event.ReadyAssignTasksEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OutboxPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final OutboxRepository outboxRepository;
    private static final Logger log =
            LoggerFactory.getLogger(AssignTaskService.class);
    private final ObjectMapper objectMapper;


    @Autowired
    public OutboxPublisher(KafkaTemplate<String, Object> kafkaTemplate, OutboxRepository outboxRepository, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.outboxRepository = outboxRepository;
        this.objectMapper= objectMapper;
    }

    @Transactional
    public void publishSingleEvent(Integer id) throws Exception {
        OutboxEvent event = outboxRepository.findById(id)
                .orElseThrow();
        try {
            ReadyAssignTasksEvent kafkaEvent =
                    objectMapper.readValue(
                            event.getPayload(),
                            ReadyAssignTasksEvent.class
                    );

            kafkaTemplate.send(
                    "ready-task-topic",
                    kafkaEvent
            ).get();//wait for broker ack
            event.setStatus(OutboxStatus.PUBLISHED);
        }catch (Exception ex){
            event.setStatus(OutboxStatus.PENDING);
            throw ex;
        }
    }
}