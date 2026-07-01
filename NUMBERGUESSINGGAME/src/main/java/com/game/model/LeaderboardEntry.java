package com.game.model;

public class LeaderboardEntry {
    private final String playerName;
    private int wins;
    private int bestScore;

    public LeaderboardEntry(String playerName, int wins, int bestScore) {
        this.playerName = playerName;
        this.wins = wins;
        this.bestScore = bestScore;
    }

    public void recordWin(int attemptsUsed) {
        wins++;
        if (bestScore == 0 || attemptsUsed < bestScore) {
            bestScore = attemptsUsed;
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getWins() {
        return wins;
    }

    public int getBestScore() {
        return bestScore;
    }
}
