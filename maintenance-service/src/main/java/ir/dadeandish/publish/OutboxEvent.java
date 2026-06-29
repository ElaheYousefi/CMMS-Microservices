package ir.dadeandish.publish;

import ir.dadeandish.enums.EventType;
import ir.dadeandish.enums.OutboxStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class OutboxEvent {

    @Id
    @GeneratedValue
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(length = 100)
    private EventType eventType;

    @Column(columnDefinition = "TEXT")
    private String payload;
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private OutboxStatus status;

    public OutboxEvent() {
    }

    public OutboxEvent(EventType eventType, String payload, LocalDateTime createdAt, OutboxStatus status) {
        this.eventType = eventType;
        this.payload = payload;
        this.createdAt = createdAt;
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public int getId() {
        return id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public OutboxStatus getStatus() {
        return status;
    }

    public void setStatus(OutboxStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
