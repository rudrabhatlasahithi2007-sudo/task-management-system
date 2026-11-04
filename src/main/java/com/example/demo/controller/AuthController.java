package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.service.AuthService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody User user) {

        String message = authService.register(user);

        return Map.of("message", message);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {

        String token = authService.login(user);

        return Map.of("token", token);
    }
}