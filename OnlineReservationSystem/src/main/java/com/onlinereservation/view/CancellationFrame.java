package com.onlinereservation.view;

import com.onlinereservation.controller.CancellationController;
import com.onlinereservation.model.Reservation;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class CancellationFrame extends BaseFrame {
    private final DashboardFrame dashboardFrame;
    private final JTextField pnrField = new JTextField(24);
    private final JTextArea detailsArea = new JTextArea(10, 35);
    private final CancellationController controller;

    public CancellationFrame(DashboardFrame dashboardFrame) {
        super("Online Reservation System - Cancel Ticket");
        this.dashboardFrame = dashboardFrame;
        this.controller = new CancellationController(this);
        buildUi();
    }

    public String getPnr() {
        return pnrField.getText();
    }

    public void showReservationDetails(Reservation reservation) {
        detailsArea.setText("""
                Passenger Name: %s
                Train Number: %d
                Train Name: %s
                Date: %s
                Source: %s
                Destination: %s
                Class: %s
                """.formatted(
                reservation.getPassengerName(),
                reservation.getTrainNo(),
                reservation.getTrainName(),
                reservation.getJourneyDate(),
                reservation.getSourceStation(),
                reservation.getDestinationStation(),
                reservation.getClassType()));
    }

    public void clearDetails() {
        detailsArea.setText("");
    }

    public void clearForm() {
        pnrField.setText("");
        clearDetails();
    }

    private void buildUi() {
        JPanel root = new JPanel(new BorderLayout(20, 20));
        root.setBackground(BACKGROUND);
        root.setBorder(new EmptyBorder(40, 180, 80, 180));

        JLabel title = new JLabel("Cancel Reservation", SwingConstants.CENTER);
        title.setFont(TITLE_FONT);
        title.setForeground(PRIMARY);
        root.add(title, BorderLayout.NORTH);

        detailsArea.setEditable(false);
        detailsArea.setFont(LABEL_FONT);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(BACKGROUND);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 8, 10, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 0;
        gbc.gridx = 0;
        form.add(new JLabel("PNR Number"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        form.add(pnrField, gbc);
        gbc.gridwidth = 1;

        JButton fetchButton = new JButton("Fetch Details");
        JButton cancelButton = new JButton("Cancel Ticket");
        JButton backButton = new JButton("Back");

        fetchButton.addActionListener(event -> controller.fetchDetails());
        cancelButton.addActionListener(event -> controller.cancelTicket());
        backButton.addActionListener(event -> goBack());

        gbc.gridy = 1;
        gbc.gridx = 0;
        form.add(fetchButton, gbc);
        gbc.gridx = 1;
        form.add(cancelButton, gbc);
        gbc.gridx = 2;
        form.add(backButton, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        form.add(detailsArea, gbc);

        root.add(form, BorderLayout.CENTER);
        setContentPane(root);
    }

    private void goBack() {
        dashboardFrame.setVisible(true);
        dispose();
    }
}
