package org.example.projectbackend.models.project.dtos;

import org.example.projectbackend.models.project.ProjectState;
import org.example.projectbackend.models.user.dtos.UserDto;

import java.util.Date;
import java.util.List;

public record ProjectDto(Long id, String name, ProjectState state, Date created, Date startDate, Date endDate, List<UserDto> users) { }