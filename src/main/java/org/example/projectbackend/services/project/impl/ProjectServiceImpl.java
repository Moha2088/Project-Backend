package org.example.projectbackend.services.project.impl;

import lombok.RequiredArgsConstructor;
import org.example.projectbackend.exceptions.project.ProjectNotFoundException;
import org.example.projectbackend.models.project.Project;
import org.example.projectbackend.models.project.ProjectState;
import org.example.projectbackend.models.project.dtos.CreateProjectDto;
import org.example.projectbackend.models.project.dtos.ProjectDto;
import org.example.projectbackend.repositories.ProjectRepository;
import org.example.projectbackend.services.project.ProjectService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    
    @Override
    public void createProject(CreateProjectDto dto) {
        Project project = new Project();
        project.setName(dto.name());
        project.setStartDate(dto.startDate());
        project.setEndDate(dto.endDate());
        project.setState(getState(dto.startDate(), dto.endDate()));
        
        projectRepository.save(project);
    }

    @Override
    public ProjectDto getProjectById(Long id) {
        Project project = projectRepository.getReferenceById(id);
        return project.toDto();
    }

    @Override
    public void deleteProject(Long id) throws ProjectNotFoundException {
        Project project = projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
        projectRepository.delete(project);
    }
    
    private ProjectState getState(Date startDate, Date endDate) {
        Date currentDate = new Date();
        ProjectState state = null;
        
        if (currentDate.before(startDate)){
            state = ProjectState.PENDING;
        }
        
        if (currentDate.after(endDate)) {
            state = ProjectState.FINISHED;
        }
        
        if (currentDate.after(startDate) && currentDate.before(endDate)) {
            state = ProjectState.ONGOING;
        }
            
        return state;
    }
}
