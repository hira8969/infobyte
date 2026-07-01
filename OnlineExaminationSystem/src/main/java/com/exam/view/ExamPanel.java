package com.exam.view;

import com.exam.model.Exam;
import com.exam.model.Question;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ExamPanel extends JPanel {
    private final JLabel timerLabel = AppTheme.label("30:00", AppTheme.HEADING);
    private final JLabel progressLabel = AppTheme.label("Question 1 of 20", AppTheme.HEADING);
    private final JLabel questionLabel = AppTheme.label("", AppTheme.HEADING);
    private final JProgressBar progressBar = new JProgressBar();
    private final ButtonGroup optionGroup = new ButtonGroup();
    private final Map<String, JRadioButton> optionButtons = new HashMap<>();
    private final JButton previousButton = new JButton("Previous");
    private final JButton nextButton = new JButton("Next");
    private final JButton submitButton = new JButton("Submit");

    public ExamPanel() {
        setBackground(AppTheme.BACKGROUND);
        setLayout(new BorderLayout(16, 16));
        buildUi();
    }

    private void buildUi() {
        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.add(progressLabel, BorderLayout.WEST);
        top.add(timerLabel, BorderLayout.EAST);
        add(top, BorderLayout.NORTH);

        JPanel questionCard = AppTheme.surface();
        questionCard.setLayout(new BorderLayout(18, 18));
        questionCard.add(questionLabel, BorderLayout.NORTH);

        JPanel options = new JPanel(new GridLayout(4, 1, 10, 10));
        options.setOpaque(false);
        for (String key : new String[]{"A", "B", "C", "D"}) {
            JRadioButton button = new JRadioButton();
            button.setFont(AppTheme.BODY);
            button.setOpaque(false);
            optionGroup.add(button);
            optionButtons.put(key, button);
            options.add(button);
        }
        questionCard.add(options, BorderLayout.CENTER);
        add(questionCard, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setOpaque(false);
        progressBar.setStringPainted(true);
        bottom.add(progressBar, BorderLayout.NORTH);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 12));
        actions.setOpaque(false);
        AppTheme.styleSecondaryButton(previousButton);
        AppTheme.styleButton(nextButton);
        AppTheme.styleWarningButton(submitButton);
        actions.add(previousButton);
        actions.add(nextButton);
        actions.add(submitButton);
        bottom.add(actions, BorderLayout.SOUTH);
        add(bottom, BorderLayout.SOUTH);
    }

    public void renderQuestion(Exam exam, int index, String selectedAnswer, int attemptedCount) {
        Question question = exam.getQuestions().get(index);
        progressLabel.setText("Question " + (index + 1) + " of " + exam.getTotalQuestions());
        questionLabel.setText("<html><body style='width:900px'>" + question.getQuestionText() + "</body></html>");
        optionGroup.clearSelection();
        question.getOptions().forEach((key, value) -> {
            JRadioButton button = optionButtons.get(key);
            button.setText(key + ". " + value);
            button.setActionCommand(key);
            button.setSelected(key.equals(selectedAnswer));
        });
        previousButton.setEnabled(index > 0);
        nextButton.setEnabled(index < exam.getTotalQuestions() - 1);
        progressBar.setMaximum(exam.getTotalQuestions());
        progressBar.setValue(attemptedCount);
        progressBar.setString(attemptedCount + " attempted / " + exam.getTotalQuestions());
    }

    public String getSelectedAnswer() {
        return optionGroup.getSelection() == null ? null : optionGroup.getSelection().getActionCommand();
    }

    public void setTimerText(String timerText) {
        timerLabel.setText(timerText);
    }

    public void onOptionSelected(ActionListener listener) {
        optionButtons.values().forEach(button -> button.addActionListener(listener));
    }

    public void onPrevious(ActionListener listener) {
        previousButton.addActionListener(listener);
    }

    public void onNext(ActionListener listener) {
        nextButton.addActionListener(listener);
    }

    public void onSubmit(ActionListener listener) {
        submitButton.addActionListener(listener);
    }
}
