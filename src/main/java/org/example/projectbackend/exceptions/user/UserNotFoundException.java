package org.example.projectbackend.exceptions.user;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(Long id) {
        super(String.format("User with id: %s, was not found", id));
    }
    
    public UserNotFoundException(String email) {
        super(String.format("User with name: %s, was not found", email));
        
    }
}