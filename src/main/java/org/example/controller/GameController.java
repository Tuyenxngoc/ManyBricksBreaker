package org.example.controller;

import org.example.model.*;
import org.example.ulti.FileLoader;
import org.example.ulti.Level;
import org.example.view.GameView;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.model.BallDirection.DOWN;
import static org.example.model.BallDirection.UP;
import static org.example.model.GameModel.*;

public class GameController implements KeyListener, ActionListener, MouseListener, MouseMotionListener {

    private final Timer timer;
    private final GameModel model;
    private final GameView view;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;

        // Thiết lập bộ điều khiển cho thanh gỗ
        view.addKeyListener(this);
        view.setFocusable(true);
        view.setFocusTraversalKeysEnabled(false);
        view.addMouseMotionListener(this);
        view.addMouseListener(this);

        this.init();

        // Thiết lập Timer để cập nhật trạng thái trò chơi
        timer = new Timer(10, this);
        timer.start();
    }

    private void init() {
        addBall();
        nextLevel(model.getLevel());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (model.isRunning()) {
            for (Ball ball : model.getBalls()) {
                ball.handleEvent();
                this.checkCollision(ball);
                this.checkCollisionBrick(ball);
            }
            for (Item item : model.getItems()) {
                item.increaseY();
                this.checkCollision(item);
            }
            model.getPaddle().handleEvent();
            model.reduceTime();
            this.removeList();
            this.checkResult();
        }
        view.repaint();
    }

    private void nextLevel(int lv) {
        model.setBricks(Level.getListBricks(lv));
        model.setRemainingTime(Level.getTime(lv));
        model.setSelectedBackgroundImageIndex(lv);
    }

    private void win() {
        sound("win");
        this.resetList();
        model.getPaddle().reset();
        model.setRunning(false);
        model.setNextLevel(true);
    }

    private void endGame() {
        model.setGameOver(true);
        model.setRunning(false);
        this.timer.stop();
    }

    private void resetList() {
        model.getItems().clear();
        model.getBricks().clear();
        model.getBalls().clear();
    }

    private void checkCollision(Item item) {
        Paddle paddle = model.getPaddle();
        if ((item.getY() >= (paddle.getY() - SIZE / 2))
                && (item.getX() >= paddle.getX()
                && item.getX() <= paddle.getX() + paddle.getWidth())) {
            item.setVisible(false);
            this.itemEventHandler(item);
        } else if (item.getY() >= HEIGHT) {
            item.setVisible(false);
        }
    }

    private void itemEventHandler(Item item) {
        sound(item.getSoundName());
        switch (item.getType()) {
            case BOMB -> model.getBalls().clear();
            case EXTRA_LIFE -> model.extraLife();
            case FIRE_BALL -> {
                Ball ball = model.getBalls().get((int) (Math.random() * model.getBalls().size()));
                ball.setFireBall(true);
                ball.setFireBallTime(item.getTime());
            }
            case KILL -> model.getBalls().remove((int) (Math.random() * model.getBalls().size()));
            case LASER -> model.getPaddle().setLaserTime(item.getTime());
            case LONGER -> {
                model.getPaddle().longer(0.3);
                model.getPaddle().setLongerTime(item.getTime());
            }
            case PLUS_FIVE -> model.increaseTime(5);
            case PLUS_SIX -> model.increaseTime(6);
            case PLUS_THREE -> {
                Ball b1 = new Ball(item.getX(), item.getY() - 20, 20, 0, 6, UP, DOWN);
                Ball b2 = new Ball(item.getX() - 20, item.getY() - 20, 20, 6, 6, DOWN, DOWN);
                Ball b3 = new Ball(item.getX() + 20, item.getY() - 20, 20, 6, 6, UP, DOWN);
                model.getBalls().add(b1);
                model.getBalls().add(b2);
                model.getBalls().add(b3);
            }
            case SHORTER -> {
                model.getPaddle().shorter(0.3);
                model.getPaddle().setShorterTime(item.getTime());
            }
            case STAR -> model.increaseStar();
            case TIMES_SIX -> System.out.println("update...");
            case TIMES_THREE -> {
                List<Ball> list_tmp = new ArrayList<>();
                for (Ball ball_tmp : model.getBalls()) {
                    Ball ball1 = new Ball(ball_tmp.getX() + SIZE, ball_tmp.getY(), SIZE, 6, 6, UP,
                            ball_tmp.getDirectionY());
                    Ball ball2 = new Ball(ball_tmp.getX() - SIZE, ball_tmp.getY(), SIZE, 6, 6, DOWN,
                            ball_tmp.getDirectionY());
                    list_tmp.add(ball1);
                    list_tmp.add(ball2);
                    if (list_tmp.size() > 15) {
                        break;
                    }
                }
                model.getBalls().addAll(list_tmp);
            }
            case WALL -> model.getPaddle().setWallTime(item.getTime());
            default -> {
            }
        }
    }

    private void checkCollision(Ball ball) {
        Paddle paddle = model.getPaddle();
        // Kiểm tra va chạm hai bên
        if (ball.getX() <= ball.getPerimeter()) {
            ball.setDirectionX(UP);
            sound("ball");
        } else if (ball.getX() >= (WIDTH - ball.getPerimeter())) {
            ball.setDirectionX(DOWN);
            sound("ball");
        }

        // Kiểm tra va chạm trên dưới
        if (ball.getY() <= ball.getPerimeter()) {
            ball.setDirectionY(UP);
            sound("ball");
        } else if ((ball.getY() >= (paddle.getY() - ball.getPerimeter())) &&
                (ball.getX() >= paddle.getX() && ball.getX() <= paddle.getX() + paddle.getWidth())) {

            ball.setDirectionY(DOWN);
            sound("ball");

            if (ball.getX() < paddle.getX() + SIZE) {
                ball.setDirectionX(DOWN);
                ball.setXSpeed(10);
            } else if (ball.getX() >= paddle.getX() + SIZE && ball.getX() < paddle.getX()
                    + SIZE * 2) {
                ball.setDirectionX(DOWN);
                ball.setXSpeed(8);
            } else if (ball.getX() >= paddle.getX() + SIZE * 2 && ball.getX() < paddle.getX() + SIZE * 3) {
                ball.setDirectionX(DOWN);
                ball.setXSpeed(6);
            } else if (ball.getX() >= paddle.getX() + SIZE * 3 && ball.getX() < paddle.getX() + SIZE * 4) {
                ball.setDirectionX(DOWN);
                ball.setXSpeed(4);
            } else if (ball.getX() >= paddle.getX() + SIZE * 4 && ball.getX() < paddle.getX() + SIZE * 5) {
                ball.setDirectionX(DOWN);
                ball.setXSpeed(2);
            } else if (ball.getX() >= paddle.getX() + SIZE * 5 && ball.getX() < paddle.getX() + SIZE * 6) {
                ball.setDirectionX(UP);
                ball.setXSpeed(2);
            } else if (ball.getX() >= paddle.getX() + SIZE * 6 && ball.getX() < paddle.getX() + SIZE * 7) {
                ball.setDirectionX(UP);
                ball.setXSpeed(4);
            } else if (ball.getX() >= paddle.getX() + SIZE * 7 && ball.getX() < paddle.getX() + SIZE * 8) {
                ball.setDirectionX(UP);
                ball.setXSpeed(6);
            } else if (ball.getX() >= paddle.getX() + SIZE * 8 && ball.getX() < paddle.getX() + SIZE * 9) {
                ball.setDirectionX(UP);
                ball.setXSpeed(8);
            } else if (ball.getX() >= paddle.getX() + SIZE * 9) {
                ball.setDirectionX(UP);
                ball.setXSpeed(10);
            }
        } else if (ball.getY() >= 690 && model.getPaddle().getWallTime() > -1) {
            ball.setDirectionY(DOWN);
            sound("ball");
        } else if (ball.getY() >= (HEIGHT - ball.getPerimeter())) {
            ball.setVisible(false);
        }
    }

    private void checkCollisionBrick(Ball ball) {
        List<Brick> bricks = model.getBricks();

        for (Brick brick : bricks) {
            // Cạnh dưới
            if ((ball.getY() > brick.getY() && ball.getY() <= brick.getY() + SIZE + ball.getySpeed() / 2)
                    && (ball.getX() >= brick.getLowerLeftCorner().x
                    && ball.getX() <= brick.getLowerRightCorner().x)) {
                if (!ball.isFireBall()) {
                    ball.setDirectionY(UP);
                }
                brick.setVisible(false);
            }

            // Cạnh trên
            if ((ball.getY() < brick.getY() && ball.getY() >= brick.getY() - SIZE - ball.getySpeed() / 2)
                    && (ball.getX() >= brick.getUpperLeftCorner().x
                    && ball.getX() <= brick.getUpperRightCorner().x)) {
                if (!ball.isFireBall()) {
                    ball.setDirectionY(DOWN);
                }
                brick.setVisible(false);
            }

            // Cạnh trái
            if ((ball.getX() < brick.getX() && ball.getX() >= brick.getX() - 2 * SIZE - ball.getxSpeed() / 2)
                    && (ball.getY() >= brick.getUpperLeftCorner().y
                    && ball.getY() <= brick.getLowerLeftCorner().y)) {
                if (!ball.isFireBall()) {
                    ball.setDirectionX(DOWN);
                }
                brick.setVisible(false);
            }

            // Cạnh phải
            if ((ball.getX() > brick.getX() && ball.getX() <= brick.getX() + 2 * SIZE + ball.getxSpeed() / 2)
                    && (ball.getY() >= brick.getUpperRightCorner().y
                    && ball.getY() <= brick.getLowerRightCorner().y)) {
                if (!ball.isFireBall()) {
                    ball.setDirectionX(UP);
                }
                brick.setVisible(false);
            }

            if (arePointsOnSameLine(ball.getX(), ball.getY(), brick.getX() + SIZE, brick.getY())) {
                // Góc dưới phải
                if (ball.getY() - brick.getY() >= 15 && ball.getY() - brick.getY() <= SIZE) {
                    if (!ball.isFireBall()) {
                        ball.setDirectionX(UP);
                        ball.setDirectionY(UP);
                    }
                    brick.setVisible(false);
                }

                // Góc trên phải
                if (ball.getY() - brick.getY() >= -SIZE && ball.getY() - brick.getY() <= -15) {
                    if (!ball.isFireBall()) {
                        ball.setDirectionX(UP);
                        ball.setDirectionY(DOWN);
                    }
                    brick.setVisible(false);
                }
            }

            if (arePointsOnSameLine(ball.getX(), ball.getY(), brick.getX() - SIZE, brick.getY())) {
                // Góc trên trái
                if (ball.getY() - brick.getY() >= -SIZE && ball.getY() - brick.getY() <= -15) {
                    if (!ball.isFireBall()) {
                        ball.setDirectionX(DOWN);
                        ball.setDirectionY(DOWN);
                    }
                    brick.setVisible(false);
                }
                // Góc dưới trái
                if (ball.getY() - brick.getY() >= 15 && ball.getY() - brick.getY() <= SIZE) {
                    if (!ball.isFireBall()) {
                        ball.setDirectionX(DOWN);
                        ball.setDirectionY(UP);
                    }
                    brick.setVisible(false);
                }
            }

            if (!brick.isVisible()) {
                sound("ball");
            }
            if (model.getPaddle().isLaser()
                    && model.getPaddle().getMidpoint() >= brick.getLowerLeftCorner().x
                    && model.getPaddle().getMidpoint() <= brick.getLowerRightCorner().x) {
                brick.setVisible(false);
                sound("laser");
            }
        }
    }

    private void removeList() {
        List<Ball> balls = model.getBalls();
        List<Ball> newBalls = new ArrayList<>();
        for (Ball ball : balls) {
            if (ball.isVisible()) {
                newBalls.add(ball);
            }
        }
        model.setBalls(newBalls);

        List<Item> items = model.getItems();
        List<Item> newItems = new ArrayList<>();
        for (Item item : items) {
            if (item.isVisible()) {
                newItems.add(item);
            }
        }
        model.setItems(newItems);

        List<Brick> bricks = model.getBricks();
        List<Brick> newBricks = new ArrayList<>();
        for (Brick brick : bricks) {
            if (brick.isVisible()) {
                newBricks.add(brick);
            } else if (brick.getItem() != null) {
                model.getItems().add(brick.getItem());
            }
        }
        model.setBricks(newBricks);
    }

    private void checkResult() {
        if (model.getRemainingTime() <= 0.0) {
            this.endGame();
        }

        if (model.getBalls().isEmpty()) {
            model.reduceTim();
            model.getPaddle().reset();
            sound("lost");
            if (model.getTim() <= 0) {
                this.endGame();
            } else {
                model.getItems().clear();
                model.setRunning(false);
                addBall();
            }
        }

        if (model.getBricks().isEmpty()) {
            this.win();
        }
    }

    private void sound(String name) {
        if (model.isMusicOn()) {
            Clip music = FileLoader.loadSound(name);
            if (music != null) {
                music.start();
            }
        }
    }

    private boolean arePointsOnSameLine(int x1, int y1, int x2, int y2) {
        // Tính vector AB
        double vectorX = x2 - x1;
        double vectorY = y2 - y1;
        // Kiểm tra xem vector AB có cùng phương với một vector đặc biệt không
        if (vectorX == 0 || vectorY == 0) {
            return true;
        }
        double ratio = vectorX / vectorY;
        return Math.abs(ratio) == 1.0; // Kiểm tra tỷ lệ vectorX/vectorY có bằng 1
    }

    public void addBall() {
        model.getBalls().add(new Ball(model.getPaddle().getXMax(), 670, SIZE, 0, 6, UP, DOWN));
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 32 && model.isPlayingGame()) {
            if (model.isPaused()) {
                model.setPaused(false);
                model.setRunning(true);
                if (!timer.isRunning()) {
                    this.timer.start();
                }
            } else {
                model.setPaused(true);
                model.setRunning(false);
                view.repaint();
                if (timer.isRunning()) {
                    this.timer.stop();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (model.isPlayingGame()) {
            if (!model.isRunning() && !model.isPaused() && model.getBalls().size() == 1) {
                model.getBalls().get(0).setX(e.getX());
            }
            if (!model.isPaused() && !model.isGameOver()) {
                model.getPaddle().setX(e.getX());
            }

            if (model.isPaused()) {
                for (ButtonGame button2 : model.getGameControlButtons()) {
                    if (button2.getType().equals(ButtonType.GAME_CONTROL_PAUSE)) {
                        button2.setHover(checkHover(e.getX(), e.getY(), button2));
                    }
                }
            } else if (model.isGameOver()) {
                for (ButtonGame button2 : model.getGameControlButtons()) {
                    if (button2.getType().equals(ButtonType.GAME_CONTROL_LOST)) {
                        button2.setHover(checkHover(e.getX(), e.getY(), button2));
                    }
                }
            }

        } else if (model.isMainMenu()) {// Hover button menu
            for (ButtonGame button2 : model.getButtonsMainMenu()) {
                button2.setHover(checkHover(e.getX(), e.getY(), button2));
            }

        } else if (model.isShop()) {// Hover button shop
            for (ButtonGame button2 : model.getButtonShop()) {
                if (model.isShoppingForBall()) {
                    if (!button2.getType().equals(ButtonType.SHOP_PADDLE)) {// Nếu ở trang bán bóng thì không cho paddle hover
                        button2.setHover(checkHover(e.getX(), e.getY(), button2));
                    }
                } else {
                    if (!button2.getType().equals(ButtonType.SHOP_BALLS)) {// Ngược lại
                        button2.setHover(checkHover(e.getX(), e.getY(), button2));
                    }
                }
            }

        } else if (model.isInfo()) {// Hover info
            for (ButtonGame button2 : model.getButtonInfo()) {
                button2.setHover(checkHover(e.getX(), e.getY(), button2));
            }
        }
    }

    private boolean checkHover(int x, int y, ButtonGame button2) {
        return x >= button2.getX() && x <= button2.getX() + button2.getWidth()
                && y >= button2.getY() && y <= button2.getY() + button2.getHeight();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        sound("click");

        if (model.isPlayingGame()) {
            if (model.isNextLevel()) {
                startNewLevel();
                return;
            }

            if (!model.isRunning() && !model.isPaused() && model.isPlayingGame()) {
                model.setRunning(true);
            }

            if (model.isPaused()) {
                for (ButtonGame button2 : model.getGameControlButtons()) {
                    if (button2.getType().equals(ButtonType.GAME_CONTROL_PAUSE)) {
                        selected(button2);
                    }
                }
            } else if (model.isGameOver()) {
                for (ButtonGame button2 : model.getGameControlButtons()) {
                    if (button2.getType().equals(ButtonType.GAME_CONTROL_LOST)) {
                        selected(button2);
                    }
                }
            }

        } else if (model.isMainMenu()) {
            for (ButtonGame button2 : model.getButtonsMainMenu()) {
                selected(button2);
            }

        } else if (model.isShop()) {
            for (ButtonGame button2 : model.getButtonShop()) {
                if (button2.isHover()) {
                    button2.setSelect(true);
                    shopHandle(button2);
                } else {
                    if (!button2.getType().equals(ButtonType.SHOP_CONTROL)) {
                        button2.setSelect(false);
                    }
                }
            }

        } else if (model.isInfo()) {
            for (ButtonGame button2 : model.getButtonInfo()) {
                selected(button2);
            }
        }
    }

    private void shopHandle(ButtonGame button) {
        try {
            if (button.getType().equals(ButtonType.SHOP_BALLS)) {
                if (!model.getBallImageStatus()[button.getImg2().getIndex()]) {
                    int price = model.getBallImagePrice()[button.getImg2().getIndex()];
                    // Hiển thị cửa sổ xác nhận
                    int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn mua với giá " + price + " sao không?", "Xác nhận",
                            JOptionPane.YES_NO_OPTION);
                    // Kiểm tra lựa chọn của người dùng
                    if (choice == JOptionPane.YES_OPTION) {
                        if (model.getStar() >= price) {
                            model.setStar(model.getStar() - price);
                            model.getBallImageStatus()[button.getImg2().getIndex()] = true;
                            JOptionPane.showMessageDialog(null, "Chúc mừng bạn đã mua thành công.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Bạn chưa có đủ tiền.");
                        }
                    }
                } else {
                    model.setSelectedBallImageIndex(button.getImg2().getIndex());
                }
            } else if (button.getType().equals(ButtonType.SHOP_PADDLE)) {
                if (!model.getPaddleImageStatus()[button.getImg2().getIndex()]) {
                    int price = model.getPaddleImagePrice()[button.getImg2().getIndex()];
                    // Hiển thị cửa sổ xác nhận
                    int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn mua với giá " + price + " sao không?", "Xác nhận",
                            JOptionPane.YES_NO_OPTION);
                    // Kiểm tra lựa chọn của người dùng
                    if (choice == JOptionPane.YES_OPTION) {
                        if (model.getStar() >= price) {
                            model.setStar(model.getStar() - price);
                            model.getPaddleImageStatus()[button.getImg2().getIndex()] = true;
                            JOptionPane.showMessageDialog(null, "Chúc mừng bạn đã mua thành công.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Bạn chưa có đủ tiền.");
                        }
                    }
                } else {
                    model.setSelectedPaddleImageIndex(button.getImg2().getIndex());
                }

            } else if (button.getType().equals(ButtonType.CLOSE)) {
                model.setClose();

            } else if (button.getType().equals(ButtonType.SHOP_CONTROL)) {
                if (button.getName().equals("ShoppingForBall")) {
                    model.setShoppingForBall(true);
                    model.getShopButtons().get(1).setSelect(false);
                } else {
                    model.setShoppingForBall(false);
                    model.getShopButtons().get(0).setSelect(false);
                }
            }
        } catch (HeadlessException e) {
            e.printStackTrace();
        }
    }

    private void selected(ButtonGame button2) {
        if (button2.isHover()) {
            button2.setSelect(true);
            buttonsHandle(button2);
        } else {
            button2.setSelect(false);
        }
    }

    private void buttonsHandle(ButtonGame button2) {
        switch (button2.getName().toLowerCase()) {
            case "play" -> model.setPlayGame();
            case "options" -> model.setShop();
            case "quests" -> model.setInfo();
            case "close" -> model.setClose();
            case "link" -> model.browse("https://youtu.be/dQw4w9WgXcQ");
            case "restart" -> restartGame();
            case "sound on", "sound off" -> {
                model.setMusicOn(!model.isMusicOn());
                if (model.isMusicOn()) {
                    button2.setName("Sound on");
                } else {
                    button2.setName("Sound off");
                }
                view.repaint();
            }
            case "next level" -> this.skipCurrentLevel();
            case "resume" -> {
                model.setPaused(false);
                model.setRunning(true);
                if (!timer.isRunning()) {
                    timer.start();
                }
            }
            case "home" -> {
                model.setHome();
                if (!timer.isRunning()) {
                    timer.start();
                }
            }
        }
    }

    private void skipCurrentLevel() {
        if (model.getStar() >= model.getNextLevelPrice()) {
            model.setRunning(false);
            this.resetList();
            model.getPaddle().reset();
            model.setGameOver(false);
            model.setPaused(false);
            this.startNewLevel();
            if (!timer.isRunning()) {
                timer.start();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Bạn chưa có đủ tiền.");
        }
    }

    private void restartGame() {
        this.resetList();
        addBall();// Thêm 1 quả bóng
        nextLevel(model.getLevel());// Lấy ra list brick theo lv

        model.getPaddle().reset();
        model.setRunning(false);
        model.setTim(3);// Cho tim về mặc định
        model.setPaused(false);
        model.setGameOver(false);
        if (!timer.isRunning()) {
            timer.start();
        }
    }

    private void startNewLevel() {
        model.increaseLevel();// Tăng lv lên 1
        nextLevel(model.getLevel());// Lấy ra list brick theo lv
        addBall();// Thêm 1 quả bóng
        model.setTim(3);// Cho tim về mặc định
        model.setNextLevel(false);
        model.setPaused(false);
        model.setGameOver(false);
        sound("neutral");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
