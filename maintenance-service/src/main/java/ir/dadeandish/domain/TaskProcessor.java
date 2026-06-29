package ir.dadeandish.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.dadeandish.enums.EventType;
import ir.dadeandish.publish.OutboxEvent;
import ir.dadeandish.publish.PublishEvents;
import ir.dadeandish.enums.OutboxStatus;
import ir.dadeandish.event.ReadyAssignTasksEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TaskProcessor {

    private final AssignedTaskRepository assignedTaskRepository;
    private final ObjectMapper objectMapper;
    private final OutboxRepository outboxRepository;

    @Autowired
    public TaskProcessor(AssignedTaskRepository assignedTaskRepository, ObjectMapper objectMapper, OutboxRepository outboxRepository) {
        this.assignedTaskRepository = assignedTaskRepository;
        this.objectMapper = objectMapper;
        this.outboxRepository = outboxRepository;
    }

    @Transactional
    public void processTask(AssignTaskDTO task) throws JsonProcessingException {

        LocalDateTime nextExecutionDate =
                LocalDateTime.now().plusDays(task.getPeriodDay());

        assignedTaskRepository.updateAssignTaskNextExecutionDate(
                task.getId(),
                nextExecutionDate
        );

        ReadyAssignTasksEvent event =
                new ReadyAssignTasksEvent(
                        task.getId(),
                        task.getEquipId()
                );

        String payload =
                objectMapper.writeValueAsString(event);

        outboxRepository.save(
                new OutboxEvent(
                        EventType.READY_ASSIGN_TASK,
                        payload,
                        LocalDateTime.now(),
                        OutboxStatus.PENDING
                )
        );
    }
 }

