package org.example.projectbackend.models.user.dtos;


import org.example.projectbackend.models.organisation.dtos.OrganisationDto;
import org.example.projectbackend.models.project.dtos.ProjectDto;
import java.util.List;

public record UserDto(Long id, String firstName, String lastName, String email, OrganisationDto organisation, List<ProjectDto> projects) { }