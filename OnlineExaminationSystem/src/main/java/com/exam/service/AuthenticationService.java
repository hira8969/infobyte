package com.exam.service;

import com.exam.model.User;
import com.exam.repository.UserRepository;
import com.exam.util.SessionManager;
import com.exam.util.ValidationUtil;

import java.util.Optional;

public class AuthenticationService {
    private final UserRepository userRepository;
    private final SessionManager sessionManager;

    public AuthenticationService(UserRepository userRepository, SessionManager sessionManager) {
        this.userRepository = userRepository;
        this.sessionManager = sessionManager;
    }

    public User login(String username, String password) {
        if (ValidationUtil.isBlank(username)) {
            throw new IllegalArgumentException("Username is required.");
        }
        if (ValidationUtil.isBlank(password)) {
            throw new IllegalArgumentException("Password is required.");
        }
        Optional<User> user = userRepository.findByUsername(username.trim());
        if (user.isEmpty() || !user.get().getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid username or password.");
        }
        sessionManager.setLoggedInUser(user.get());
        return user.get();
    }

    public void updateProfile(String displayName, String password) {
        User user = requireLoggedInUser();
        if (ValidationUtil.isBlank(displayName)) {
            throw new IllegalArgumentException("Display name cannot be empty.");
        }
        if (!ValidationUtil.isPasswordValid(password)) {
            throw new IllegalArgumentException("Password must contain at least 6 characters.");
        }
        user.setDisplayName(displayName.trim());
        user.setPassword(password);
        userRepository.save(user);
    }

    public void logout() {
        sessionManager.logout();
    }

    public User requireLoggedInUser() {
        if (!sessionManager.isLoggedIn()) {
            throw new IllegalStateException("Please login to continue.");
        }
        return sessionManager.getLoggedInUser();
    }
}
