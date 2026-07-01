package com.onlinereservation.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class DashboardFrame extends BaseFrame {
    public DashboardFrame() {
        super("Online Reservation System - Dashboard");
        buildUi();
    }

    private void buildUi() {
        JPanel root = new JPanel(new BorderLayout(20, 30));
        root.setBackground(BACKGROUND);
        root.setBorder(new EmptyBorder(80, 220, 120, 220));

        JLabel title = new JLabel("Reservation Dashboard", SwingConstants.CENTER);
        title.setFont(TITLE_FONT);
        title.setForeground(PRIMARY);
        root.add(title, BorderLayout.NORTH);

        JPanel actions = new JPanel(new GridLayout(3, 1, 0, 20));
        actions.setBackground(BACKGROUND);

        JButton reservationButton = new JButton("Book New Ticket");
        JButton cancellationButton = new JButton("Cancel Reservation");
        JButton logoutButton = new JButton("Logout");

        reservationButton.addActionListener(event -> {
            new ReservationFrame(this).setVisible(true);
            setVisible(false);
        });
        cancellationButton.addActionListener(event -> {
            new CancellationFrame(this).setVisible(true);
            setVisible(false);
        });
        logoutButton.addActionListener(event -> {
            new LoginFrame().setVisible(true);
            dispose();
        });

        actions.add(reservationButton);
        actions.add(cancellationButton);
        actions.add(logoutButton);
        root.add(actions, BorderLayout.CENTER);
        setContentPane(root);
    }
}
