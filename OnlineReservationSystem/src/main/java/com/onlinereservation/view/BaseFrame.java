package com.onlinereservation.view;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;

public abstract class BaseFrame extends JFrame {
    protected static final Color PRIMARY = new Color(36, 91, 166);
    protected static final Color BACKGROUND = new Color(245, 247, 251);
    protected static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 28);
    protected static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 15);

    protected BaseFrame(String title) {
        super(title);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
