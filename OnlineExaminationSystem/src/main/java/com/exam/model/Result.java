package com.exam.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Result {
    private int totalQuestions;
    private int attemptedQuestions;
    private int score;
    private double percentage;
    private int correctAnswers;
    private int incorrectAnswers;
    private int unansweredQuestions;
    private Duration timeTaken;
    private String status;
    private List<AnswerBreakdown> breakdown;

    public Result() {
        this.breakdown = new ArrayList<>();
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getAttemptedQuestions() {
        return attemptedQuestions;
    }

    public void setAttemptedQuestions(int attemptedQuestions) {
        this.attemptedQuestions = attemptedQuestions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(int incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public int getUnansweredQuestions() {
        return unansweredQuestions;
    }

    public void setUnansweredQuestions(int unansweredQuestions) {
        this.unansweredQuestions = unansweredQuestions;
    }

    public Duration getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(Duration timeTaken) {
        this.timeTaken = timeTaken;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AnswerBreakdown> getBreakdown() {
        return breakdown;
    }

    public void setBreakdown(List<AnswerBreakdown> breakdown) {
        this.breakdown = new ArrayList<>(breakdown);
    }
}
