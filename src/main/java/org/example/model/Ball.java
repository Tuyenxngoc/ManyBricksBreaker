package org.example.model;

import static org.example.model.GameModel.TIME_DECREMENT;

public class Ball {
    private int x;
    private int y;
    private int diameter;
    private int xSpeed;
    private int ySpeed;
    private BallDirection directionX;
    private BallDirection directionY;

    private boolean isVisible;
    private boolean fireBall;
    private double fireBallTime;

    public Ball(int x, int y, int diameter, int xSpeed, int ySpeed, BallDirection directionX, BallDirection directionY) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.directionX = directionX;
        this.directionY = directionY;
        this.isVisible = true;
        this.fireBall = false;
    }

    public int getXDraw() {
        return x - getPerimeter();
    }

    public int getYDraw() {
        return y - getPerimeter();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDiameter() {
        return diameter;
    }

    public int getPerimeter() {
        return diameter / 2;
    }

    public void reduceY() {
        this.y -= this.ySpeed;
        if (this.y <= getPerimeter()) {
            this.y = getPerimeter();
        }
    }

    public void increaseY() {
        this.y += this.ySpeed;
    }

    public void reduceX() {
        this.x -= this.xSpeed;
        if (this.x < getPerimeter()) {
            this.x = getPerimeter();
        }
    }

    public void increaseX() {
        this.x += this.xSpeed;
        if (this.x > GameModel.WIDTH - getPerimeter()) {
            this.x = GameModel.WIDTH - getPerimeter();
        }
    }

    public void move() {
        //Di chuyển hướng x của bóng
        if (this.directionX.equals(BallDirection.UP)) {
            this.increaseX();
        } else if (this.directionX.equals(BallDirection.DOWN)) {
            this.reduceX();
        }
        //Di chuyển hướng y của bóng
        if (this.directionY.equals(BallDirection.UP)) {
            this.increaseY();
        } else if (this.directionY.equals(BallDirection.DOWN)) {
            this.reduceY();
        }
    }

    public void handleEvent() {
        this.move();
        if (isFireBall()) {
            increaseFireBallTime();
        }
    }


    public boolean isVisible() {
        return isVisible;
    }

    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setDirectionX(BallDirection directionX) {
        this.directionX = directionX;
    }

    public void setDirectionY(BallDirection directionY) {
        this.directionY = directionY;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public void setX(int x) {
        if (x <= getPerimeter() || x >= GameModel.WIDTH - getPerimeter()) {
            return;
        }
        this.x = x;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public BallDirection getDirectionY() {
        return directionY;
    }

    public boolean isFireBall() {
        return fireBall;
    }

    public void setFireBall(boolean fireBall) {
        this.fireBall = fireBall;
    }

    public double getFireBallTime() {
        return fireBallTime;
    }

    public void setFireBallTime(double fireBallTime) {
        this.fireBallTime = fireBallTime;
    }

    public void increaseFireBallTime() {
        fireBallTime -= TIME_DECREMENT;
        if (fireBallTime <= 0.0) {
            this.fireBall = false;
        }
    }
}
