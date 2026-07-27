package org.example.projectbackend.models.user.dtos;

public record CreateUserDto(String firstName, String lastName, String email, String password) { }