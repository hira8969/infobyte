package com.game;

import com.game.controller.GameController;
import com.game.service.GameService;
import com.game.view.MainFrame;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameService gameService = GameService.getInstance();
            MainFrame mainFrame = new MainFrame();
            new GameController(gameService, mainFrame);
            mainFrame.setVisible(true);
        });
    }
}
