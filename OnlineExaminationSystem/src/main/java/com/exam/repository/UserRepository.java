package com.exam.repository;

import com.exam.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepository {
    private final Map<String, User> usersByUsername = new HashMap<>();

    public UserRepository() {
        save(new User(1, "student", "student123", "Student"));
    }

    public Optional<User> findByUsername(String username) {
        if (username == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(usersByUsername.get(username.toLowerCase()));
    }

    public void save(User user) {
        usersByUsername.put(user.getUsername().toLowerCase(), user);
    }
}
