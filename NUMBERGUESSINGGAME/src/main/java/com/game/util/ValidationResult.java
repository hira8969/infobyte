package com.game.util;

public class ValidationResult {
    private final boolean valid;
    private final int value;
    private final String message;

    private ValidationResult(boolean valid, int value, String message) {
        this.valid = valid;
        this.value = value;
        this.message = message;
    }

    public static ValidationResult valid(int value) {
        return new ValidationResult(true, value, "");
    }

    public static ValidationResult invalid(String message) {
        return new ValidationResult(false, 0, message);
    }

    public boolean isValid() {
        return valid;
    }

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
