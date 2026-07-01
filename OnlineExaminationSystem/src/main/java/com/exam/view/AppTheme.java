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
    static final Color SUCCESS = new Color(22, 128, 84);
    static final Color WARNING = new Color(194, 105, 0);
    static final Color DANGER = new Color(190, 48, 48);
    static final Color SECONDARY = new Color(226, 232, 240);
    static final Color BACKGROUND = new Color(246, 248, 252);
    static final Color SURFACE = Color.WHITE;
    static final Color TEXT = new Color(28, 35, 48);
    static final Font TITLE = new Font("Segoe UI", Font.BOLD, 30);
    static final Font HEADING = new Font("Segoe UI", Font.BOLD, 20);
    static final Font BODY = new Font("Segoe UI", Font.PLAIN, 15);

    private AppTheme() {
    }

    static void styleButton(JButton button) {
        styleSolidButton(button, PRIMARY, Color.WHITE);
    }

    static void styleSecondaryButton(JButton button) {
        styleSolidButton(button, SECONDARY, TEXT);
    }

    static void styleSuccessButton(JButton button) {
        styleSolidButton(button, SUCCESS, Color.WHITE);
    }

    static void styleWarningButton(JButton button) {
        styleSolidButton(button, WARNING, Color.WHITE);
    }

    static void styleDangerButton(JButton button) {
        styleSolidButton(button, DANGER, Color.WHITE);
    }

    private static void styleSolidButton(JButton button, Color background, Color foreground) {
        button.setFont(BODY);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setBorder(BorderFactory.createEmptyBorder(11, 20, 11, 20));
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
