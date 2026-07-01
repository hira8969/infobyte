package com.game.model;

public class RoundResult {
    private final int roundNumber;
    private final DifficultyLevel difficulty;
    private final int attemptsUsed;
    private final String result;
    private final int targetNumber;
    private final long durationSeconds;

    public RoundResult(int roundNumber, DifficultyLevel difficulty, int attemptsUsed, String result,
                       int targetNumber, long durationSeconds) {
        this.roundNumber = roundNumber;
        this.difficulty = difficulty;
        this.attemptsUsed = attemptsUsed;
        this.result = result;
        this.targetNumber = targetNumber;
        this.durationSeconds = durationSeconds;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public DifficultyLevel getDifficulty() {
        return difficulty;
    }

    public int getAttemptsUsed() {
        return attemptsUsed;
    }

    public String getResult() {
        return result;
    }

    public int getTargetNumber() {
        return targetNumber;
    }

    public long getDurationSeconds() {
        return durationSeconds;
    }
}
