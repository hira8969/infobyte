package com.exam.controller;

import com.exam.model.Exam;
import com.exam.model.Result;
import com.exam.service.ExamService;
import com.exam.service.ResultService;
import com.exam.util.DialogUtil;
import com.exam.util.TimerUtil;
import com.exam.view.DashboardFrame;
import com.exam.view.ExamPanel;

import javax.swing.Timer;

public class ExamController {
    private final ExamPanel examPanel;
    private final DashboardFrame dashboardFrame;
    private final ExamService examService;
    private final ResultService resultService;
    private Exam currentExam;
    private int currentIndex;
    private Timer timer;

    public ExamController(ExamPanel examPanel, DashboardFrame dashboardFrame,
                          ExamService examService, ResultService resultService) {
        this.examPanel = examPanel;
        this.dashboardFrame = dashboardFrame;
        this.examService = examService;
        this.resultService = resultService;
        bindEvents();
    }

    private void bindEvents() {
        examPanel.onOptionSelected(event -> saveCurrentAnswer());
        examPanel.onPrevious(event -> {
            saveCurrentAnswer();
            currentIndex--;
            render();
        });
        examPanel.onNext(event -> {
            saveCurrentAnswer();
            currentIndex++;
            render();
        });
        examPanel.onSubmit(event -> manualSubmit());
        dashboardFrame.onStartExam(event -> startExam());
    }

    private void startExam() {
        try {
            currentExam = examService.startExam();
            currentIndex = 0;
            startTimer();
            render();
        } catch (Exception exception) {
            DialogUtil.showError(examPanel, exception.getMessage());
        }
    }

    private void render() {
        if (currentExam == null) {
            return;
        }
        int questionId = currentExam.getQuestions().get(currentIndex).getQuestionId();
        examPanel.renderQuestion(currentExam, currentIndex, examService.getSelectedAnswer(questionId),
                examService.getAttemptedCount());
    }

    private void saveCurrentAnswer() {
        if (currentExam == null) {
            return;
        }
        int questionId = currentExam.getQuestions().get(currentIndex).getQuestionId();
        examService.saveAnswer(questionId, examPanel.getSelectedAnswer());
        render();
    }

    private void startTimer() {
        if (timer != null) {
            timer.stop();
        }
        timer = TimerUtil.countdown(currentExam.getDuration(),
                remaining -> examPanel.setTimerText(TimerUtil.format(remaining)),
                () -> submit(true));
        timer.start();
    }

    private void manualSubmit() {
        saveCurrentAnswer();
        if (examService.getAttemptedCount() < currentExam.getTotalQuestions()) {
            DialogUtil.showInfo(examPanel, "Some questions are unanswered. Please review before final submission.");
        }
        if (DialogUtil.confirm(examPanel, "Are you sure you want to submit the exam?")) {
            submit(false);
        }
    }

    private void submit(boolean autoSubmitted) {
        if (timer != null) {
            timer.stop();
        }
        examService.markSubmitted();
        Result result = resultService.generateResult();
        new ResultController.RenderResultEvent(dashboardFrame, result).render();
        if (autoSubmitted) {
            DialogUtil.showInfo(examPanel, "Time Up! Exam Submitted Automatically.");
        }
    }
}
