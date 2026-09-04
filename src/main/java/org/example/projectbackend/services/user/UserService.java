package org.example.projectbackend.services.user;

import org.example.projectbackend.exceptions.user.UserNotFoundException;
import org.example.projectbackend.models.user.User;
import org.example.projectbackend.models.user.dtos.CreateUserDto;
import org.example.projectbackend.models.user.dtos.UserDto;

public interface UserService  {
    void createuser(CreateUserDto dto);
    UserDto getUserById(Long id) throws UserNotFoundException;
    User findByEmail(String email);
    void deleteUser(Long id) throws UserNotFoundException;
}