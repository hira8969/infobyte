package com.onlinereservation;

import com.onlinereservation.config.DatabaseConnection;
import com.onlinereservation.view.LoginFrame;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                DatabaseConnection.initializeDatabase();
                new LoginFrame().setVisible(true);
            } catch (SQLException exception) {
                JOptionPane.showMessageDialog(null,
                        "Database could not be started: " + exception.getMessage(),
                        "Startup Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null,
                        "Application could not be started: " + exception.getMessage(),
                        "Startup Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
