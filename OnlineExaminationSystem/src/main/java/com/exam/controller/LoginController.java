package com.exam.controller;

import com.exam.model.Exam;
import com.exam.model.User;
import com.exam.service.AuthenticationService;
import com.exam.service.ExamService;
import com.exam.service.ResultService;
import com.exam.util.DialogUtil;
import com.exam.view.DashboardFrame;
import com.exam.view.ExamPanel;
import com.exam.view.LoginFrame;
import com.exam.view.ProfilePanel;
import com.exam.view.ResultPanel;

public class LoginController {
    private final LoginFrame loginFrame;
    private final AuthenticationService authenticationService;
    private final ExamService examService;
    private final ResultService resultService;

    public LoginController(LoginFrame loginFrame, AuthenticationService authenticationService,
                           ExamService examService, ResultService resultService) {
        this.loginFrame = loginFrame;
        this.authenticationService = authenticationService;
        this.examService = examService;
        this.resultService = resultService;
        bindEvents();
    }

    private void bindEvents() {
        loginFrame.onLogin(event -> login());
        loginFrame.onReset(event -> loginFrame.clearFields());
        loginFrame.onExit(event -> {
            if (DialogUtil.confirm(loginFrame, "Are you sure you want to exit?")) {
                System.exit(0);
            }
        });
    }

    private void login() {
        try {
            User user = authenticationService.login(loginFrame.getUsername(), loginFrame.getPassword());
            Exam examSummary = examService.createExam();
            ProfilePanel profilePanel = new ProfilePanel();
            ExamPanel examPanel = new ExamPanel();
            ResultPanel resultPanel = new ResultPanel();
            DashboardFrame dashboardFrame = new DashboardFrame(profilePanel, examPanel, resultPanel);
            new ProfileController(profilePanel, dashboardFrame, authenticationService);
            new ExamController(examPanel, dashboardFrame, examService, resultService);
            new ResultController(resultPanel, dashboardFrame, authenticationService, examService, resultService);
            new DashboardController(dashboardFrame, authenticationService, examService);
            dashboardFrame.bindUserAndExam(user, examSummary);
            dashboardFrame.showHome();
            dashboardFrame.setVisible(true);
            loginFrame.dispose();
        } catch (Exception exception) {
            DialogUtil.showError(loginFrame, exception.getMessage());
        }
    }
}
