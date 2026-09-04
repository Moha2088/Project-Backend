package org.example.projectbackend.config;

import lombok.RequiredArgsConstructor;
import org.example.projectbackend.models.user.User;
import org.example.projectbackend.services.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrentUserProvider {
    private final UserService userService;
    
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (auth == null || !auth.isAuthenticated()) {
            throw new IllegalStateException("No authenticated user was found!");
        }
        
        
        return (User)auth.getPrincipal();
    }
}
