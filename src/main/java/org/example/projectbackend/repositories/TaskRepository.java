package org.example.projectbackend.repositories;

import org.example.projectbackend.models.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> { }
