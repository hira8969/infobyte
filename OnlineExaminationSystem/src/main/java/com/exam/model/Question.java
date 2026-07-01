package com.exam.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Question {
    private int questionId;
    private String questionText;
    private Map<String, String> options;
    private String correctAnswer;

    public Question() {
        this.options = new LinkedHashMap<>();
    }

    public Question(int questionId, String questionText, Map<String, String> options, String correctAnswer) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.options = new LinkedHashMap<>(options);
        this.correctAnswer = correctAnswer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = new LinkedHashMap<>(options);
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
