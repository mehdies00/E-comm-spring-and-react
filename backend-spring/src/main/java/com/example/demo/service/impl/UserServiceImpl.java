package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        if (user.getLogin() == null || user.getLogin().trim().isEmpty()) {
            throw new IllegalArgumentException("Login cannot be empty");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (user.getConnectionNumber() == null || user.getConnectionNumber() < 0) {
            throw new IllegalArgumentException("Connection number cannot be null or negative");
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(Integer id, User userDetails) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found for ID: " + id));

        if (userDetails.getLogin() == null || userDetails.getLogin().trim().isEmpty()) {
            throw new IllegalArgumentException("Login cannot be empty");
        }
        if (userDetails.getPassword() == null || userDetails.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (userDetails.getConnectionNumber() == null || userDetails.getConnectionNumber() < 0) {
            throw new IllegalArgumentException("Connection number cannot be null or negative");
        }

        existingUser.setLogin(userDetails.getLogin());

        existingUser.setPassword(userDetails.getPassword());
        existingUser.setConnectionNumber(userDetails.getConnectionNumber());

        return userRepository.save(existingUser);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found for ID: " + id);
        }
        userRepository.deleteById(id);
    }
}