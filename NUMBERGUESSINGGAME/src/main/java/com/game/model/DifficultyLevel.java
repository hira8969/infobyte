package com.game.model;

public enum DifficultyLevel {
    EASY("Easy", 1, 50, 10),
    MEDIUM("Medium", 1, 100, 7),
    HARD("Hard", 1, 200, 5);

    private final String displayName;
    private final int minimumNumber;
    private final int maximumNumber;
    private final int maxAttempts;

    DifficultyLevel(String displayName, int minimumNumber, int maximumNumber, int maxAttempts) {
        this.displayName = displayName;
        this.minimumNumber = minimumNumber;
        this.maximumNumber = maximumNumber;
        this.maxAttempts = maxAttempts;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getMinimumNumber() {
        return minimumNumber;
    }

    public int getMaximumNumber() {
        return maximumNumber;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public String getRangeText() {
        return minimumNumber + " - " + maximumNumber;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
