package org.example.view;

import org.example.model.*;

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
        this.drawGame(g);

        if (model.isDebug()) {
            drawSquare(g);
        }
    }

    private void draw(Graphics g) {
        // Vẽ nền
        g.drawImage(model.getBackgroundImage(), 0, 0, this);
        // Vẽ thông tin thời gian game và số mạng
        g.setColor(whiteColor);
        g.fillRoundRect(280, 10, 100, 28, 10, 10);
        g.fillRoundRect(580, 10, 100, 28, 10, 10);
        g.setColor(blueColor);
        g.setFont(tahomaFont);
        g.drawString(model.getTim() + "x", 320, 30);
        g.drawString(model.getTimeDraw(), 620, 30);
        // Vẽ các viên gạch
        List<Brick> bricks = model.getBricks();
        for (Brick brick : bricks) {
            g.drawImage(brick.getImage(), brick.getUpperLeftCorner().x, brick.getUpperLeftCorner().y, brick.getWidth(),
                    brick.getHeight(), null);
        }
        // Vẽ các viên bi
        List<Ball> balls = model.getBalls();
        for (Ball ball : balls) {
            if (ball.isFireBall()) {
                g.setColor(Color.red);
                g.fillOval(ball.getXDraw(), ball.getYDraw(), ball.getDiameter(), ball.getDiameter());
                // Vẽ thời gian hiệu lực item FIREBALL
                g.drawImage(GameModel.iconItem[2], 20, 660, 20, 20, null);
                g.setColor(Color.black);
                g.drawString(String.format("%.2fs", ball.getFireBallTime()), 45, 676);
            } else {
                g.drawImage(model.getBallImage(), ball.getXDraw(), ball.getYDraw(), ball.getDiameter(),
                        ball.getDiameter(), null);
            }
        }
        // Vẽ item
        List<Item> items = model.getItems();
        for (Item item : items) {
            g.drawImage(item.getIcon(), item.getX() - 10, item.getY() - 10, SIZE, SIZE, null);
        }
        // Vẽ thanh gỗ(Tâm thanh gỗ ở góc trên trái)
        g.drawImage(model.getPaddleImage(), model.getPaddle().getX(), model.getPaddle().getY(),
                model.getPaddle().getWidth(), model.getPaddle().getHeight(), null);
        // Laser 2
        if (model.getPaddle().getLaserTime() > -1
                && model.getPaddle().getLaserTime() < 20) {
            g.drawImage(GameModel.iconItem[5], model.getPaddle().getMidpoint() - 10, 0, 20, 680, null);
        }
        // Vẽ thời gian hiệu lực item LASER
        if (model.getPaddle().getLaserTime() > -1) {
            g.setColor(Color.black);
            g.drawImage(GameModel.iconItem[4], 20, 680, 20, 20, null);
            g.drawString(String.format("%.2fs", model.getPaddle().getLaserTime() / 100), 45, 696);
        }
        // Vẽ thời gian hiệu lực item LONGER
        if (model.getPaddle().getLongerTime() > -1) {
            g.setColor(Color.black);
            g.drawImage(GameModel.iconItem[6], 20, 640, 20, 20, null);
            g.drawString(String.format("%.2fs", model.getPaddle().getLongerTime() / 100), 45, 656);
        }
        // Vẽ thời gian hiệu lực item SHORTER
        if (model.getPaddle().getShorterTime() > -1) {
            g.setColor(Color.black);
            g.drawImage(GameModel.iconItem[10], 20, 620, 20, 20, null);
            g.drawString(String.format("%.2fs", model.getPaddle().getShorterTime() / 100), 45, 636);
        }
        // Vẽ thời gian hiệu lực item WALL
        if (model.getPaddle().getWallTime() > -1) {
            g.setColor(Color.black);
            g.drawImage(GameModel.iconItem[14], 20, 600, 20, 20, null);
            g.drawString(String.format("%.2fs", model.getPaddle().getWallTime() / 100), 45, 616);
            for (byte i = 0; i < 48; i += 3) {
                g.drawImage(blockImages[8][2], i * 20, 710, 60, 20, null);
            }
        }

        // Game control
        if (model.isGameOver()) {// Game over
            this.drawGameOver(g);
        } else if (model.isNextLevel()) {// Next level
            g.setColor(Color.black);
            g.setFont(model.getInkFree());
            g.drawString("CLEAR", 387, 360);
        } else if (model.isPaused()) {// Pause
            this.drawGamePause(g);
        }
    }

    private void drawGamePause(Graphics g) {
        // Xóa màn hình với màu trắng
        g.setColor(whiteColor);
        g.fillRect(0, 0, 960, 720);

        // Thiết lập font chữ và fontMetrics
        g.setFont(tahomaFont);
        FontMetrics fontMetrics = g.getFontMetrics();

        // Lặp qua các nút trong danh sách nút điều khiển game
        for (ButtonGame button : model.getGameControlButtons()) {
            if (button.getType().equals(ButtonType.GAME_CONTROL_PAUSE)) {
                this.drawButtonGame(g, button, fontMetrics);
            }
        }
    }

    private void drawGameOver(Graphics g) {
        // Xóa màn hình với màu trắng
        g.setColor(whiteColor);
        g.fillRect(0, 0, 960, 720);

        // Vẽ ô chữ nhật màu xanh
        g.setColor(Color.blue);
        g.fillRect(0, 240, 960, 200);

        // Thiết lập font chữ và fontMetrics cho phần "GAME OVER"
        g.setColor(Color.black);
        g.setFont(inkFree60Font);
        FontMetrics fontMetrics = g.getFontMetrics();
        String gameOverText = "GAME OVER";
        int gameOverTextWidth = fontMetrics.stringWidth(gameOverText);
        int x = (GameModel.WIDTH - gameOverTextWidth) / 2;
        int y = 360;
        // Vẽ chữ "GAME OVER"
        g.drawString(gameOverText, x, y);

        // Thiết lập font chữ và fontMetrics cho các button
        g.setFont(tahomaFont);
        fontMetrics = g.getFontMetrics();

        // Lặp qua các nút trong danh sách nút điều khiển game
        for (ButtonGame button : model.getGameControlButtons()) {
            if (button.getType().equals(ButtonType.GAME_CONTROL_LOST)) {
                this.drawButtonGame(g, button, fontMetrics);
            }
        }
    }

    private void drawButtonGame(Graphics g, ButtonGame button, FontMetrics fontMetrics) {
        // Vẽ nền cho button với màu xám
        g.setColor(grayColor);
        g.fillRect(button.getX(), button.getY(), button.getWidth(), button.getHeight());
        g.setColor(Color.white);

        int stringWidth = fontMetrics.stringWidth(button.getName());
        int stringHeight = fontMetrics.getHeight();

        // Tính toán vị trí x và y để vẽ chữ vào giữa của button
        int x = button.getX() + (button.getWidth() - stringWidth) / 2;
        int y = button.getY() + ((button.getHeight() - stringHeight) / 2) + fontMetrics.getAscent();

        // Vẽ chữ vào vị trí đã tính toán
        g.drawString(button.getName(), x, y);

        if (button.getImg2() != null) {
            Img img2 = button.getImg2();
            // Tính toán vị trí x cho chữ
            int imageX = x + stringWidth;
            //Vẽ chữ
            g.drawString(img2.getName(), imageX, y);
            // Tính toán vị trí x cho hình ảnh
            imageX += fontMetrics.stringWidth(img2.getName());
            // Vẽ hình ảnh ngay sau bên phải chữ
            g.drawImage(img2.getImage(), imageX, y - fontMetrics.getAscent(), img2.getWidth(), img2.getHeight(),
                    null);
        }
    }

    private void drawSquare(Graphics g) {
        // kẻ ô vuông
        g.setColor(Color.blue);
        // Kẻ các đường ngang
        for (int y = 0; y <= GameModel.HEIGHT; y += SIZE) {
            g.drawLine(0, y, GameModel.WIDTH, y);
        }

        // Kẻ các đường dọc
        for (int x = 0; x <= GameModel.WIDTH; x += SIZE) {
            g.drawLine(x, 0, x, GameModel.HEIGHT);
        }
    }

    private void drawGame(Graphics g) {
        if (model.isMainMenu()) {
            drawMainMenu(g);
        } else if (model.isShop()) {
            drawShop(g);
        } else if (model.isInfo()) {
            drawInfo(g);
        } else {
            draw(g);
        }
    }

    private void drawMainMenu(Graphics g) {
        g.drawImage(image_menu, 0, 0, 960, 720, null);
        g.drawImage(menuImages[0], 700, 20, 200, 40, null);
        g.setColor(Color.blue);
        g.setFont(tahomaFont);
        g.drawString("" + model.getLevel(), 716, 52);
        g.setColor(Color.white);
        g.drawString("" + model.getStar(), 790, 45);
        for (ButtonGame button2 : model.getButtonsMainMenu()) {
            drawButtons(g, button2);
        }
    }

    private void drawButtons(Graphics g, ButtonGame button) {
        if (button.getImg() != null) {
            int x = button.getX();
            int y = button.getY();
            int width = button.getWidth();
            int height = button.getHeight();

            if (button.isHover()) {
                x -= (int) (width * 0.1);
                y -= (int) (height * 0.1);
                width *= 1.2;
                height *= 1.2;
            }

            g.drawImage(button.getImg().getImage(), x, y, width, height, null);
        }
    }

    private void drawShop(Graphics g) {
        // Vẽ nền
        g.drawImage(backgroundImages[7], 0, 0, 960, 720, null);
        // Vẽ thanh lv, star
        g.drawImage(menuImages[0], 700, 20, 200, 40, null);
        // Vẽ chữ vào
        g.setFont(tahomaFont);
        g.setColor(Color.blue);
        g.drawString("" + model.getLevel(), 716, 52);
        g.setColor(Color.white);
        g.drawString("" + model.getStar(), 790, 45);

        for (ButtonGame button : model.getButtonShop()) {

            if (model.isShoppingForBall()) {// Shop ball
                if (button.getType().equals(ButtonType.SHOP_BALLS)) {
                    this.drawProduct(g, button, model.getBallImageStatus(), model.getBallImagePrice());
                }
            } else {// Shop paddle
                if (button.getType().equals(ButtonType.SHOP_PADDLE)) {
                    this.drawProduct(g, button, model.getPaddleImageStatus(), model.getPaddleImagePrice());
                }
            }

            if (button.getType().equals(ButtonType.CLOSE)) {//Close
                drawButtons(g, button);

            } else if (button.getType().equals(ButtonType.SHOP_CONTROL)) {//Control
                if (button.isSelect()) {
                    g.drawImage(shopImages[1], button.getX(), button.getY(), button.getWidth(), button.getHeight(),
                            null);
                } else {
                    g.drawImage(shopImages[2], button.getX(), button.getY(), button.getWidth(), button.getHeight(),
                            null);
                }
                Img img2 = button.getImg2();
                g.drawImage(img2.getImage(), img2.getX(), img2.getY(), img2.getWidth(), img2.getHeight(), null);
            }
        }
    }

    private void drawProduct(Graphics g, ButtonGame button, boolean[] isPurchased, int[] prices) {
        int x = button.getX();
        int y = button.getY();
        int width = button.getWidth();
        int height = button.getHeight();

        // Vẽ khung
        g.drawImage(button.getImg().getImage(), x, y, width, height, null);
        // Vẽ tên
        g.setColor(colorShop);
        g.drawString(button.getNameDraw(), x + 8, y + 70);
        // Vẽ img2
        Img img2 = button.getImg2();
        g.drawImage(img2.getImage(), img2.getX(), img2.getY(), img2.getWidth(), img2.getHeight(), null);

        // Kiểm tra đã mua hay chưa
        if (!isPurchased[button.getImg2().getIndex()]) {
            // Vẽ giá
            g.setColor(blackColor);
            g.fillRoundRect(button.getX() - 1, y - 1, width, height, 11, 11);
            g.drawImage(shopImages[3], x + 62, y + 47, 60, 24, null);
            g.setColor(orangeColor);
            g.drawString(prices[button.getImg2().getIndex()] + "", x + 85, y + 65);
        } else if (model.getBallImageStatus()[button.getImg2().getIndex()]
                && model.getSelectedBallImageIndex() == button.getImg2().getIndex()) {
            // Vẽ khung xanh
            g.setColor(lightBlueColor);
            g.fillRoundRect(button.getX(), y, width, height, 11, 11);
        }
    }

    private void drawInfo(Graphics g) {
        // Vẽ nền trước
        g.drawImage(backgroundImages[0], 0, 0, 960, 720, null);

        // Thiết lập font và màu chữ
        g.setFont(arialFont);
        g.setColor(Color.black);

        // Vẽ các đoạn văn bản
        g.setColor(whiteColor);
        g.fillRect(0, 0, 960, 720);

        g.setColor(Color.black);
        g.drawString("CHÀO MỪNG BẠN ĐẾN VỚI MANY BRICKS BREAKER!", 280, 80);
        g.drawString("MANY BRICKS BREAKER là một trò chơi phá khối vui nhộn thư giãn nhưng đồng thời cũng rất thú vị.",
                80, 120);
        g.drawString(
                "Trò chơi được lấy cảm hứng từ trò chơi arcade Atari Breakout huyền thoại từ những năm 70. Tuy nhiên phiên",
                80, 140);
        g.drawString("bản này có một sự thay đổi cho phép bạn có được hàng trăm quả bóng cùng một lúc.", 80, 160);
        g.drawString(
                "Mục tiêu của trò chơi rất đơn giản: phá vỡ tất cả các viên gạch và dành chiến thắng. Một số viên gạch khi bị vỡ",
                80, 180);
        g.drawString("sẽ rơi ra item, được giải thích ở bên dưới.", 80, 200);

        // Vẽ các item description
        for (byte i = 0; i < 13; i++) {
            g.drawImage(iconItem[i], 80, 220 + i * 20, 20, 20, null);
            g.drawString(detailItem[i], 120, 240 + i * 20 - 4);
        }

        g.drawString("Khi rơi hết bóng bạn có thể click chuột trái để bắn một quả mới", 80, 520);
        g.drawString("Ấn phím cách để tạm dừng trò chơi", 80, 540);
        g.drawString("Hình ảnh trong game được lấy từ game The Sandbox, Brick Mania", 80, 560);
        g.drawString("Mọi đóng góp ý kiến xin liên hệ.", 80, 580);
        g.drawString("Email: tuyenn9448@gmail.com", 370, 680);

        // Vẽ thông tin liên hệ
        g.setColor(Color.blue);
        g.drawString("Tuyenngoc", 440, 660);
        g.drawLine(440, 661, 525, 661);

        // Vẽ các nút
        for (ButtonGame button2 : model.getButtonInfo()) {
            drawButtons(g, button2);
        }
    }

}
