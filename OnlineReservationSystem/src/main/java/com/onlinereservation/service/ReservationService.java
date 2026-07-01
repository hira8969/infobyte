package com.onlinereservation.service;

import com.onlinereservation.dao.ReservationDAO;
import com.onlinereservation.dao.TrainDAO;
import com.onlinereservation.model.Reservation;
import com.onlinereservation.model.Train;
import com.onlinereservation.util.PNRGenerator;
import com.onlinereservation.util.ValidationUtil;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Optional;

public class ReservationService {
    private final ReservationDAO reservationDAO = new ReservationDAO();
    private final TrainDAO trainDAO = new TrainDAO();

    public Optional<Train> findTrain(String trainNoText) throws SQLException {
        if (!ValidationUtil.isNumeric(trainNoText)) {
            return Optional.empty();
        }
        return trainDAO.findByTrainNo(Integer.parseInt(trainNoText));
    }

    public Reservation bookReservation(Reservation reservation) throws SQLException {
        validateReservation(reservation);

        Optional<String> latestPnr = reservationDAO.findLatestPnrForYear(Year.now().getValue());
        reservation.setPnr(PNRGenerator.generateNextPnr(latestPnr));
        reservation.setBookingTime(LocalDateTime.now().toString());
        reservationDAO.save(reservation);
        return reservation;
    }

    public Optional<Reservation> findReservationByPnr(String pnr) throws SQLException {
        if (ValidationUtil.isBlank(pnr)) {
            throw new IllegalArgumentException("PNR number is required.");
        }
        return reservationDAO.findByPnr(pnr.trim().toUpperCase());
    }

    public boolean cancelReservation(String pnr) throws SQLException {
        if (ValidationUtil.isBlank(pnr)) {
            throw new IllegalArgumentException("PNR number is required.");
        }
        return reservationDAO.deleteByPnr(pnr.trim().toUpperCase());
    }

    private void validateReservation(Reservation reservation) throws SQLException {
        if (ValidationUtil.isBlank(reservation.getPassengerName())) {
            throw new IllegalArgumentException("Passenger name is required.");
        }
        if (reservation.getTrainNo() <= 0) {
            throw new IllegalArgumentException("Train number is required and must be numeric.");
        }
        if (ValidationUtil.isBlank(reservation.getTrainName())) {
            throw new IllegalArgumentException("Enter a valid train number to auto-fill train name.");
        }
        if (!ValidationUtil.isValidDate(reservation.getJourneyDate())) {
            throw new IllegalArgumentException("Journey date must be in YYYY-MM-DD format.");
        }
        if (ValidationUtil.isPastDate(reservation.getJourneyDate())) {
            throw new IllegalArgumentException("Journey date cannot be a previous date.");
        }
        if (ValidationUtil.isBlank(reservation.getSourceStation())) {
            throw new IllegalArgumentException("Source station is required.");
        }
        if (ValidationUtil.isBlank(reservation.getDestinationStation())) {
            throw new IllegalArgumentException("Destination station is required.");
        }
        if (reservation.getSourceStation().trim().equalsIgnoreCase(reservation.getDestinationStation().trim())) {
            throw new IllegalArgumentException("Source and destination cannot be the same.");
        }
        if (trainDAO.findByTrainNo(reservation.getTrainNo()).isEmpty()) {
            throw new IllegalArgumentException("Selected train number does not exist.");
        }
    }
}
