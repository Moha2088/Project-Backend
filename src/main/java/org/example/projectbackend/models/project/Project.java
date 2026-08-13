package org.example.projectbackend.models.project;

import jakarta.persistence.*;
import lombok.Data;
import org.example.projectbackend.models.organisation.Organisation;
import org.example.projectbackend.models.project.dtos.ProjectDto;
import org.example.projectbackend.models.task.Task;
import org.example.projectbackend.models.user.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "projects", indexes = {
        @Index(name = "idx_project_name", columnList = "name"),
        @Index(name = "idx_project_created_at", columnList = "createdAt"),
        @Index(name = "idx_project_start_date", columnList = "start_date"),
        @Index(name = "idx_project_endDate", columnList = "end_date")
})
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 20)
    private String name;
    
    @CreationTimestamp
    private Date createdAt;
    
    @UpdateTimestamp
    private Date updatedAt;
    
    private Date startDate;

    private Date endDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 8)
    private ProjectState state;

    @ManyToMany(mappedBy = "projects")
    private List<User> users;
    
    @OneToMany(mappedBy = "project")
    private List<Task> tasks;
    
    @ManyToOne
    @JoinColumn(name = "organisation_id", nullable = false)
    private Organisation organisation;
    
    public ProjectDto toDto() {
        return new ProjectDto(this.id, this.name, this.state, this.createdAt, this.startDate, this.endDate, this.users.stream().map(User::toDto).toList());
    }
}
