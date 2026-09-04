package org.example.projectbackend.services.auth;

import lombok.RequiredArgsConstructor;
import org.example.projectbackend.config.JwtService;
import org.example.projectbackend.exceptions.user.UserNotFoundException;
import org.example.projectbackend.models.auth.AuthenticationDto;
import org.example.projectbackend.models.auth.AuthenticationResponseDto;
import org.example.projectbackend.models.auth.LoginRequestDto;
import org.example.projectbackend.models.user.Role;
import org.example.projectbackend.models.user.User;
import org.example.projectbackend.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    
    public AuthenticationResponseDto register(AuthenticationDto dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRole(Role.USER);
        user.setOrganisation(null);
        user.setProjects(null);
        
        userRepository.save(user);

        var token = jwtService.generateToken(user);
        return new AuthenticationResponseDto(token);
    }

    public AuthenticationResponseDto login(LoginRequestDto dto) throws UserNotFoundException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(), 
                        dto.getPassword()
                )
        );
        
        var user =  userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UserNotFoundException(dto.getEmail()));
        
        var token = jwtService.generateToken(user);
        return new AuthenticationResponseDto(token);
    }
}
