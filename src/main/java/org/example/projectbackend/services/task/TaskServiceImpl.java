package org.example.projectbackend.services.task;

import lombok.RequiredArgsConstructor;
import org.example.projectbackend.config.CurrentUserProvider;
import org.example.projectbackend.models.task.Task;
import org.example.projectbackend.models.task.TaskState;
import org.example.projectbackend.models.task.dtos.CreateTaskDto;
import org.example.projectbackend.models.task.dtos.TaskDto;
import org.example.projectbackend.models.user.User;
import org.example.projectbackend.repositories.TaskRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    
    private final TaskRepository taskRepository;
    private final CurrentUserProvider userProvider;

    @Override
    public void createTask(CreateTaskDto dto) {
        User user = userProvider.getCurrentUser();


        Task task = new Task();
        task.setName(dto.name());
        task.setDescription(dto.description());
        task.setAssignee(dto.assignee());
        task.setState(TaskState.IN_PROGRESS);
//        task.setProject();
        
        taskRepository.save(task);
    }

    @Override
    public TaskDto getTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        return task.toDto();
    }
    
    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        taskRepository.delete(task);
    }
}
