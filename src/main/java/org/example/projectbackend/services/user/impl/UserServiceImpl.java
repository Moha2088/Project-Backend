package org.example.projectbackend.services.user.impl;

import lombok.RequiredArgsConstructor;
import org.example.projectbackend.exceptions.user.UserNotFoundException;
import org.example.projectbackend.models.user.User;
import org.example.projectbackend.models.user.dtos.CreateUserDto;
import org.example.projectbackend.models.user.dtos.UserDto;
import org.example.projectbackend.repositories.UserRepository;
import org.example.projectbackend.services.user.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    
    @Override
    public void createuser(CreateUserDto dto) {
        String hash = encoder.encode(dto.password());

        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(hash);
        
        userRepository.save(user);
    }

    @Override
    public UserDto getUserById(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return user.toDto();
    }

    @Override
    public void deleteUser(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
    }
}
