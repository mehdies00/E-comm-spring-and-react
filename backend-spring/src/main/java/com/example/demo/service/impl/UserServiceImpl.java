package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
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
    public User findByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new IllegalArgumentException("No account found with this email"));
    }

    @Override
    public boolean checkPassword(String raw, String encoded) {
        if (!BCrypt.checkpw(raw, encoded)) {
            throw new IllegalArgumentException("Invalid password");
        }
        return true;
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
    public User saveUser(String login, String password) {
        if (userRepository.findByLogin(login).isPresent()) {
            throw new IllegalArgumentException("Login already taken");
        }
        User user = new User();
        user.setLogin(login);
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        user.setConnectionNumber(0);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(Integer id, User userDetails) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setLogin(userDetails.getLogin());

        if (userDetails.getPassword() != null && !userDetails.getPassword().isBlank()) {
            existingUser.setPassword(
                    BCrypt.hashpw(userDetails.getPassword(), BCrypt.gensalt()));
        }

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