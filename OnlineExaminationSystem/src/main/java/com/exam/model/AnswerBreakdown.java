package com.exam.model;

public class AnswerBreakdown {
    private int questionNumber;
    private String selectedAnswer;
    private String correctAnswer;
    private String status;

    public AnswerBreakdown(int questionNumber, String selectedAnswer, String correctAnswer, String status) {
        this.questionNumber = questionNumber;
        this.selectedAnswer = selectedAnswer;
        this.correctAnswer = correctAnswer;
        this.status = status;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getStatus() {
        return status;
    }
}
