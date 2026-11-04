package com.example.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtService;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String register(User user) {

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        user.setPassword(
            passwordEncoder.encode(user.getPassword())
        );

        // Every public registration gets USER role.
        user.setRole("USER");

        userRepository.save(user);

        return "User registered successfully";
    }

    public String login(User user) {

        User existingUser = userRepository
                .findByUsername(user.getUsername())
                .orElseThrow(() ->
                    new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(
                user.getPassword(),
                existingUser.getPassword())) {

            throw new RuntimeException("Invalid username or password");
        }

        return jwtService.generateToken(existingUser.getUsername());
    }
}