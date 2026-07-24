package org.example.projectbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.projectbackend.exceptions.project.ProjectNotFoundException;
import org.example.projectbackend.models.project.dtos.CreateProjectDto;
import org.example.projectbackend.models.project.dtos.ProjectDto;
import org.example.projectbackend.services.project.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createProject(@RequestBody CreateProjectDto dto) {
        projectService.createProject(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProjectDto> getProject(@PathVariable Long id) {
        ProjectDto dto = projectService.getProjectById(id);
        return ResponseEntity.ok(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProject(@PathVariable Long id) throws ProjectNotFoundException {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}