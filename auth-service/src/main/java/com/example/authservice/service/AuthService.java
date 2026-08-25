package com.example.authservice.service;

import com.example.authservice.controller.AuthController;
import com.example.authservice.dto.LoginRequestDTO;
import com.example.authservice.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserService userService;
    public AuthService(UserService userService){
        this.userService = userService;
    }

    public Optional<String > authenticate(LoginRequestDTO loginRequestDTO){
        Optional<User> User = userService.findByEmail(loginRequestDTO.getEmail());
    }



}
