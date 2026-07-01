package com.exam.util;

public final class ValidationUtil {
    private ValidationUtil() {
    }

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isPasswordValid(String password) {
        return password != null && password.length() >= 6;
    }
}
