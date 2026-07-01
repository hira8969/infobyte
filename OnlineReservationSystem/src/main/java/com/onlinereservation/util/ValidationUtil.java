package com.onlinereservation.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public final class ValidationUtil {
    private ValidationUtil() {
    }

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isNumeric(String value) {
        return value != null && value.matches("\\d+");
    }

    public static boolean isValidDate(String value) {
        if (isBlank(value)) {
            return false;
        }
        try {
            LocalDate.parse(value);
            return true;
        } catch (DateTimeParseException exception) {
            return false;
        }
    }

    public static boolean isPastDate(String value) {
        return LocalDate.parse(value).isBefore(LocalDate.now());
    }
}
