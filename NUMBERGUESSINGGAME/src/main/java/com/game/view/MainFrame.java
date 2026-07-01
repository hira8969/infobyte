package com.game.view;

import com.game.constants.GameConstants;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class MainFrame extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel contentPanel;
    private final WelcomePanel welcomePanel;
    private final GamePanel gamePanel;
    private final ResultPanel resultPanel;

    public MainFrame() {
        super(GameConstants.APP_TITLE);
        this.cardLayout = new CardLayout();
        this.contentPanel = new JPanel(cardLayout);
        this.welcomePanel = new WelcomePanel();
        this.gamePanel = new GamePanel();
        this.resultPanel = new ResultPanel();

        contentPanel.add(welcomePanel, "welcome");
        contentPanel.add(gamePanel, "game");
        contentPanel.add(resultPanel, "result");

        setContentPane(contentPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void showWelcomePanel() {
        cardLayout.show(contentPanel, "welcome");
    }

    public void showGamePanel() {
        cardLayout.show(contentPanel, "game");
    }

    public void showResultPanel() {
        cardLayout.show(contentPanel, "result");
    }

    public WelcomePanel getWelcomePanel() {
        return welcomePanel;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public ResultPanel getResultPanel() {
        return resultPanel;
    }
}
