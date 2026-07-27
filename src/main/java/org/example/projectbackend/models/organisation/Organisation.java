package org.example.projectbackend.models.organisation;

import jakarta.persistence.*;
import lombok.Data;
import org.example.projectbackend.models.project.Project;
import org.example.projectbackend.models.user.User;
import java.util.List;

@Entity
@Data
@Table(name = "organisations")
public class Organisation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 30)
    private Long name;
    
    @OneToMany(mappedBy = "organisation")
    private List<Project> projects;

    @OneToMany(mappedBy = "organisation")
    private List<User> users;
}
