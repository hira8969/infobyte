package com.game.model;

public class GameStatistics {
    private final int totalGamesPlayed;
    private final int totalWins;
    private final int totalLosses;
    private final double winPercentage;
    private final double averageAttempts;
    private final int bestScore;

    public GameStatistics(int totalGamesPlayed, int totalWins, int totalLosses,
                          double winPercentage, double averageAttempts, int bestScore) {
        this.totalGamesPlayed = totalGamesPlayed;
        this.totalWins = totalWins;
        this.totalLosses = totalLosses;
        this.winPercentage = winPercentage;
        this.averageAttempts = averageAttempts;
        this.bestScore = bestScore;
    }

    public int getTotalGamesPlayed() {
        return totalGamesPlayed;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public int getTotalLosses() {
        return totalLosses;
    }

    public double getWinPercentage() {
        return winPercentage;
    }

    public double getAverageAttempts() {
        return averageAttempts;
    }

    public int getBestScore() {
        return bestScore;
    }
}
