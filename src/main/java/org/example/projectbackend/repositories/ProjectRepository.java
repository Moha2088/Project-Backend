package org.example.projectbackend.repositories;

import org.example.projectbackend.models.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> { }
