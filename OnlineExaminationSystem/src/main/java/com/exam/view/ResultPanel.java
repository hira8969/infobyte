package com.exam.view;

import com.exam.model.AnswerBreakdown;
import com.exam.model.Result;
import com.exam.util.TimerUtil;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class ResultPanel extends JPanel {
    private final JLabel summaryLabel = AppTheme.label("", AppTheme.HEADING);
    private final JPanel statsPanel = new JPanel(new GridLayout(3, 3, 12, 12));
    private final DefaultTableModel tableModel = new DefaultTableModel(
            new Object[]{"Question", "Selected Answer", "Correct Answer", "Status"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable table = new JTable(tableModel);
    private final JButton logoutButton = new JButton("Logout");

    public ResultPanel() {
        setBackground(AppTheme.BACKGROUND);
        setLayout(new BorderLayout(16, 16));
        buildUi();
    }

    private void buildUi() {
        add(AppTheme.label("Result", AppTheme.TITLE), BorderLayout.NORTH);
        JPanel center = AppTheme.surface();
        center.setLayout(new BorderLayout(14, 14));
        statsPanel.setOpaque(false);
        center.add(summaryLabel, BorderLayout.NORTH);
        center.add(statsPanel, BorderLayout.CENTER);
        center.add(new JScrollPane(table), BorderLayout.SOUTH);
        add(center, BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.setOpaque(false);
        AppTheme.styleButton(logoutButton);
        actions.add(logoutButton);
        add(actions, BorderLayout.SOUTH);
    }

    public void render(String studentName, String examName, Result result) {
        summaryLabel.setText(studentName + " | " + examName + " | " + result.getStatus());
        statsPanel.removeAll();
        statsPanel.add(new JLabel("Score: " + result.getScore() + "/" + result.getTotalQuestions()));
        statsPanel.add(new JLabel(String.format("Percentage: %.2f%%", result.getPercentage())));
        statsPanel.add(new JLabel("Attempted: " + result.getAttemptedQuestions()));
        statsPanel.add(new JLabel("Correct: " + result.getCorrectAnswers()));
        statsPanel.add(new JLabel("Incorrect: " + result.getIncorrectAnswers()));
        statsPanel.add(new JLabel("Unanswered: " + result.getUnansweredQuestions()));
        statsPanel.add(new JLabel("Time Taken: " + TimerUtil.format(result.getTimeTaken())));
        statsPanel.add(new JLabel("Result: " + result.getStatus()));
        statsPanel.add(new JLabel("Exam: " + examName));

        tableModel.setRowCount(0);
        for (AnswerBreakdown row : result.getBreakdown()) {
            tableModel.addRow(new Object[]{
                    "Q" + row.getQuestionNumber(),
                    row.getSelectedAnswer(),
                    row.getCorrectAnswer(),
                    row.getStatus()
            });
        }
        revalidate();
        repaint();
    }

    public void onLogout(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }
}
