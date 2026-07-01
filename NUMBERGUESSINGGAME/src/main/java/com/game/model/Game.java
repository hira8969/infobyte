package com.game.model;

public class Game {
    private int targetNumber;
    private int currentAttempts;
    private int maxAttempts;
    private GameStatus gameStatus;
    private DifficultyLevel difficultyLevel;
    private long startTimeMillis;
    private long endTimeMillis;

    public Game(int targetNumber, DifficultyLevel difficultyLevel) {
        this.targetNumber = targetNumber;
        this.difficultyLevel = difficultyLevel;
        this.maxAttempts = difficultyLevel.getMaxAttempts();
        this.currentAttempts = 0;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.startTimeMillis = System.currentTimeMillis();
    }

    public GuessOutcome checkGuess(int guess) {
        incrementAttempts();
        if (guess == targetNumber) {
            gameStatus = GameStatus.WON;
            endTimeMillis = System.currentTimeMillis();
            return GuessOutcome.CORRECT;
        }
        if (currentAttempts >= maxAttempts) {
            gameStatus = GameStatus.LOST;
            endTimeMillis = System.currentTimeMillis();
            return GuessOutcome.GAME_OVER;
        }
        return guess < targetNumber ? GuessOutcome.TOO_LOW : GuessOutcome.TOO_HIGH;
    }

    public void incrementAttempts() {
        currentAttempts++;
    }

    public boolean isGameOver() {
        return gameStatus == GameStatus.WON || gameStatus == GameStatus.LOST;
    }

    public long getDurationSeconds() {
        long effectiveEnd = endTimeMillis == 0 ? System.currentTimeMillis() : endTimeMillis;
        return Math.max(0, (effectiveEnd - startTimeMillis) / 1000);
    }

    public int getRemainingAttempts() {
        return Math.max(0, maxAttempts - currentAttempts);
    }

    public int getTargetNumber() {
        return targetNumber;
    }

    public void setTargetNumber(int targetNumber) {
        this.targetNumber = targetNumber;
    }

    public int getCurrentAttempts() {
        return currentAttempts;
    }

    public void setCurrentAttempts(int currentAttempts) {
        this.currentAttempts = currentAttempts;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
