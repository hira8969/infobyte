package com.game.controller;

import com.game.model.DifficultyLevel;
import com.game.model.Game;
import com.game.model.GuessOutcome;
import com.game.model.GuessResult;
import com.game.service.GameService;
import com.game.view.MainFrame;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class GameController {
    private final GameService gameService;
    private final MainFrame mainFrame;
    private final Timer timer;

    public GameController(GameService gameService, MainFrame mainFrame) {
        this.gameService = gameService;
        this.mainFrame = mainFrame;
        this.timer = new Timer(1000, event -> refreshTimer());
        attachListeners();
        updateDashboard();
    }

    private void attachListeners() {
        mainFrame.getWelcomePanel().addStartListener(event -> startNewGame());
        mainFrame.getGamePanel().addGuessListener(event -> submitGuess());
        mainFrame.getGamePanel().addEnterKeyListener(event -> submitGuess());
        mainFrame.getResultPanel().addPlayAgainListener(event -> mainFrame.showWelcomePanel());
        mainFrame.getResultPanel().addExitListener(event -> System.exit(0));
    }

    private void startNewGame() {
        DifficultyLevel difficultyLevel = mainFrame.getWelcomePanel().getSelectedDifficulty();
        String playerName = mainFrame.getWelcomePanel().getPlayerName();
        Game game = gameService.startGame(playerName, difficultyLevel);
        mainFrame.getGamePanel().prepareForGame(game, gameService.getPlayerName());
        mainFrame.showGamePanel();
        timer.start();
    }

    private void submitGuess() {
        try {
            GuessResult guessResult = gameService.submitGuess(mainFrame.getGamePanel().getGuessInput());
            Game game = gameService.getCurrentGame();
            mainFrame.getGamePanel().updateAfterGuess(game, guessResult);

            if (guessResult.isRoundComplete()) {
                timer.stop();
                boolean won = guessResult.getOutcome() == GuessOutcome.CORRECT;
                mainFrame.getResultPanel().showResult(won, game, gameService.calculateStatistics());
                updateDashboard();
                mainFrame.showResultPanel();
            }
        } catch (IllegalStateException exception) {
            JOptionPane.showMessageDialog(mainFrame, exception.getMessage(), "Game Error", JOptionPane.WARNING_MESSAGE);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(mainFrame, "Something went wrong. Please try again.",
                    "Unexpected Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshTimer() {
        Game game = gameService.getCurrentGame();
        if (game != null && !game.isGameOver()) {
            mainFrame.getGamePanel().setTimerText(game.getDurationSeconds());
        }
    }

    private void updateDashboard() {
        mainFrame.getWelcomePanel().updateStatistics(gameService.calculateStatistics());
        mainFrame.getWelcomePanel().updateHistory(gameService.getRoundHistory());
        mainFrame.getWelcomePanel().updateLeaderboard(gameService.getLeaderboard());
    }
}
