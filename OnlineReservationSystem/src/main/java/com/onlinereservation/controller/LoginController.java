package com.onlinereservation.controller;

import com.onlinereservation.service.AuthService;
import com.onlinereservation.view.DashboardFrame;
import com.onlinereservation.view.LoginFrame;

import javax.swing.JOptionPane;
import java.sql.SQLException;

public class LoginController {
    private final LoginFrame view;
    private final AuthService authService = new AuthService();

    public LoginController(LoginFrame view) {
        this.view = view;
    }

    public void login() {
        try {
            boolean validUser = authService.login(view.getUsername(), view.getPassword());
            if (validUser) {
                JOptionPane.showMessageDialog(view, "Login successful.");
                new DashboardFrame().setVisible(true);
                view.dispose();
            } else {
                JOptionPane.showMessageDialog(view, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException exception) {
            JOptionPane.showMessageDialog(view, exception.getMessage(), "Validation Error", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(view, "Database error: " + exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
