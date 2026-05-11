package com.example.demo.dto;

import jakarta.validation.constraints.*;

public class RegisterRequest {

    @Email(message = "Please provide a valid email")
    @NotBlank(message = "Email is required")
    private String login;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}