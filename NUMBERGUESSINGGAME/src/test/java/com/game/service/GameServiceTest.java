package com.game.service;

import com.game.model.DifficultyLevel;
import com.game.model.GameStatistics;
import com.game.model.GuessOutcome;
import com.game.model.GuessResult;
import com.game.util.RandomNumberGenerator;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameServiceTest {
    @Test
    void correctGuessWinsRound() {
        GameService service = fixedTargetService(25);
        service.startGame("Asha", DifficultyLevel.EASY);

        GuessResult result = service.submitGuess("25");

        assertEquals(GuessOutcome.CORRECT, result.getOutcome());
        assertTrue(result.isRoundComplete());
        assertEquals(1, service.calculateStatistics().getTotalWins());
    }

    @Test
    void higherGuessReturnsTooHigh() {
        GameService service = fixedTargetService(25);
        service.startGame("Asha", DifficultyLevel.EASY);

        GuessResult result = service.submitGuess("40");

        assertEquals(GuessOutcome.TOO_HIGH, result.getOutcome());
        assertFalse(result.isRoundComplete());
    }

    @Test
    void lowerGuessReturnsTooLow() {
        GameService service = fixedTargetService(25);
        service.startGame("Asha", DifficultyLevel.EASY);

        GuessResult result = service.submitGuess("10");

        assertEquals(GuessOutcome.TOO_LOW, result.getOutcome());
        assertFalse(result.isRoundComplete());
    }

    @Test
    void invalidInputDoesNotUseAttempt() {
        GameService service = fixedTargetService(25);
        service.startGame("Asha", DifficultyLevel.EASY);

        GuessResult result = service.submitGuess("abc");

        assertEquals("Only numeric input is allowed.", result.getMessage());
        assertEquals(0, service.getCurrentGame().getCurrentAttempts());
    }

    @Test
    void attemptLimitReachedLosesRound() {
        GameService service = fixedTargetService(50);
        service.startGame("Asha", DifficultyLevel.HARD);

        GuessResult result = null;
        for (int guess = 1; guess <= DifficultyLevel.HARD.getMaxAttempts(); guess++) {
            result = service.submitGuess(String.valueOf(guess));
        }

        assertEquals(GuessOutcome.GAME_OVER, result.getOutcome());
        assertEquals(1, service.calculateStatistics().getTotalLosses());
    }

    @Test
    void playAgainCreatesNewRoundWithoutClearingHistory() {
        GameService service = fixedTargetService(25);
        service.startGame("Asha", DifficultyLevel.EASY);
        service.submitGuess("25");

        service.startGame("Asha", DifficultyLevel.MEDIUM);

        assertEquals(1, service.getRoundHistory().size());
        assertEquals(DifficultyLevel.MEDIUM, service.getCurrentGame().getDifficultyLevel());
    }

    @Test
    void difficultySelectionControlsAttempts() {
        GameService service = fixedTargetService(25);
        service.startGame("Asha", DifficultyLevel.MEDIUM);

        assertEquals(7, service.getCurrentGame().getMaxAttempts());
    }

    @Test
    void statisticsCalculationUsesCompletedRounds() {
        GameService service = fixedTargetService(25);
        service.startGame("Asha", DifficultyLevel.EASY);
        service.submitGuess("25");
        service.startGame("Asha", DifficultyLevel.HARD);
        for (int guess = 1; guess <= DifficultyLevel.HARD.getMaxAttempts(); guess++) {
            service.submitGuess(String.valueOf(guess));
        }

        GameStatistics statistics = service.calculateStatistics();

        assertEquals(2, statistics.getTotalGamesPlayed());
        assertEquals(1, statistics.getTotalWins());
        assertEquals(1, statistics.getTotalLosses());
        assertEquals(50.0, statistics.getWinPercentage());
        assertEquals(1, statistics.getBestScore());
    }

    private GameService fixedTargetService(int target) {
        Random random = new Random() {
            @Override
            public int nextInt(int bound) {
                return target - 1;
            }
        };
        return GameService.createForTesting(new RandomNumberGenerator(random));
    }
}
