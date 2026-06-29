package ir.dadeandish.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.dadeandish.domain.*;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class AssignTaskService {

    @PostConstruct
    public void init() {
        System.out.println("AssignTaskService CREATED");
    }
    private AssignedTaskRepository assginTaskRepository;
    private Mapper mapper;
    private final DefineTaskRepository defineTaskRepository;
    private static final Logger log =
            LoggerFactory.getLogger(AssignTaskService.class);
    private ObjectMapper objectMapper;
    private final TaskProcessor taskProcessor;

    @Autowired
    public AssignTaskService(AssignedTaskRepository assginTaskRepository, Mapper mapper,
                              DefineTaskRepository defineTaskRepository, ObjectMapper objectMapper, TaskProcessor taskProcessor) {
        this.assginTaskRepository = assginTaskRepository;
        this.mapper= mapper;
        this.defineTaskRepository= defineTaskRepository;
        this.objectMapper= objectMapper;
        this.taskProcessor= taskProcessor;
    }

    public AssignTaskDTO getById(int id) {
         AssignTask assignTask= assginTaskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AssignTask not found with id: " + id));
         return mapper.convertAssignTaskToDTO(assignTask);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onStartup(){
        getReadyTask();
    }

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.HOURS)
    public void scheduledRun() {
        getReadyTask();
    }

    @Scheduled(fixedDelay= 200000)  //each 200 seconds
    public void getReadyTask(){
        System.out.println("try to publish tasks are ready");
        List<AssignTaskDTO> tasks= assginTaskRepository.findTop100ReadyTasks(PageRequest.of(0, 100));

        tasks.forEach(task -> {
            try {
                taskProcessor.processTask(task);
            } catch (Exception e) {
                log.error("Failed processing task {}", task.getId(), e);
            }
        });
    }
}