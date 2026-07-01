package com.exam.util;

import com.exam.model.Exam;
import com.exam.model.Result;
import com.exam.model.User;
import com.exam.model.UserAnswer;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public final class SessionManager {
    private static final SessionManager INSTANCE = new SessionManager();

    private User loggedInUser;
    private Exam currentExam;
    private Result currentResult;
    private final Map<Integer, UserAnswer> answers = new HashMap<>();
    private Instant examStartedAt;
    private Duration elapsedOnSubmit = Duration.ZERO;

    private SessionManager() {
    }

    public static SessionManager getInstance() {
        return INSTANCE;
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public Exam getCurrentExam() {
        return currentExam;
    }

    public Result getCurrentResult() {
        return currentResult;
    }

    public void setCurrentResult(Result currentResult) {
        this.currentResult = currentResult;
    }

    public Map<Integer, UserAnswer> getAnswers() {
        return answers;
    }

    public void startExam(Exam exam) {
        this.currentExam = exam;
        this.currentResult = null;
        this.answers.clear();
        this.examStartedAt = Instant.now();
        this.elapsedOnSubmit = Duration.ZERO;
    }

    public Duration getElapsedTime() {
        if (examStartedAt == null) {
            return elapsedOnSubmit;
        }
        return Duration.between(examStartedAt, Instant.now());
    }

    public void markSubmitted() {
        this.elapsedOnSubmit = getElapsedTime();
        this.examStartedAt = null;
    }

    public void clearExam() {
        currentExam = null;
        currentResult = null;
        answers.clear();
        examStartedAt = null;
        elapsedOnSubmit = Duration.ZERO;
    }

    public void logout() {
        loggedInUser = null;
        clearExam();
    }
}
