package com.example.demo.dto;

import jakarta.validation.constraints.*;

public class LoginRequest {

    @NotBlank(message = "Email is required")
    private String login;

    @NotBlank(message = "Password is required")
    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}