package com.atm.util;

import java.math.BigDecimal;

public final class ValidationUtil {
    private ValidationUtil() {
    }

    public static boolean isNumeric(String value) {
        return value != null && value.matches("\\d+");
    }

    public static boolean isValidUserId(String userId) {
        return isNumeric(userId);
    }

    public static boolean isValidPin(String pin) {
        return pin != null && pin.matches("\\d{4}");
    }

    public static boolean isPositiveAmount(BigDecimal amount) {
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
    }
}
