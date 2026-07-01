package com.exam.view;

import com.exam.constants.ExamConstants;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private final JTextField usernameField = new JTextField(22);
    private final JPasswordField passwordField = new JPasswordField(22);
    private final JButton loginButton = new JButton("Login");
    private final JButton resetButton = new JButton("Reset");
    private final JButton exitButton = new JButton("Exit");

    public LoginFrame() {
        setTitle("Online Examination System - Login");
        setSize(ExamConstants.WINDOW_WIDTH, ExamConstants.WINDOW_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        buildUi();
    }

    private void buildUi() {
        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(AppTheme.BACKGROUND);
        JPanel form = AppTheme.surface();
        form.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = AppTheme.label("Online Examination System", AppTheme.TITLE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        form.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        form.add(AppTheme.label("Username", AppTheme.BODY), gbc);
        gbc.gridx = 1;
        form.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        form.add(AppTheme.label("Password", AppTheme.BODY), gbc);
        gbc.gridx = 1;
        form.add(passwordField, gbc);

        JPanel actions = new JPanel();
        actions.setOpaque(false);
        AppTheme.styleButton(loginButton);
        AppTheme.styleSecondaryButton(resetButton);
        AppTheme.styleDangerButton(exitButton);
        actions.add(loginButton);
        actions.add(resetButton);
        actions.add(exitButton);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        form.add(actions, gbc);

        root.add(form);
        add(root, BorderLayout.CENTER);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
        usernameField.requestFocusInWindow();
    }

    public void onLogin(ActionListener listener) {
        loginButton.addActionListener(listener);
        passwordField.addActionListener(listener);
    }

    public void onReset(ActionListener listener) {
        resetButton.addActionListener(listener);
    }

    public void onExit(ActionListener listener) {
        exitButton.addActionListener(listener);
    }
}
