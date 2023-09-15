package org.example;

import org.example.controller.GameController;
import org.example.model.GameModel;
import org.example.view.GameView;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class BrickBreakerGameApp {


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameModel model = new GameModel();
            GameView view = new GameView(model);
            new GameController(model, view);

            JFrame frame = new JFrame("Many Brick Breaker");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(view);
            frame.setSize(GameModel.WIDTH + 14, GameModel.HEIGHT + 38);
            frame.setLocationRelativeTo(null);
            frame.setIconImage(model.getBallImage());
            frame.setResizable(false);

            // Tạo một đối tượng WindowListener
            WindowListener windowListener = new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    //Lưu dữ liệu game trước khi đóng
                    model.saveData();
                }
            };
            // Gán WindowListener cho cửa sổ
            frame.addWindowListener(windowListener);
            frame.setVisible(true);
        });
    }
}

