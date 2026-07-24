package org.example.projectbackend.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.example.projectbackend.exceptions.project.ProjectNotFoundException;
import org.example.projectbackend.exceptions.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(Exception exception, HttpServletRequest request) {
        var date = LocalDateTime.now().format(formatter);
        return new ResponseEntity<>(new ExceptionResponse(date, exception.getMessage(),  request.getServletPath()), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleProjectNotFound(Exception exception, HttpServletRequest request) {
        var date = LocalDateTime.now().format(formatter);
        return new ResponseEntity<>(new ExceptionResponse(date, exception.getMessage(),  request.getServletPath()), HttpStatus.NOT_FOUND);
    }
}
