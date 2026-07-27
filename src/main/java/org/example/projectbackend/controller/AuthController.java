package org.example.projectbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.projectbackend.exceptions.user.UserNotFoundException;
import org.example.projectbackend.models.auth.AuthenticationDto;
import org.example.projectbackend.models.auth.AuthenticationResponseDto;
import org.example.projectbackend.models.auth.LoginRequestDto;
import org.example.projectbackend.services.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @RequestMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> register(@RequestBody AuthenticationDto dto) {
        return ResponseEntity.ok(service.register(dto));
    }
    
    @RequestMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody LoginRequestDto dto) throws UserNotFoundException {
        return ResponseEntity.ok(service.login(dto));
    }
}