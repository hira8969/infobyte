package com.onlinereservation.view;

import com.onlinereservation.controller.LoginController;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class LoginFrame extends BaseFrame {
    private final JTextField usernameField = new JTextField(20);
    private final JPasswordField passwordField = new JPasswordField(20);
    private final LoginController controller;

    public LoginFrame() {
        super("Online Reservation System - Login");
        controller = new LoginController(this);
        buildUi();
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    private void buildUi() {
        JPanel root = new JPanel(new BorderLayout(20, 20));
        root.setBackground(BACKGROUND);
        root.setBorder(new EmptyBorder(80, 280, 100, 280));

        JLabel title = new JLabel("Online Reservation System", SwingConstants.CENTER);
        title.setFont(TITLE_FONT);
        title.setForeground(PRIMARY);
        root.add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(BACKGROUND);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 8, 12, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addLabel(formPanel, "Username", gbc, 0);
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        addLabel(formPanel, "Password", gbc, 1);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        JButton resetButton = new JButton("Reset");
        JButton exitButton = new JButton("Exit");

        loginButton.addActionListener(event -> controller.login());
        resetButton.addActionListener(event -> {
            usernameField.setText("");
            passwordField.setText("");
        });
        exitButton.addActionListener(event -> System.exit(0));

        gbc.gridy = 2;
        gbc.gridx = 0;
        formPanel.add(loginButton, gbc);
        gbc.gridx = 1;
        formPanel.add(resetButton, gbc);
        gbc.gridx = 2;
        formPanel.add(exitButton, gbc);

        root.add(formPanel, BorderLayout.CENTER);
        setContentPane(root);
    }

    private void addLabel(JPanel panel, String text, GridBagConstraints gbc, int row) {
        JLabel label = new JLabel(text);
        label.setFont(LABEL_FONT);
        gbc.gridy = row;
        gbc.gridx = 0;
        panel.add(label, gbc);
    }
}
