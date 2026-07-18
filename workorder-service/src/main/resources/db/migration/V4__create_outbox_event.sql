CREATE TABLE outbox_event (
          id BIGSERIAL PRIMARY KEY,
          event_type VARCHAR(100),
          payload TEXT,
          created_at TIMESTAMP,
          status VARCHAR(20)
);