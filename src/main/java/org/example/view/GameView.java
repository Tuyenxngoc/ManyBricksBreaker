package org.example.view;


import org.example.model.Ball;
import org.example.model.Brick;
import org.example.model.GameModel;
import org.example.model.Item;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static org.example.model.GameModel.*;

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
        // Vẽ thông tin thời gian game và số mạng
        g.setColor(white);
        g.fillRoundRect(280, 10, 100, 28, 10, 10);
        g.fillRoundRect(580, 10, 100, 28, 10, 10);
        g.setColor(blue);
        g.setFont(tahoma);
        g.drawString(model.getTim() + "x", 320, 30);
        g.drawString(model.getTimeDraw(), 620, 30);
        // Vẽ các viên gạch
        List<Brick> bricks = model.getBricks();
        for (Brick brick : bricks) {
            g.drawImage(brick.getImage(), brick.getUpperLeftCorner().x, brick.getUpperLeftCorner().y, brick.getWidth(), brick.getHeight(), null);
        }
        // Vẽ các viên bi
        List<Ball> balls = model.getBalls();
        for (Ball ball : balls) {
            if (ball.isFireBall()) {
                g.setColor(Color.red);
                g.fillOval(ball.getXDraw(), ball.getYDraw(), ball.getDiameter(), ball.getDiameter());
                //Vẽ thời gian hiệu lực item FIREBALL
                g.drawImage(GameModel.iconItem[2], 20, 660, 20, 20, null);
                g.setColor(Color.black);
                g.drawString(String.format("%.2fs", ball.getFireBallTime()), 45, 676);
            } else {
                g.drawImage(model.getBallImage(), ball.getXDraw(), ball.getYDraw(), ball.getDiameter(), ball.getDiameter(), null);
            }
        }
        // Vẽ item
        List<Item> items = model.getItems();
        for (Item item : items) {
            g.drawImage(item.getIcon(), item.getX() - 10, item.getY() - 10, SIZE, SIZE, null);
        }
        // Vẽ thanh gỗ(Tâm thanh gỗ ở góc trên trái)
        g.drawImage(model.getPaddleImage(), model.getPaddle().getX(), model.getPaddle().getY(), model.getPaddle().getWidth(), model.getPaddle().getHeight(), null);
        //Laser 2
        if (model.getPaddle().getLaserTime() > -1
                && model.getPaddle().getLaserTime() < 20) {
            g.drawImage(GameModel.iconItem[5], model.getPaddle().getMidpoint() - 10, 0, 20, 680, null);
        }
        //Vẽ thời gian hiệu lực item LASER
        if (model.getPaddle().getLaserTime() > -1) {
            g.setColor(Color.black);
            g.drawImage(GameModel.iconItem[4], 20, 680, 20, 20, null);
            g.drawString(String.format("%.2fs", model.getPaddle().getLaserTime() / 100), 45, 696);
        }
        //Vẽ thời gian hiệu lực item LONGER
        if (model.getPaddle().getLongerTime() > -1) {
            g.setColor(Color.black);
            g.drawImage(GameModel.iconItem[6], 20, 640, 20, 20, null);
            g.drawString(String.format("%.2fs", model.getPaddle().getLongerTime() / 100), 45, 656);
        }
        //Vẽ thời gian hiệu lực item SHORTER
        if (model.getPaddle().getShorterTime() > -1) {
            g.setColor(Color.black);
            g.drawImage(GameModel.iconItem[10], 20, 620, 20, 20, null);
            g.drawString(String.format("%.2fs", model.getPaddle().getShorterTime() / 100), 45, 636);
        }
        //Vẽ thời gian hiệu lực item WALL
        if (model.getPaddle().getWallTime() > -1) {
            g.setColor(Color.black);
            g.drawImage(GameModel.iconItem[14], 20, 600, 20, 20, null);
            g.drawString(String.format("%.2fs", model.getPaddle().getWallTime() / 100), 45, 616);
            for (byte i = 0; i < 48; i += 3) {
                g.drawImage(blockImage[8][2], i * 20, 710, 60, 20, null);
            }
        }
        // Game over
        if (model.isGameOver()) {
            g.setColor(Color.blue);
            g.fillRect(0, 240, 960, 200);
            g.setColor(Color.black);
            g.setFont(model.getInkFree());
            FontMetrics fontMetricas = getFontMetrics(g.getFont());
            g.drawString("GAME OVER", (GameModel.WIDTH / 2) - (fontMetricas.stringWidth("GAME OVER") / 2), 360);
        }
        // Next level
        if (model.isNextLevel()) {
            g.setColor(Color.black);
            g.setFont(model.getInkFree());
            g.drawString("CLEAR", 387, 360);
        }
        //Pause
        if (model.isPause()) {
            g.setFont(tahoma);
            g.setColor(Color.blue);
            g.fillRect(0, 240, 960, 40);
            g.fillRect(0, 300, 960, 40);
            g.fillRect(0, 360, 960, 40);
            g.fillRect(0, 420, 960, 60);
            g.setColor(Color.white);
            g.drawString(model.isMusicOn() ? "Tắt âm" : "Bật âm", 445, 270);
            g.drawString("Chơi tiếp", 439, 330);
            g.drawString("Ván mới", 440, 390);
            g.drawString("Màn hình chính", 410, 460);
        }

        // kẻ ô vuông
        g.setColor(Color.BLUE);
//        for (int i = 0; i <= 48; i++) {
//            int x = i * 20;
//            g.drawLine(x, 0, x, 720);
//            if (i <= 720 / 20) {
//                int y = i * 20;
//                g.drawLine(0, y, 960, y);
//            }
//        }
    }

    private void drawMenu(Graphics g) {
    }

    private void drawInfo(Graphics g) {
        g.drawImage(backgroundImages[0], 0, 0, 960, 720, null);
        g.setColor(white);
        g.setFont(arial);
        g.fillRect(0, 0, 960, 720);
        g.setColor(Color.black);
        g.drawString("CHÀO MỪNG BẠN ĐẾN VỚI MANY BRICKS BREAKER!", 280, 80);
        g.drawString("MANY BRICKS BREAKER là một trò chơi phá khối vui nhộn thư giãn nhưng đồng thời cũng rất thú vị.", 120, 120);
        g.drawString("Trò chơi được lấy cảm hứng từ trò chơi arcade Atari Breakout huyền thoại từ những năm 70. Tuy nhiên phiên", 80, 140);
        g.drawString("bản này có một sự thay đổi cho phép bạn có được hàng trăm quả bóng cùng một lúc.", 80, 160);
        g.drawString("Mục tiêu của trò chơi rất đơn giản: phá vỡ tất cả các viên gạch và dành chiến thắng. Một số viên gạch khi bị vỡ", 80, 180);
        g.drawString("sẽ rơi ra item , được giải thích ở bên dưới.", 80, 200);
        int i = 0;
        while (i < 13) {
            g.drawImage(iconItem[i], 80, 220 + i * 20, 20, 20, null);
            g.drawString(detailItem[i], 120, 240 + i * 20 - 4);
            ++i;
        }
        g.drawString("Khi rơi hết bóng bạn có thể double click chuột trái để bắn một quả mới", 80, 520);
        g.drawString("Ấn phím Enter để tạm dừng trò chơi", 80, 540);
        g.drawString("Mọi đóng góp ý kiến xin liên hệ.", 80, 560);
        g.drawString("Email: tuyenn9448@gmail.com", 370, 680);
        g.setColor(Color.blue);
        g.drawString("Tuyenngoc", 440, 660);
        g.drawLine(440, 661, 525, 661);
    }
}
