package org.example.projectbackend.controller;

import lombok.RequiredArgsConstructor;

import java.security.Principal;

import org.example.projectbackend.exceptions.user.UserNotFoundException;
import org.example.projectbackend.models.user.dtos.CreateUserDto;
import org.example.projectbackend.models.user.dtos.UserDto;
import org.example.projectbackend.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody CreateUserDto dto) {
        userService.createuser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getUser(@PathVariable(name = "id") Long id, Principal principal) throws UserNotFoundException {
        UserDto dto = userService.getUserById(id);  
        logger.info("User retrieved: " + dto.email());
        return ResponseEntity.ok(dto);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable Long id) throws UserNotFoundException{
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
