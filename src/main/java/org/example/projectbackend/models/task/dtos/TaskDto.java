package org.example.projectbackend.models.task.dtos;

import org.example.projectbackend.models.project.dtos.ProjectDto;
import org.example.projectbackend.models.task.TaskState;

public record TaskDto(Long id, String name, String description, String assignee, TaskState state, ProjectDto project) { }