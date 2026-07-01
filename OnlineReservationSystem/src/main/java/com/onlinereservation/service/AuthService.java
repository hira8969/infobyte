package com.onlinereservation.service;

import com.onlinereservation.dao.UserDAO;
import com.onlinereservation.util.ValidationUtil;

import java.sql.SQLException;

public class AuthService {
    private final UserDAO userDAO = new UserDAO();

    public boolean login(String username, String password) throws SQLException {
        if (ValidationUtil.isBlank(username)) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (ValidationUtil.isBlank(password)) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        return userDAO.validateLogin(username.trim(), password);
    }
}
