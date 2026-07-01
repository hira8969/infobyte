package com.onlinereservation.controller;

import com.onlinereservation.model.Reservation;
import com.onlinereservation.service.ReservationService;
import com.onlinereservation.view.CancellationFrame;

import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.Optional;

public class CancellationController {
    private final CancellationFrame view;
    private final ReservationService reservationService = new ReservationService();
    private Reservation fetchedReservation;

    public CancellationController(CancellationFrame view) {
        this.view = view;
    }

    public void fetchDetails() {
        try {
            Optional<Reservation> reservation = reservationService.findReservationByPnr(view.getPnr());
            if (reservation.isPresent()) {
                fetchedReservation = reservation.get();
                view.showReservationDetails(fetchedReservation);
            } else {
                fetchedReservation = null;
                view.clearDetails();
                JOptionPane.showMessageDialog(view, "No reservation found for this PNR.", "Not Found", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException exception) {
            JOptionPane.showMessageDialog(view, exception.getMessage(), "Validation Error", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(view, "Unable to fetch reservation: " + exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cancelTicket() {
        if (fetchedReservation == null) {
            JOptionPane.showMessageDialog(view, "Fetch reservation details before cancellation.", "Missing Details", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int choice = JOptionPane.showConfirmDialog(view,
                "Are you sure you want to cancel this reservation?",
                "Confirm Cancellation",
                JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            try {
                if (reservationService.cancelReservation(fetchedReservation.getPnr())) {
                    JOptionPane.showMessageDialog(view, "Reservation Cancelled Successfully");
                    fetchedReservation = null;
                    view.clearForm();
                }
            } catch (SQLException exception) {
                JOptionPane.showMessageDialog(view, "Cancellation failed: " + exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
