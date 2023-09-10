package org.example.view;


import org.example.model.Ball;
import org.example.model.Brick;
import org.example.model.GameModel;
import org.example.model.Item;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameView extends JPanel {
    private final GameModel model;

    public GameView(GameModel model) {
        this.model = model;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.draw(g);
    }

    private void draw(Graphics g) {
        // Vẽ nền
        g.drawImage(model.getBackgroundImage(), 0, 0, this);
        // Vẽ các viên gạch
        List<Brick> bricks = model.getBricks();
        for (Brick brick : bricks) {
            g.drawImage(brick.getImage(), brick.getUpperLeftCorner().x, brick.getUpperLeftCorner().y, brick.getWidth(), brick.getHeight(), null);
        }
        // Vẽ các viên bi
        List<Ball> balls = model.getBalls();
        for (Ball ball : balls) {
            g.drawImage(model.getBallImage(), ball.getXDraw(), ball.getYDraw(), ball.getDiameter(), ball.getDiameter(), null);
        }
        // Vẽ item
        List<Item> items = model.getItems();
        for (Item item : items) {
            g.drawImage(item.getIcon(), item.getX() - 10, item.getY() - 10, 20, 20, null);
        }
        // Vẽ thanh gỗ
        g.drawImage(model.getPaddleImage(), model.getPaddle().getX(), model.getPaddle().getY(), model.getPaddle().getWidth(), model.getPaddle().getHeight(), null);
        if (model.isGameOver()) {
            g.setColor(Color.blue);
            g.fillRect(0, 240, 960, 200);
            g.setColor(Color.black);
            g.drawString("Hồi sinh 10 sao", 240, 420);
            g.drawString("Choi lai", 435, 420);
            g.drawString("Qua man 50 sao", 540, 420);
            g.drawString("Dang co: " + model.getStar() + "sao", 700, 260);
            g.setFont(new Font("Ink Free", Font.BOLD, 60));
            FontMetrics fontMetrics = getFontMetrics(g.getFont());
            g.drawString("GAME OVER", 480 - fontMetrics.stringWidth("GAME OVER") / 2, 360);
        }

        if (model.isNextLevel()) {
            g.setColor(Color.black);
            g.setFont(model.getInkFree());
            g.drawString("CLEAR", 387, 360);
        }

        // kẻ ô vuông
//        g.setColor(Color.BLUE);
//        for (int i = 0; i <= 48; i++) {
//            g.drawLine(i * 20, 0, i * 20, 720);
//            if (i <= 720 / 20) {
//                g.drawLine(0, i * 20, 960, i * 20);
//            }
//        }
    }
}
