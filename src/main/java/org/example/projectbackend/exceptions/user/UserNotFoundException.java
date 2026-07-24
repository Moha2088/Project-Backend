package org.example.projectbackend.exceptions.user;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(Long id) {
        super(String.format("User with id: %s, was not found", id));
    }
}