package com.game.view;

import com.game.model.Game;
import com.game.model.GameStatistics;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class ResultPanel extends JPanel {
    private final JLabel resultTitleLabel;
    private final JLabel resultDetailsLabel;
    private final JLabel statisticsLabel;
    private final JButton playAgainButton;
    private final JButton exitButton;

    public ResultPanel() {
        setLayout(new BorderLayout(18, 18));
        setBackground(StyleUtil.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(80, 190, 80, 190));

        resultTitleLabel = StyleUtil.heading("", 30);
        resultDetailsLabel = StyleUtil.body("");
        statisticsLabel = StyleUtil.body("");
        playAgainButton = new JButton("Yes, Play Again");
        exitButton = new JButton("No, Exit");
        buildLayout();
    }

    private void buildLayout() {
        JPanel card = new JPanel(new BorderLayout(18, 18));
        StyleUtil.applyPanel(card);
        card.add(resultTitleLabel, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(0, 1, 12, 12));
        center.setBackground(StyleUtil.PANEL);
        center.add(resultDetailsLabel);
        center.add(statisticsLabel);
        card.add(center, BorderLayout.CENTER);

        StyleUtil.applyPrimaryButton(playAgainButton);
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.setBackground(StyleUtil.PANEL);
        actions.add(playAgainButton);
        actions.add(exitButton);
        card.add(actions, BorderLayout.SOUTH);
        add(card, BorderLayout.CENTER);
    }

    public void showResult(boolean won, Game game, GameStatistics statistics) {
        resultTitleLabel.setText(won ? "Congratulations!" : "You Lost!");
        resultTitleLabel.setForeground(won ? StyleUtil.SUCCESS : StyleUtil.DANGER);
        String resultMessage = won ? "You guessed the number correctly." : "The maximum attempts were used.";
        resultDetailsLabel.setText("<html>" + resultMessage
                + "<br>Number: " + game.getTargetNumber()
                + "<br>Attempts Used: " + game.getCurrentAttempts()
                + "<br>Duration: " + game.getDurationSeconds() + " seconds"
                + "<br><br>Play Again?</html>");

        String bestScore = statistics.getBestScore() == 0 ? "Not available" : statistics.getBestScore() + " attempts";
        statisticsLabel.setText("<html>Total Games: " + statistics.getTotalGamesPlayed()
                + "<br>Wins: " + statistics.getTotalWins()
                + "<br>Losses: " + statistics.getTotalLosses()
                + "<br>Win Percentage: " + String.format("%.1f", statistics.getWinPercentage()) + "%"
                + "<br>Best Score: " + bestScore + "</html>");
    }

    public void addPlayAgainListener(ActionListener listener) {
        playAgainButton.addActionListener(listener);
    }

    public void addExitListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }
}
