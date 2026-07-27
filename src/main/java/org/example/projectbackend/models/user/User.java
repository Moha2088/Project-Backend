package org.example.projectbackend.models.user;

import jakarta.persistence.*;
import lombok.Data;
import org.example.projectbackend.models.organisation.Organisation;
import org.example.projectbackend.models.project.Project;
import org.example.projectbackend.models.project.dtos.ProjectDto;
import org.example.projectbackend.models.user.dtos.UserDto;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
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
public class User implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 20)
    private String firstName;
    
    @Column(length = 20)
    private String lastName;
    
    @Column(length = 30)
    private String email;
    
    @Column(length = 60)
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 5)
    private Role role;
    
    @CreationTimestamp
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "organisation_id", nullable = false)
    private Organisation organisation;
    
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
        
        return new UserDto(this.id, this.firstName, this.lastName, this.email, this.projects.stream().map(Project::toDto).toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return null;
    }
}
