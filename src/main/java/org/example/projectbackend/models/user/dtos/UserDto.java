package org.example.projectbackend.models.user.dtos;


import org.example.projectbackend.models.project.dtos.ProjectDto;
import java.util.List;

public record UserDto(Long id, String name, String email, List<ProjectDto> projects) { }
