package ir.dadeandish.workorder.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.dadeandish.domain.OutboxEvent;
import ir.dadeandish.event.WorkOrderCreatedEvent;
import ir.dadeandish.publisher.OutboxPublisher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import java.util.concurrent.CompletableFuture;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class KafkaPublisherTest {

    @Mock
    private KafkaTemplate<String, WorkOrderCreatedEvent> kafkaTemplate;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private OutboxPublisher publisher;

    @Test
    void publishWorkOrderAssigned_shouldSendKafkaMessage() throws Exception {

        // Arrange
        OutboxEvent outboxEvent = new OutboxEvent();
        outboxEvent.setPayload("{json}");

        WorkOrderCreatedEvent event = new WorkOrderCreatedEvent(
                3,
                100,
                5,
                "123",
                "john@test.com",
                "John",
                "Pump"
        );

        when(objectMapper.readValue("{json}", WorkOrderCreatedEvent.class))
                .thenReturn(event);

        CompletableFuture<SendResult<String, WorkOrderCreatedEvent>> future =
                CompletableFuture.completedFuture(null);

        when(kafkaTemplate.send("workorder-created-topic", event))
                .thenReturn(future);

        // Act
        publisher.publishWorkOrderAssigned(outboxEvent);

        // Assert
        verify(objectMapper)
                .readValue("{json}", WorkOrderCreatedEvent.class);

        verify(kafkaTemplate)
                .send("workorder-created-topic", event);
    }
}
