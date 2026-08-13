package org.example.projectbackend.models.organisation.dtos;

import org.example.projectbackend.models.project.dtos.ProjectDto;
import org.example.projectbackend.models.user.dtos.UserDto;

import java.util.List;

public record OrganisationDto(String name, List<UserDto> users, List<ProjectDto> projects) { }
