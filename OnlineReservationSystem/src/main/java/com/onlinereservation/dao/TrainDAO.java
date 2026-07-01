package com.onlinereservation.dao;

import com.onlinereservation.config.DatabaseConnection;
import com.onlinereservation.model.Train;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TrainDAO {
    public Optional<Train> findByTrainNo(int trainNo) throws SQLException {
        String sql = "SELECT train_no, train_name FROM trains WHERE train_no = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, trainNo);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new Train(
                            resultSet.getInt("train_no"),
                            resultSet.getString("train_name")));
                }
                return Optional.empty();
            }
        }
    }
}
