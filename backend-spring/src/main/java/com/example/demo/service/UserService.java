package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(Integer id);

    User saveUser(User user);

    User updateUser(Integer id, User userDetails);

    void deleteUser(Integer id);
}