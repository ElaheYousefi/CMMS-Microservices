package ir.dadeandish.domain;

import ir.dadeandish.enums.EventType;
import ir.dadeandish.enums.OutboxStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class OutboxEvent {

    @Id
    @GeneratedValue
    private Integer id;
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(length = 100)
    private EventType eventType;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private OutboxStatus status;

    @Column(columnDefinition = "TEXT")
    private String payload;

    public OutboxEvent() {
    }

    public OutboxEvent(LocalDateTime createdAt, EventType eventType, OutboxStatus status, String payload) {
        this.createdAt = createdAt;
        this.eventType = eventType;
        this.status = status;
        this.payload = payload;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OutboxStatus getStatus() {
        return status;
    }

    public void setStatus(OutboxStatus status) {
        this.status = status;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
