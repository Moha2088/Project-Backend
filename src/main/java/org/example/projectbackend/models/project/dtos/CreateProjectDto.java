package org.example.projectbackend.models.project.dtos;

import java.util.Date;

public record CreateProjectDto(String name, Date startDate, Date endDate) {}