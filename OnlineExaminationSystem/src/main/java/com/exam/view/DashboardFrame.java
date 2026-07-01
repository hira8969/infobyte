package com.exam.view;

import com.exam.constants.ExamConstants;
import com.exam.model.Exam;
import com.exam.model.User;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class DashboardFrame extends JFrame {
    private static final String HOME = "home";
    private static final String PROFILE = "profile";
    private static final String EXAM = "exam";
    private static final String RESULT = "result";

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cards = new JPanel(cardLayout);
    private final JPanel homePanel = AppTheme.surface();
    private final JButton profileButton = new JButton("Update Profile");
    private final JButton startExamButton = new JButton("Start Exam");
    private final JButton logoutButton = new JButton("Logout");
    private final ProfilePanel profilePanel;
    private final ExamPanel examPanel;
    private final ResultPanel resultPanel;
    private JLabel welcomeLabel;
    private JLabel examInfoLabel;

    public DashboardFrame(ProfilePanel profilePanel, ExamPanel examPanel, ResultPanel resultPanel) {
        this.profilePanel = profilePanel;
        this.examPanel = examPanel;
        this.resultPanel = resultPanel;
        setTitle("Online Examination System - Dashboard");
        setSize(ExamConstants.WINDOW_WIDTH, ExamConstants.WINDOW_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        buildUi();
    }

    private void buildUi() {
        JPanel root = new JPanel(new BorderLayout(16, 16));
        root.setBackground(AppTheme.BACKGROUND);
        AppTheme.pad(root, 18, 18, 18, 18);
        root.add(buildHeader(), BorderLayout.NORTH);
        buildHomePanel();
        cards.add(homePanel, HOME);
        cards.add(profilePanel, PROFILE);
        cards.add(examPanel, EXAM);
        cards.add(resultPanel, RESULT);
        root.add(cards, BorderLayout.CENTER);
        add(root);
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        welcomeLabel = AppTheme.label("Welcome Student", AppTheme.HEADING);
        JButton headerLogoutButton = new JButton("Logout");
        AppTheme.styleDangerButton(headerLogoutButton);
        headerLogoutButton.addActionListener(event -> logoutButton.doClick());
        header.add(welcomeLabel, BorderLayout.WEST);
        header.add(headerLogoutButton, BorderLayout.EAST);
        return header;
    }

    private void buildHomePanel() {
        homePanel.setLayout(new BorderLayout(20, 20));
        JPanel info = new JPanel(new GridLayout(4, 1, 8, 8));
        info.setOpaque(false);
        examInfoLabel = AppTheme.label("", AppTheme.BODY);
        info.add(AppTheme.label("Dashboard", AppTheme.TITLE));
        info.add(examInfoLabel);
        info.add(AppTheme.label("Use the actions below to update your profile or begin the examination.", AppTheme.BODY));
        homePanel.add(info, BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 12));
        actions.setOpaque(false);
        AppTheme.styleButton(profileButton);
        AppTheme.styleSuccessButton(startExamButton);
        AppTheme.styleDangerButton(logoutButton);
        actions.add(profileButton);
        actions.add(startExamButton);
        actions.add(logoutButton);
        homePanel.add(actions, BorderLayout.SOUTH);
    }

    public void bindUserAndExam(User user, Exam exam) {
        welcomeLabel.setText("Welcome " + user.getDisplayName());
        examInfoLabel.setText("Student: " + user.getDisplayName()
                + "    |    Exam: " + exam.getExamName()
                + "    |    Questions: " + exam.getTotalQuestions()
                + "    |    Duration: " + exam.getDuration().toMinutes() + " minutes");
        profilePanel.setProfile(user.getDisplayName(), user.getPassword());
    }

    public void showHome() {
        cardLayout.show(cards, HOME);
    }

    public void showProfile() {
        cardLayout.show(cards, PROFILE);
    }

    public void showExam() {
        cardLayout.show(cards, EXAM);
    }

    public void showResult() {
        cardLayout.show(cards, RESULT);
    }

    public ResultPanel getResultPanel() {
        return resultPanel;
    }

    public void onProfile(ActionListener listener) {
        profileButton.addActionListener(listener);
    }

    public void onStartExam(ActionListener listener) {
        startExamButton.addActionListener(listener);
    }

    public void onLogout(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }
}
