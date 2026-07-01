package com.exam.controller;

import com.exam.model.Exam;
import com.exam.model.Result;
import com.exam.model.User;
import com.exam.service.AuthenticationService;
import com.exam.service.ExamService;
import com.exam.service.ResultService;
import com.exam.util.DialogUtil;
import com.exam.util.SessionManager;
import com.exam.view.DashboardFrame;
import com.exam.view.LoginFrame;
import com.exam.view.ResultPanel;

public class ResultController {
    private final ResultPanel resultPanel;
    private final DashboardFrame dashboardFrame;
    private final AuthenticationService authenticationService;
    private final ExamService examService;
    private final ResultService resultService;

    public ResultController(ResultPanel resultPanel, DashboardFrame dashboardFrame,
                            AuthenticationService authenticationService, ExamService examService,
                            ResultService resultService) {
        this.resultPanel = resultPanel;
        this.dashboardFrame = dashboardFrame;
        this.authenticationService = authenticationService;
        this.examService = examService;
        this.resultService = resultService;
        bindEvents();
    }

    private void bindEvents() {
        resultPanel.onLogout(event -> logout());
    }

    private void logout() {
        authenticationService.logout();
        DialogUtil.showInfo(dashboardFrame, "Logged Out Successfully");
        LoginFrame loginFrame = new LoginFrame();
        new LoginController(loginFrame, authenticationService, examService, resultService);
        loginFrame.setVisible(true);
        dashboardFrame.dispose();
    }

    public static class RenderResultEvent {
        private final DashboardFrame dashboardFrame;
        private final Result result;

        public RenderResultEvent(DashboardFrame dashboardFrame, Result result) {
            this.dashboardFrame = dashboardFrame;
            this.result = result;
        }

        public void render() {
            SessionManager sessionManager = SessionManager.getInstance();
            User user = sessionManager.getLoggedInUser();
            Exam exam = sessionManager.getCurrentExam();
            ResultPanel resultPanel = dashboardFrame.getResultPanel();
            resultPanel.render(user.getDisplayName(), exam.getExamName(), result);
            dashboardFrame.showResult();
        }
    }
}
