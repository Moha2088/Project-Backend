package org.example.projectbackend.exceptions.project;

public class ProjectNotFoundException extends Exception {
    public ProjectNotFoundException(Long id) {
        super(String.format("Project with id: %s was not found!", id));
    }
}
