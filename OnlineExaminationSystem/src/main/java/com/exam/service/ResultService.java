package com.exam.service;

import com.exam.constants.ExamConstants;
import com.exam.model.AnswerBreakdown;
import com.exam.model.Exam;
import com.exam.model.Question;
import com.exam.model.Result;
import com.exam.model.UserAnswer;
import com.exam.util.SessionManager;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultService {
    private final SessionManager sessionManager;

    public ResultService(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public Result generateResult() {
        Exam exam = sessionManager.getCurrentExam();
        if (exam == null) {
            throw new IllegalStateException("Exam must be completed before viewing result.");
        }

        Map<Integer, UserAnswer> answers = sessionManager.getAnswers();
        List<AnswerBreakdown> breakdowns = new ArrayList<>();
        int correct = 0;
        int incorrect = 0;
        int questionNumber = 1;

        for (Question question : exam.getQuestions()) {
            UserAnswer answer = answers.get(question.getQuestionId());
            String selected = answer == null ? "Not Answered" : answer.getSelectedAnswer();
            String status;
            if (answer == null) {
                status = "Unanswered";
            } else if (question.getCorrectAnswer().equals(answer.getSelectedAnswer())) {
                status = "Correct";
                correct++;
            } else {
                status = "Incorrect";
                incorrect++;
            }
            breakdowns.add(new AnswerBreakdown(questionNumber++, selected, question.getCorrectAnswer(), status));
        }

        int total = exam.getTotalQuestions();
        int attempted = answers.size();
        int unanswered = total - attempted;
        double percentage = total == 0 ? 0 : (correct * 100.0) / total;
        Duration timeTaken = sessionManager.getElapsedTime();

        Result result = new Result();
        result.setTotalQuestions(total);
        result.setAttemptedQuestions(attempted);
        result.setScore(correct);
        result.setCorrectAnswers(correct);
        result.setIncorrectAnswers(incorrect);
        result.setUnansweredQuestions(unanswered);
        result.setPercentage(percentage);
        result.setTimeTaken(timeTaken);
        result.setStatus(percentage >= ExamConstants.PASS_PERCENTAGE ? "PASS" : "FAIL");
        result.setBreakdown(breakdowns);
        sessionManager.setCurrentResult(result);
        return result;
    }

    public Result requireResult() {
        Result result = sessionManager.getCurrentResult();
        if (result == null) {
            throw new IllegalStateException("Result is available only after exam submission.");
        }
        return result;
    }
}
