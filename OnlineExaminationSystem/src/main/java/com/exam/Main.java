package com.exam;

import com.exam.controller.LoginController;
import com.exam.repository.QuestionRepository;
import com.exam.repository.UserRepository;
import com.exam.service.AuthenticationService;
import com.exam.service.ExamService;
import com.exam.service.ResultService;
import com.exam.util.SessionManager;
import com.exam.view.LoginFrame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            setLookAndFeel();
            SessionManager sessionManager = SessionManager.getInstance();
            UserRepository userRepository = new UserRepository();
            QuestionRepository questionRepository = new QuestionRepository();
            AuthenticationService authenticationService = new AuthenticationService(userRepository, sessionManager);
            ExamService examService = new ExamService(questionRepository, sessionManager);
            ResultService resultService = new ResultService(sessionManager);

            LoginFrame loginFrame = new LoginFrame();
            new LoginController(loginFrame, authenticationService, examService, resultService);
            loginFrame.setVisible(true);
        });
    }

    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
            // Default Swing theme is acceptable if the host look and feel is unavailable.
        }
    }
}
