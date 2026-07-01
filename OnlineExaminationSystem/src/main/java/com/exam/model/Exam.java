package com.exam.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Exam {
    private int examId;
    private String examName;
    private Duration duration;
    private int totalQuestions;
    private List<Question> questions;

    public Exam() {
        this.questions = new ArrayList<>();
    }

    public Exam(int examId, String examName, Duration duration, List<Question> questions) {
        this.examId = examId;
        this.examName = examName;
        this.duration = duration;
        this.questions = new ArrayList<>(questions);
        this.totalQuestions = questions.size();
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = new ArrayList<>(questions);
        this.totalQuestions = questions.size();
    }
}
