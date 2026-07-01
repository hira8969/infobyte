package com.game.util;

import com.game.model.DifficultyLevel;

public final class ValidationUtil {
    private ValidationUtil() {
    }

    public static ValidationResult validateGuess(String input, DifficultyLevel difficultyLevel) {
        if (input == null || input.trim().isEmpty()) {
            return ValidationResult.invalid("Please enter a number.");
        }

        String trimmedInput = input.trim();
        if (!trimmedInput.matches("\\d+")) {
            return ValidationResult.invalid("Only numeric input is allowed.");
        }

        try {
            int number = Integer.parseInt(trimmedInput);
            if (number < difficultyLevel.getMinimumNumber() || number > difficultyLevel.getMaximumNumber()) {
                return ValidationResult.invalid("Please enter a number between "
                        + difficultyLevel.getMinimumNumber() + " and " + difficultyLevel.getMaximumNumber() + ".");
            }
            return ValidationResult.valid(number);
        } catch (NumberFormatException exception) {
            return ValidationResult.invalid("The entered number is too large.");
        }
    }
}
