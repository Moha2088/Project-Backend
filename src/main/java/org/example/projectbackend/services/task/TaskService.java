package org.example.projectbackend.services.task;


import org.example.projectbackend.models.task.dtos.CreateTaskDto;
import org.example.projectbackend.models.task.dtos.TaskDto;

public interface TaskService {
    void createTask(CreateTaskDto dto);
    TaskDto getTask(Long id);
    void deleteTask(Long id);
    
}
