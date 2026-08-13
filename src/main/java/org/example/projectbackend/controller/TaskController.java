package org.example.projectbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.projectbackend.models.task.dtos.CreateTaskDto;
import org.example.projectbackend.models.task.dtos.TaskDto;
import org.example.projectbackend.services.task.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    
    private final TaskService taskService;
    
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createTask(@RequestBody CreateTaskDto dto) {
        taskService.createTask(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {
        TaskDto dto = taskService.getTask(id);
        return ResponseEntity.ok(dto);
    }
    
    @RequestMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
