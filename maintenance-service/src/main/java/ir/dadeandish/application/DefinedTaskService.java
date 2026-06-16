package ir.dadeandish.application;

import ir.dadeandish.domain.DefineTaskDto;
import ir.dadeandish.domain.DefineTaskRepository;
import ir.dadeandish.domain.DefinedTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DefinedTaskService {

    private DefineTaskRepository repository;
    private DefinedTaskMapper mapper;

    @Autowired
    public DefinedTaskService(DefineTaskRepository repository, DefinedTaskMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public DefineTaskDto createTask(DefineTaskDto dto) {
        DefinedTask task= mapper.converDefinDTOToDefineTask(dto);
        task= repository.save(task);
        return  mapper.converDefineTaskToDefinDTO(task);
    }

    public DefineTaskDto updateTask(int id, DefineTaskDto dto) {
        DefinedTask existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DefinedTask not found with id: " + id));

        existing.setTaskName(dto.getTaskName());
        existing.setStartDate(dto.getStartDate());
        existing.setEndDate(dto.getEndDate());
        existing.setPeriodDay(dto.getPeriodDay());
        existing= repository.save(existing);
        return mapper.converDefineTaskToDefinDTO(existing);
    }

    public DefineTaskDto getDefineTaskById(int id) {
         DefinedTask definedTask= repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DefinedTask not found with id: " + id));
         return mapper.converDefineTaskToDefinDTO(definedTask);
    }

    public List<DefineTaskDto> getAllTasks() {

       return repository.findAll()
                .stream()
                .map(mapper::converDefineTaskToDefinDTO)
                .toList();
    }

    public void deleteTask(int id) {
        repository.deleteById(id);
    }
}
