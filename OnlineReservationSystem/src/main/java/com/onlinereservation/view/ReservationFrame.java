package com.onlinereservation.view;

import com.onlinereservation.controller.ReservationController;
import com.onlinereservation.model.Reservation;
import com.onlinereservation.util.ValidationUtil;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class ReservationFrame extends BaseFrame {
    private final DashboardFrame dashboardFrame;
    private final JTextField passengerNameField = new JTextField(20);
    private final JTextField trainNoField = new JTextField(20);
    private final JTextField trainNameField = new JTextField(20);
    private final JComboBox<String> classTypeBox = new JComboBox<>(new String[]{"Sleeper", "AC", "First Class", "Second Class"});
    private final JTextField journeyDateField = new JTextField("2026-07-01", 20);
    private final JTextField sourceField = new JTextField(20);
    private final JTextField destinationField = new JTextField(20);
    private final ReservationController controller;

    public ReservationFrame(DashboardFrame dashboardFrame) {
        super("Online Reservation System - Book Ticket");
        this.dashboardFrame = dashboardFrame;
        this.controller = new ReservationController(this);
        buildUi();
    }

    public String getTrainNoText() {
        return trainNoField.getText().trim();
    }

    public void setTrainName(String trainName) {
        trainNameField.setText(trainName);
    }

    public Reservation buildReservationFromForm() {
        Reservation reservation = new Reservation();
        reservation.setPassengerName(passengerNameField.getText().trim());
        reservation.setTrainNo(ValidationUtil.isNumeric(trainNoField.getText().trim()) ? Integer.parseInt(trainNoField.getText().trim()) : 0);
        reservation.setTrainName(trainNameField.getText().trim());
        reservation.setClassType((String) classTypeBox.getSelectedItem());
        reservation.setJourneyDate(journeyDateField.getText().trim());
        reservation.setSourceStation(sourceField.getText().trim());
        reservation.setDestinationStation(destinationField.getText().trim());
        return reservation;
    }

    public void clearForm() {
        passengerNameField.setText("");
        trainNoField.setText("");
        trainNameField.setText("");
        classTypeBox.setSelectedIndex(0);
        journeyDateField.setText("");
        sourceField.setText("");
        destinationField.setText("");
    }

    public String formatReservationDetails(Reservation reservation) {
        return """
                PNR Number: %s
                Passenger Name: %s
                Train Number: %d
                Train Name: %s
                Journey Date: %s
                Source: %s
                Destination: %s
                Class Type: %s
                """.formatted(
                reservation.getPnr(),
                reservation.getPassengerName(),
                reservation.getTrainNo(),
                reservation.getTrainName(),
                reservation.getJourneyDate(),
                reservation.getSourceStation(),
                reservation.getDestinationStation(),
                reservation.getClassType());
    }

    private void buildUi() {
        JPanel root = new JPanel(new BorderLayout(20, 20));
        root.setBackground(BACKGROUND);
        root.setBorder(new EmptyBorder(40, 160, 60, 160));

        JLabel title = new JLabel("Book Ticket", SwingConstants.CENTER);
        title.setFont(TITLE_FONT);
        title.setForeground(PRIMARY);
        root.add(title, BorderLayout.NORTH);

        trainNameField.setEditable(false);
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(BACKGROUND);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 8, 10, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addRow(form, gbc, 0, "Passenger Name", passengerNameField);
        addRow(form, gbc, 1, "Train Number", trainNoField);
        addRow(form, gbc, 2, "Train Name", trainNameField);
        addRow(form, gbc, 3, "Class Type", classTypeBox);
        addRow(form, gbc, 4, "Date of Journey (YYYY-MM-DD)", journeyDateField);
        addRow(form, gbc, 5, "Source Station", sourceField);
        addRow(form, gbc, 6, "Destination Station", destinationField);

        JButton bookButton = new JButton("Book Ticket");
        JButton clearButton = new JButton("Clear");
        JButton backButton = new JButton("Back");

        bookButton.addActionListener(event -> controller.bookTicket());
        clearButton.addActionListener(event -> clearForm());
        backButton.addActionListener(event -> goBack());

        gbc.gridy = 7;
        gbc.gridx = 0;
        form.add(bookButton, gbc);
        gbc.gridx = 1;
        form.add(clearButton, gbc);
        gbc.gridx = 2;
        form.add(backButton, gbc);

        trainNoField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent event) {
                controller.autoFillTrainName();
            }

            public void removeUpdate(DocumentEvent event) {
                controller.autoFillTrainName();
            }

            public void changedUpdate(DocumentEvent event) {
                controller.autoFillTrainName();
            }
        });

        root.add(form, BorderLayout.CENTER);
        setContentPane(root);
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, int row, String labelText, java.awt.Component component) {
        JLabel label = new JLabel(labelText);
        label.setFont(LABEL_FONT);
        gbc.gridy = row;
        gbc.gridx = 0;
        panel.add(label, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        panel.add(component, gbc);
        gbc.gridwidth = 1;
    }

    private void goBack() {
        dashboardFrame.setVisible(true);
        dispose();
    }
}
