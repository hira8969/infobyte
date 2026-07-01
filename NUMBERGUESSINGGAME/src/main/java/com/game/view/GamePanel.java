package com.game.view;

import com.game.model.Game;
import com.game.model.GuessResult;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
    private final JLabel titleLabel;
    private final JLabel rangeLabel;
    private final JLabel attemptLabel;
    private final JLabel timerLabel;
    private final JLabel hintLabel;
    private final JTextField guessField;
    private final JButton guessButton;

    public GamePanel() {
        setLayout(new BorderLayout(18, 18));
        setBackground(StyleUtil.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(60, 160, 60, 160));

        titleLabel = StyleUtil.heading("Round in Progress", 28);
        rangeLabel = StyleUtil.body("");
        attemptLabel = StyleUtil.body("");
        timerLabel = StyleUtil.body("Time: 0 seconds");
        hintLabel = StyleUtil.heading("Enter your guess", 22);
        guessField = new JTextField(12);
        guessButton = new JButton("Submit Guess");

        buildLayout();
    }

    private void buildLayout() {
        JPanel card = new JPanel(new BorderLayout(16, 16));
        StyleUtil.applyPanel(card);

        JPanel top = new JPanel(new GridLayout(0, 1, 6, 6));
        top.setBackground(StyleUtil.PANEL);
        top.add(titleLabel);
        top.add(rangeLabel);
        top.add(attemptLabel);
        top.add(timerLabel);
        card.add(top, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(0, 1, 14, 14));
        center.setBackground(StyleUtil.PANEL);
        center.add(hintLabel);
        center.add(guessField);
        card.add(center, BorderLayout.CENTER);

        StyleUtil.applyPrimaryButton(guessButton);
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionPanel.setBackground(StyleUtil.PANEL);
        actionPanel.add(guessButton);
        card.add(actionPanel, BorderLayout.SOUTH);

        add(card, BorderLayout.CENTER);
    }

    public void prepareForGame(Game game, String playerName) {
        titleLabel.setText("Good luck, " + playerName + "!");
        rangeLabel.setText("Difficulty: " + game.getDifficultyLevel().getDisplayName()
                + " | Range: " + game.getDifficultyLevel().getRangeText());
        hintLabel.setText("Enter your guess");
        guessField.setText("");
        guessField.setEnabled(true);
        guessButton.setEnabled(true);
        updateAttempts(game);
        setTimerText(0);
        guessField.requestFocusInWindow();
    }

    public void updateAfterGuess(Game game, GuessResult guessResult) {
        hintLabel.setText(guessResult.getMessage());
        updateAttempts(game);
        setTimerText(game.getDurationSeconds());
        guessField.setText("");
        if (guessResult.isRoundComplete()) {
            guessField.setEnabled(false);
            guessButton.setEnabled(false);
        }
    }

    private void updateAttempts(Game game) {
        attemptLabel.setText("Attempt " + game.getCurrentAttempts() + " of " + game.getMaxAttempts()
                + " | Remaining: " + game.getRemainingAttempts());
    }

    public String getGuessInput() {
        return guessField.getText();
    }

    public void setTimerText(long seconds) {
        timerLabel.setText("Time: " + seconds + " seconds");
    }

    public void addGuessListener(ActionListener listener) {
        guessButton.addActionListener(listener);
    }

    public void addEnterKeyListener(ActionListener listener) {
        guessField.addActionListener(listener);
    }
}
