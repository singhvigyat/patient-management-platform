package com.example.authservice.repository;

import com.example.authservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
        // then JPA will handle the SQL Query based on the findBy provided by us
        // & based on Email provided by us (in findBy{}).
        Optional<User> findByEmail(String email);

}
