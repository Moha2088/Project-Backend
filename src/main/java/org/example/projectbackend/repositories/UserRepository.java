package org.example.projectbackend.repositories;

import org.example.projectbackend.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { }