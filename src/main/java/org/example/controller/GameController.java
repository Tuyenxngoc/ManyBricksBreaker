package org.example.controller;

import org.example.model.*;
import org.example.ulti.FileLoader;
import org.example.ulti.Level;
import org.example.view.GameView;

import javax.sound.sampled.Clip;
import javax.swing.*;
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
            List<Ball> balls = model.getBalls();
            for (Ball ball : balls) {
                ball.handleEvent();
                this.checkCollision(ball);
                this.checkCollisionBrick(ball);
            }
            List<Item> items = model.getItems();
            for (Item item : items) {
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
        model.setTime(Level.getTime(lv));
        model.setIndexBackgroundImage(lv);
    }

    private void win() {
        sound("win");
        this.resetGame();
        model.getPaddle().reset();
        model.setRunning(false);
        model.setNextLevel(true);
    }

    private void endGame() {
        model.setGameOver(true);
        model.setRunning(false);
        this.timer.stop();
    }

    private void resetGame() {
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
            case BOMB -> {
                model.getBalls().clear();
            }
            case EXTRA_LIFE -> {
                model.extraLife();
            }
            case FIRE_BALL -> {
                Ball ball = model.getBalls().get((int) (Math.random() * model.getBalls().size()));
                ball.setFireBall(true);
                ball.setFireBallTime(item.getTime());
            }
            case KILL -> {
                model.getBalls().remove((int) (Math.random() * model.getBalls().size()));
            }
            case LASER -> {
                model.getPaddle().setLaserTime(item.getTime());
            }
            case LONGER -> {
                model.getPaddle().longer(0.3);
                model.getPaddle().setLongerTime(item.getTime());
            }
            case PLUS_FIVE -> {
                model.increaseTime(5);
            }
            case PLUS_SIX -> {
                model.increaseTime(6);
            }
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
            case STAR -> {
                model.increaseStar();
            }
            case TIMES_SIX -> {
                System.out.println("update...");
            }
            case TIMES_THREE -> {
                List<Ball> list_tmp = new ArrayList<>();
                for (Ball ball_tmp : model.getBalls()) {
                    Ball ball1 = new Ball(ball_tmp.getX() + SIZE, ball_tmp.getY(), SIZE, 6, 6, UP, ball_tmp.getDirectionY());
                    Ball ball2 = new Ball(ball_tmp.getX() - SIZE, ball_tmp.getY(), SIZE, 6, 6, DOWN, ball_tmp.getDirectionY());
                    list_tmp.add(ball1);
                    list_tmp.add(ball2);
                    if (list_tmp.size() > 15) {
                        break;
                    }
                }
                model.getBalls().addAll(list_tmp);
            }
            case WALL -> {
                model.getPaddle().setWallTime(item.getTime());
            }
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
                    && model.getPaddle().getMidpoint() > brick.getLowerLeftCorner().x
                    && model.getPaddle().getMidpoint() < brick.getLowerRightCorner().x) {
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
        if (model.getTime() <= 0.0) {
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
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == 32) {
            if (model.isPause()) {
                model.setPause(false);
                if (!timer.isRunning()) {
                    this.timer.start();
                }
            } else {
                model.setPause(true);
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
        if (!model.isRunning() && model.getBalls().size() == 1) {
            model.getBalls().get(0).setX(e.getX());
        }
        model.getPaddle().setX(e.getX());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        sound("click");

        if (model.isNextLevel()) {
            model.increaseLevel();// Tăng lv lên 1
            nextLevel(model.getLevel());// Lấy ra list brick theo lv
            addBall();// Thêm 1 quả bóng
            model.setTim(3);// Cho tim về mặc định
            model.setNextLevel(false);
            sound("neutral");
            return;
        }

        if (!model.isRunning()) {
            model.setRunning(true);
        }
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
