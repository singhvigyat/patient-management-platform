package com.example.authservice.service;

import com.example.authservice.controller.AuthController;
import com.example.authservice.dto.LoginRequestDTO;
import com.example.authservice.model.User;
import com.example.authservice.util.JwtUtil;
import io.jsonwebtoken.Jwt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public Optional<String > authenticate(LoginRequestDTO loginRequestDTO){
        Optional<String> token = userService
                .findByEmail(loginRequestDTO.getEmail())
                .filter(u -> passwordEncoder.matches(loginRequestDTO.getPassword(), u.getPassword()))
                .map(u->jwtUtil.generateToken(u.getEmail(), u.getRole()));

        return token;
    }



}
