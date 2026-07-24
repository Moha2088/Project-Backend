package org.example.projectbackend.integration;

import jakarta.transaction.Transactional;
import org.example.projectbackend.exceptions.user.UserNotFoundException;
import org.example.projectbackend.models.user.dtos.CreateUserDto;
import org.example.projectbackend.services.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.example.projectbackend.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Import(TestcontainersConfiguration.class)
public class UserServiceTests {
    
    private final UserService userService;

    @Autowired
    public UserServiceTests(UserService userService) {
        this.userService = userService;
    }
    

    @Test
    public void getUser_ShouldReturnUserDto_WhenUserExists() throws UserNotFoundException {
        CreateUserDto dto = new CreateUserDto("Johndoe", "john@doe.com", "Johndoe123456");
        
        userService.createuser(dto);
        var response = userService.getUserById(1L);

        assertEquals(response.name(), dto.name());
        assertEquals(response.email(), dto.email());
    }
    
    @Test
    public void getUser_ShouldThrowUserNotFoundException_WhenUserDoesntExist() {
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(1L));
    }
}
