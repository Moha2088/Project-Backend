package org.example.projectbackend.services.project;

import org.example.projectbackend.exceptions.project.ProjectNotFoundException;
import org.example.projectbackend.models.project.dtos.CreateProjectDto;
import org.example.projectbackend.models.project.dtos.ProjectDto;

public interface ProjectService {
    void createProject(CreateProjectDto dto);
    ProjectDto getProjectById(Long id);
    void deleteProject(Long id) throws ProjectNotFoundException;
}
