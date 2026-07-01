package com.game.service;

import com.game.constants.GameConstants;
import com.game.model.DifficultyLevel;
import com.game.model.Game;
import com.game.model.GameStatistics;
import com.game.model.GuessOutcome;
import com.game.model.GuessResult;
import com.game.model.LeaderboardEntry;
import com.game.model.RoundResult;
import com.game.util.RandomNumberGenerator;
import com.game.util.ValidationResult;
import com.game.util.ValidationUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

public class GameService {
    private static GameService instance;

    private final RandomNumberGenerator randomNumberGenerator;
    private final List<RoundResult> roundHistory;
    private final Map<String, LeaderboardEntry> leaderboard;
    private Game currentGame;
    private String playerName;

    private GameService(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
        this.roundHistory = new ArrayList<>();
        this.leaderboard = new LinkedHashMap<>();
        this.playerName = GameConstants.DEFAULT_PLAYER_NAME;
    }

    public static synchronized GameService getInstance() {
        if (instance == null) {
            instance = new GameService(new RandomNumberGenerator());
        }
        return instance;
    }

    public static GameService createForTesting(RandomNumberGenerator randomNumberGenerator) {
        return new GameService(randomNumberGenerator);
    }

    public Game startGame(String playerName, DifficultyLevel difficultyLevel) {
        this.playerName = sanitizePlayerName(playerName);
        int targetNumber = randomNumberGenerator.generate(difficultyLevel);
        currentGame = new Game(targetNumber, difficultyLevel);
        return currentGame;
    }

    public GuessResult submitGuess(String input) {
        ensureGameStarted();
        ValidationResult validationResult = ValidationUtil.validateGuess(input, currentGame.getDifficultyLevel());
        if (!validationResult.isValid()) {
            return new GuessResult(GuessOutcome.TOO_LOW, validationResult.getMessage(), false);
        }

        GuessOutcome outcome = currentGame.checkGuess(validationResult.getValue());
        String message = switch (outcome) {
            case CORRECT -> "Correct!";
            case TOO_HIGH -> "Too High!";
            case TOO_LOW -> "Too Low!";
            case GAME_OVER -> "You Lost!";
        };

        if (currentGame.isGameOver()) {
            storeCompletedRound();
        }

        return new GuessResult(outcome, message, currentGame.isGameOver());
    }

    private void storeCompletedRound() {
        String result = currentGame.getGameStatus().name();
        RoundResult roundResult = new RoundResult(
                roundHistory.size() + 1,
                currentGame.getDifficultyLevel(),
                currentGame.getCurrentAttempts(),
                result,
                currentGame.getTargetNumber(),
                currentGame.getDurationSeconds()
        );
        roundHistory.add(roundResult);

        if ("WON".equals(result)) {
            LeaderboardEntry entry = leaderboard.computeIfAbsent(playerName,
                    name -> new LeaderboardEntry(name, 0, 0));
            entry.recordWin(currentGame.getCurrentAttempts());
        }
    }

    public GameStatistics calculateStatistics() {
        int totalGames = roundHistory.size();
        int totalWins = (int) roundHistory.stream().filter(result -> "WON".equals(result.getResult())).count();
        int totalLosses = totalGames - totalWins;
        double winPercentage = totalGames == 0 ? 0 : (totalWins * 100.0) / totalGames;
        OptionalDouble averageAttempts = roundHistory.stream().mapToInt(RoundResult::getAttemptsUsed).average();
        int bestScore = roundHistory.stream()
                .filter(result -> "WON".equals(result.getResult()))
                .mapToInt(RoundResult::getAttemptsUsed)
                .min()
                .orElse(0);
        return new GameStatistics(totalGames, totalWins, totalLosses,
                winPercentage, averageAttempts.orElse(0), bestScore);
    }

    public List<RoundResult> getRoundHistory() {
        return List.copyOf(roundHistory);
    }

    public List<LeaderboardEntry> getLeaderboard() {
        return leaderboard.values().stream()
                .sorted(Comparator.comparingInt(LeaderboardEntry::getWins).reversed()
                        .thenComparingInt(entry -> entry.getBestScore() == 0 ? Integer.MAX_VALUE : entry.getBestScore()))
                .limit(10)
                .toList();
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public String getPlayerName() {
        return playerName;
    }

    private String sanitizePlayerName(String playerName) {
        if (playerName == null || playerName.trim().isEmpty()) {
            return GameConstants.DEFAULT_PLAYER_NAME;
        }
        return playerName.trim();
    }

    private void ensureGameStarted() {
        if (currentGame == null || currentGame.isGameOver()) {
            throw new IllegalStateException("Please start a new game first.");
        }
    }
}
