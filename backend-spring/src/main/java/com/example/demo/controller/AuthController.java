package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.JwtService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody RegisterRequest request) {
        try {
            userService.saveUser(request.getLogin(), request.getPassword());
            String token = jwtService.generateToken(request.getLogin());
            return ResponseEntity.status(201).body(Map.of(
                    "token", token,
                    "login", request.getLogin()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("login", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            User existing = userService.findByLogin(request.getLogin());
            userService.checkPassword(request.getPassword(), existing.getPassword());
            String token = jwtService.generateToken(existing.getLogin());
            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "login", existing.getLogin()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(Map.of("password", e.getMessage()));
        }
    }
}