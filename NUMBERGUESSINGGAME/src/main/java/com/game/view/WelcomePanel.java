package com.game.view;

import com.game.model.DifficultyLevel;
import com.game.model.GameStatistics;
import com.game.model.LeaderboardEntry;
import com.game.model.RoundResult;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

public class WelcomePanel extends JPanel {
    private final JTextField playerNameField;
    private final JComboBox<DifficultyLevel> difficultyComboBox;
    private final JLabel difficultyInfoLabel;
    private final JButton startButton;
    private final JLabel statisticsLabel;
    private final DefaultTableModel historyTableModel;
    private final DefaultTableModel leaderboardTableModel;

    public WelcomePanel() {
        setLayout(new BorderLayout(18, 18));
        setBackground(StyleUtil.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(24, 28, 24, 28));

        playerNameField = new JTextField("Player", 18);
        difficultyComboBox = new JComboBox<>(DifficultyLevel.values());
        difficultyInfoLabel = StyleUtil.body("");
        startButton = new JButton("Start Game");
        statisticsLabel = StyleUtil.body("No games played yet.");
        historyTableModel = new DefaultTableModel(new String[]{"Round", "Difficulty", "Attempts", "Result", "Number", "Seconds"}, 0);
        leaderboardTableModel = new DefaultTableModel(new String[]{"Player", "Wins", "Best Score"}, 0);

        buildHeader();
        buildDashboard();
        updateDifficultyInfo();
        difficultyComboBox.addActionListener(event -> updateDifficultyInfo());
    }

    private void buildHeader() {
        JPanel header = new JPanel(new BorderLayout(10, 10));
        header.setBackground(StyleUtil.BACKGROUND);
        header.add(StyleUtil.heading("Number Guessing Game", 30), BorderLayout.NORTH);
        header.add(StyleUtil.body("Select a difficulty, guess the hidden number, and track your score across rounds."), BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);
    }

    private void buildDashboard() {
        JPanel leftPanel = new JPanel(new BorderLayout(12, 12));
        StyleUtil.applyPanel(leftPanel);
        leftPanel.add(StyleUtil.heading("New Round", 20), BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 12, 12));
        formPanel.setBackground(StyleUtil.PANEL);
        formPanel.add(new JLabel("Player Name"));
        formPanel.add(playerNameField);
        formPanel.add(new JLabel("Difficulty"));
        formPanel.add(difficultyComboBox);
        formPanel.add(new JLabel("Details"));
        formPanel.add(difficultyInfoLabel);
        leftPanel.add(formPanel, BorderLayout.CENTER);

        StyleUtil.applyPrimaryButton(startButton);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(StyleUtil.PANEL);
        buttonPanel.add(startButton);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel rightPanel = new JPanel(new BorderLayout(12, 12));
        StyleUtil.applyPanel(rightPanel);
        rightPanel.add(StyleUtil.heading("Statistics", 20), BorderLayout.NORTH);
        rightPanel.add(statisticsLabel, BorderLayout.CENTER);

        JPanel topGrid = new JPanel(new GridLayout(1, 2, 16, 0));
        topGrid.setBackground(StyleUtil.BACKGROUND);
        topGrid.add(leftPanel);
        topGrid.add(rightPanel);

        JPanel tablesPanel = new JPanel(new GridLayout(1, 2, 16, 0));
        tablesPanel.setBackground(StyleUtil.BACKGROUND);
        tablesPanel.add(tablePanel("Round History", historyTableModel));
        tablesPanel.add(tablePanel("Leaderboard", leaderboardTableModel));

        JPanel center = new JPanel(new BorderLayout(0, 16));
        center.setBackground(StyleUtil.BACKGROUND);
        center.add(topGrid, BorderLayout.NORTH);
        center.add(tablesPanel, BorderLayout.CENTER);
        add(center, BorderLayout.CENTER);
    }

    private JPanel tablePanel(String title, DefaultTableModel tableModel) {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        StyleUtil.applyPanel(panel);
        panel.add(StyleUtil.heading(title, 18), BorderLayout.NORTH);
        JTable table = new JTable(tableModel);
        table.setRowHeight(26);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 280));
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private void updateDifficultyInfo() {
        DifficultyLevel difficulty = getSelectedDifficulty();
        difficultyInfoLabel.setText(difficulty.getRangeText() + " | " + difficulty.getMaxAttempts() + " attempts");
    }

    public DifficultyLevel getSelectedDifficulty() {
        return (DifficultyLevel) difficultyComboBox.getSelectedItem();
    }

    public String getPlayerName() {
        return playerNameField.getText();
    }

    public void addStartListener(ActionListener listener) {
        startButton.addActionListener(listener);
    }

    public void updateStatistics(GameStatistics statistics) {
        String bestScoreText = statistics.getBestScore() == 0 ? "Not available" : statistics.getBestScore() + " attempts";
        statisticsLabel.setText("<html>Total Games: " + statistics.getTotalGamesPlayed()
                + "<br>Total Wins: " + statistics.getTotalWins()
                + "<br>Total Losses: " + statistics.getTotalLosses()
                + "<br>Win Percentage: " + String.format("%.1f", statistics.getWinPercentage()) + "%"
                + "<br>Average Attempts: " + String.format("%.1f", statistics.getAverageAttempts())
                + "<br>Best Score: " + bestScoreText + "</html>");
    }

    public void updateHistory(List<RoundResult> history) {
        historyTableModel.setRowCount(0);
        for (RoundResult result : history) {
            historyTableModel.addRow(new Object[]{
                    result.getRoundNumber(),
                    result.getDifficulty().getDisplayName(),
                    result.getAttemptsUsed(),
                    result.getResult(),
                    result.getTargetNumber(),
                    result.getDurationSeconds()
            });
        }
    }

    public void updateLeaderboard(List<LeaderboardEntry> leaderboard) {
        leaderboardTableModel.setRowCount(0);
        for (LeaderboardEntry entry : leaderboard) {
            leaderboardTableModel.addRow(new Object[]{
                    entry.getPlayerName(),
                    entry.getWins(),
                    entry.getBestScore()
            });
        }
    }
}
