package com.exam.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

public class ProfilePanel extends JPanel {
    private final JTextField displayNameField = new JTextField(28);
    private final JPasswordField passwordField = new JPasswordField(28);
    private final JButton saveButton = new JButton("Save");
    private final JButton cancelButton = new JButton("Cancel");

    public ProfilePanel() {
        setBackground(AppTheme.BACKGROUND);
        setLayout(new GridBagLayout());
        buildUi();
    }

    private void buildUi() {
        JPanel form = AppTheme.surface();
        form.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        form.add(AppTheme.label("Update Profile", AppTheme.TITLE), gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        form.add(new JLabel("Display Name"), gbc);
        gbc.gridx = 1;
        form.add(displayNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        form.add(new JLabel("Password"), gbc);
        gbc.gridx = 1;
        form.add(passwordField, gbc);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.setOpaque(false);
        AppTheme.styleButton(saveButton);
        AppTheme.styleSecondaryButton(cancelButton);
        actions.add(cancelButton);
        actions.add(saveButton);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        form.add(actions, gbc);
        add(form);
    }

    public void setProfile(String displayName, String password) {
        displayNameField.setText(displayName);
        passwordField.setText(password);
    }

    public String getDisplayName() {
        return displayNameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void onSave(ActionListener listener) {
        saveButton.addActionListener(listener);
    }

    public void onCancel(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }
}
