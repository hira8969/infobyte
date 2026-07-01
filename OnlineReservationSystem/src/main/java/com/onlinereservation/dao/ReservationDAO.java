package com.onlinereservation.dao;

import com.onlinereservation.config.DatabaseConnection;
import com.onlinereservation.model.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ReservationDAO {
    public void save(Reservation reservation) throws SQLException {
        String sql = """
                INSERT INTO reservations
                (pnr, passenger_name, train_no, train_name, class_type, journey_date,
                 source_station, destination_station, booking_time)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, reservation.getPnr());
            statement.setString(2, reservation.getPassengerName());
            statement.setInt(3, reservation.getTrainNo());
            statement.setString(4, reservation.getTrainName());
            statement.setString(5, reservation.getClassType());
            statement.setString(6, reservation.getJourneyDate());
            statement.setString(7, reservation.getSourceStation());
            statement.setString(8, reservation.getDestinationStation());
            statement.setString(9, reservation.getBookingTime());
            statement.executeUpdate();
        }
    }

    public Optional<Reservation> findByPnr(String pnr) throws SQLException {
        String sql = "SELECT * FROM reservations WHERE pnr = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pnr);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapReservation(resultSet));
                }
                return Optional.empty();
            }
        }
    }

    public boolean deleteByPnr(String pnr) throws SQLException {
        String sql = "DELETE FROM reservations WHERE pnr = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pnr);
            return statement.executeUpdate() > 0;
        }
    }

    public Optional<String> findLatestPnrForYear(int year) throws SQLException {
        String sql = "SELECT pnr FROM reservations WHERE pnr LIKE ? ORDER BY pnr DESC LIMIT 1";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "PNR" + year + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(resultSet.getString("pnr"));
                }
                return Optional.empty();
            }
        }
    }

    private Reservation mapReservation(ResultSet resultSet) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId(resultSet.getInt("id"));
        reservation.setPnr(resultSet.getString("pnr"));
        reservation.setPassengerName(resultSet.getString("passenger_name"));
        reservation.setTrainNo(resultSet.getInt("train_no"));
        reservation.setTrainName(resultSet.getString("train_name"));
        reservation.setClassType(resultSet.getString("class_type"));
        reservation.setJourneyDate(resultSet.getString("journey_date"));
        reservation.setSourceStation(resultSet.getString("source_station"));
        reservation.setDestinationStation(resultSet.getString("destination_station"));
        reservation.setBookingTime(resultSet.getString("booking_time"));
        return reservation;
    }
}
