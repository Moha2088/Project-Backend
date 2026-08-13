package org.example.projectbackend.models.task;

import jakarta.persistence.*;
import lombok.Data;
import org.example.projectbackend.models.project.Project;
import org.example.projectbackend.models.task.dtos.TaskDto;


@Entity
@Data
@Table(name = "tasks", indexes = {
        @Index(name = "idx_task_name", columnList = "name"),
        @Index(name = "idx_task_assignee", columnList = "assignee"),
        @Index(name = "idx_task_state", columnList = "state")
})
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private String description;
    
    @Column(length = 20)
    private String assignee;
    
    @Enumerated(EnumType.STRING)
    private TaskState state;
    
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Project project;
    
    public TaskDto toDto() {
        return new TaskDto(this.id, this.name, this.description, this.assignee, this.state, this.project.toDto());
    }
}
