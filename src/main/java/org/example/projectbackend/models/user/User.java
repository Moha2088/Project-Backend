package org.example.projectbackend.models.user;

import jakarta.persistence.*;
import lombok.Data;
import org.example.projectbackend.models.project.Project;
import org.example.projectbackend.models.project.dtos.ProjectDto;
import org.example.projectbackend.models.user.dtos.UserDto;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "users", indexes = {
        @Index(name = "idx_user_name", columnList = "name"),
        @Index(name = "idx_user_email", columnList = "email")
}, uniqueConstraints = {
        @UniqueConstraint(name = "UniqueEmail",
        columnNames = "email")
})
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 20)
    private String name;
    
    @Column(length = 30)
    private String email;
    
    @Column(length = 60)
    private String password;
    
    @CreationTimestamp
    private Date createdAt;
    
    @ManyToMany
    @JoinTable(
            name = "project_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects;
    
    public UserDto toDto() {
        if (this.projects == null) {
            this.projects = new ArrayList<Project>();
        }
        
        return new UserDto(this.id, this.name, this.email, this.projects.stream().map(Project::toDto).toList());
    }
}
