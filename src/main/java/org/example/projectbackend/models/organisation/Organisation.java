package org.example.projectbackend.models.organisation;

import jakarta.persistence.*;
import lombok.Data;
import org.example.projectbackend.models.organisation.dtos.OrganisationDto;
import org.example.projectbackend.models.project.Project;
import org.example.projectbackend.models.user.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "organisations")
public class Organisation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 30)
    private String name;
    
    @OneToMany(mappedBy = "organisation")
    private List<Project> projects;

    @OneToMany(mappedBy = "organisation")
    private List<User> users;
    
    public OrganisationDto toDto() {
        if (this.projects == null){ 
            projects = new ArrayList<Project>();
        }
        
        if (this.users == null) {
            users = new ArrayList<User>();
        }
        
        return new OrganisationDto(this.name, this.users.stream().map(User::toDto).toList(), this.projects.stream().map(Project::toDto).toList());
    }
}
