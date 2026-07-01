package com.exam.view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;

final class AppTheme {
    static final Color PRIMARY = new Color(34, 89, 172);
    static final Color BACKGROUND = new Color(246, 248, 252);
    static final Color SURFACE = Color.WHITE;
    static final Color TEXT = new Color(28, 35, 48);
    static final Font TITLE = new Font("Segoe UI", Font.BOLD, 30);
    static final Font HEADING = new Font("Segoe UI", Font.BOLD, 20);
    static final Font BODY = new Font("Segoe UI", Font.PLAIN, 15);

    private AppTheme() {
    }

    static void styleButton(JButton button) {
        button.setFont(BODY);
        button.setFocusPainted(false);
        button.setBackground(PRIMARY);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
    }

    static void styleSecondaryButton(JButton button) {
        button.setFont(BODY);
        button.setFocusPainted(false);
        button.setBackground(new Color(226, 232, 240));
        button.setForeground(TEXT);
        button.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
    }

    static JPanel surface() {
        JPanel panel = new JPanel();
        panel.setBackground(SURFACE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240)),
                BorderFactory.createEmptyBorder(24, 28, 24, 28)));
        return panel;
    }

    static JLabel label(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(TEXT);
        return label;
    }

    static void pad(JComponent component, int top, int left, int bottom, int right) {
        component.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
    }
}
