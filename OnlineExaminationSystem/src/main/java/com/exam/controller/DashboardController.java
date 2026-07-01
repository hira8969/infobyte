package com.exam.controller;

import com.exam.service.AuthenticationService;
import com.exam.service.ExamService;
import com.exam.util.DialogUtil;
import com.exam.view.DashboardFrame;
import com.exam.view.LoginFrame;

import javax.swing.WindowConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DashboardController {
    private final DashboardFrame dashboardFrame;
    private final AuthenticationService authenticationService;
    private final ExamService examService;

    public DashboardController(DashboardFrame dashboardFrame, AuthenticationService authenticationService,
                               ExamService examService) {
        this.dashboardFrame = dashboardFrame;
        this.authenticationService = authenticationService;
        this.examService = examService;
        bindEvents();
    }

    private void bindEvents() {
        dashboardFrame.onProfile(event -> dashboardFrame.showProfile());
        dashboardFrame.onStartExam(event -> dashboardFrame.showExam());
        dashboardFrame.onLogout(event -> logout());
        dashboardFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                String message = "Are you sure you want to quit the exam?";
                if (DialogUtil.confirm(dashboardFrame, message)) {
                    dashboardFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    logout();
                }
            }
        });
    }

    private void logout() {
        authenticationService.logout();
        DialogUtil.showInfo(dashboardFrame, "Logged Out Successfully");
        LoginFrame loginFrame = new LoginFrame();
        new LoginController(loginFrame, authenticationService, examService,
                new com.exam.service.ResultService(com.exam.util.SessionManager.getInstance()));
        loginFrame.setVisible(true);
        dashboardFrame.dispose();
    }
}
