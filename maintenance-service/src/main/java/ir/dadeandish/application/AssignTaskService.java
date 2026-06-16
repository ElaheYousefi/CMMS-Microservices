package ir.dadeandish.application;

import ir.dadeandish.domain.AssignTask;
import ir.dadeandish.domain.AssignTaskDTO;
import ir.dadeandish.domain.AssignedTaskRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
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

    @Autowired
    public AssignTaskService(AssignedTaskRepository assginTaskRepository, Mapper mapper) {
        this.assginTaskRepository = assginTaskRepository;
        this.mapper= mapper;
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

    @Scheduled(fixedDelay= 5000)  //each 200 seconds
    public void getReadyTask(){
        System.out.println("try to publish tasks are ready");
         List<AssignTaskDTO> assignTaskList= assginTaskRepository.findReadyTasks();
//         eventPublisher.publishEvent(new ReadyAssignTasksEvent(assignTaskList));
    }

    public void proceedSchedule(int taskId){
        AssignTask assignTask= assginTaskRepository.findById(taskId)
                .orElseThrow(()-> new RuntimeException("there is not this assign task"));
        //DefineTaskDto defineTaskDto= maintenanceAPI.getDefineTaskById(assignTask.getDefinedTaskId());
        LocalDate nextExecutionDate= LocalDate.now()
                .plusDays(10);
        //.plusDays(defineTaskDto.getPeriodDay()+ 1);
        assginTaskRepository.updateAssignTaskNextExecutionDate(taskId, nextExecutionDate);
    }
}