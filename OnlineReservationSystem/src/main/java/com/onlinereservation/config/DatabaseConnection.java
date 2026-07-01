package com.onlinereservation.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class DatabaseConnection {
    private static final String DATABASE_DIRECTORY = "data";
    private static final String DATABASE_URL = "jdbc:sqlite:" + DATABASE_DIRECTORY + "/online_reservation.db";
    private static boolean initialized;

    private DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }

    public static synchronized void initializeDatabase() throws SQLException {
        if (initialized) {
            return;
        }

        try {
            Files.createDirectories(Path.of(DATABASE_DIRECTORY));
            executeSqlResource("schema.sql");
            executeSqlResource("sample_data.sql");
            initialized = true;
        } catch (IOException exception) {
            throw new SQLException("Unable to prepare database files.", exception);
        }
    }

    private static void executeSqlResource(String resourceName) throws SQLException, IOException {
        try (InputStream inputStream = DatabaseConnection.class.getClassLoader().getResourceAsStream(resourceName)) {
            if (inputStream == null) {
                throw new IOException("Missing resource: " + resourceName);
            }

            String sql = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
                for (String query : sql.split(";")) {
                    String trimmedQuery = query.trim();
                    if (!trimmedQuery.isEmpty()) {
                        statement.execute(trimmedQuery);
                    }
                }
            }
        }
    }
}
