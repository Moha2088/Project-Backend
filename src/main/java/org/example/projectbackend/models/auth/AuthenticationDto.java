package org.example.projectbackend.models.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDto {
    private String firstName; 
    private String lastName; 
    private String email; 
    private String password;
}
