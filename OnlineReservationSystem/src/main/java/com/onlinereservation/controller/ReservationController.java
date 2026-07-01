package com.onlinereservation.controller;

import com.onlinereservation.model.Reservation;
import com.onlinereservation.model.Train;
import com.onlinereservation.service.ReservationService;
import com.onlinereservation.view.ReservationFrame;

import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.Optional;

public class ReservationController {
    private final ReservationFrame view;
    private final ReservationService reservationService = new ReservationService();

    public ReservationController(ReservationFrame view) {
        this.view = view;
    }

    public void autoFillTrainName() {
        try {
            Optional<Train> train = reservationService.findTrain(view.getTrainNoText());
            view.setTrainName(train.map(Train::getTrainName).orElse(""));
        } catch (SQLException exception) {
            view.setTrainName("");
            JOptionPane.showMessageDialog(view, "Unable to fetch train: " + exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void bookTicket() {
        try {
            Reservation reservation = view.buildReservationFromForm();
            Reservation bookedReservation = reservationService.bookReservation(reservation);
            JOptionPane.showMessageDialog(view, view.formatReservationDetails(bookedReservation),
                    "Reservation Successful", JOptionPane.INFORMATION_MESSAGE);
            view.clearForm();
        } catch (IllegalArgumentException exception) {
            JOptionPane.showMessageDialog(view, exception.getMessage(), "Validation Error", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(view, "Reservation failed: " + exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
