package com.exam.service;

import com.exam.constants.ExamConstants;
import com.exam.model.Exam;
import com.exam.model.Question;
import com.exam.model.UserAnswer;
import com.exam.repository.QuestionRepository;
import com.exam.util.SessionManager;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class ExamService {
    private final QuestionRepository questionRepository;
    private final SessionManager sessionManager;

    public ExamService(QuestionRepository questionRepository, SessionManager sessionManager) {
        this.questionRepository = questionRepository;
        this.sessionManager = sessionManager;
    }

    public Exam createExam() {
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) {
            throw new IllegalStateException("No questions are available for the exam.");
        }
        return new Exam(1, ExamConstants.EXAM_NAME, Duration.ofMinutes(ExamConstants.EXAM_DURATION_MINUTES), questions);
    }

    public Exam startExam() {
        if (!sessionManager.isLoggedIn()) {
            throw new IllegalStateException("Please login before starting the exam.");
        }
        Exam exam = createExam();
        sessionManager.startExam(exam);
        return exam;
    }

    public void saveAnswer(int questionId, String selectedAnswer) {
        if (sessionManager.getCurrentExam() == null) {
            throw new IllegalStateException("No active exam found.");
        }
        Map<Integer, UserAnswer> answers = sessionManager.getAnswers();
        if (selectedAnswer == null || selectedAnswer.isBlank()) {
            answers.remove(questionId);
        } else {
            answers.put(questionId, new UserAnswer(questionId, selectedAnswer));
        }
    }

    public String getSelectedAnswer(int questionId) {
        UserAnswer answer = sessionManager.getAnswers().get(questionId);
        return answer == null ? null : answer.getSelectedAnswer();
    }

    public int getAttemptedCount() {
        return sessionManager.getAnswers().size();
    }

    public Duration getElapsedTime() {
        return sessionManager.getElapsedTime();
    }

    public void markSubmitted() {
        sessionManager.markSubmitted();
    }
}
