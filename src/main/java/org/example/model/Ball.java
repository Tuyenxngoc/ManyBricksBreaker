package org.example.model;

import java.awt.*;

public class Ball {
    private int x;
    private int y;
    private int diameter;
    private int xSpeed;
    private int ySpeed;
    private Image image;
    private BallDirection directionX;
    private BallDirection directionY;

    private boolean isVisible;

    public Ball(int x, int y, int diameter, int xSpeed, int ySpeed, BallDirection directionX, BallDirection directionY) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.directionX = directionX;
        this.directionY = directionY;
        this.isVisible = true;
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

    public Rectangle getBounds() {
        return new Rectangle(x, y, diameter, diameter);
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

    public boolean isVisible() {
        return isVisible;
    }

    public void setxSpeed(int xSpeed) {
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

    public Image getImage() {
        return image;
    }

    public void setX(int x) {
        if (x <= getPerimeter() || x >= GameModel.WIDTH - getPerimeter()) {
            return;
        }
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public BallDirection getDirectionX() {
        return directionX;
    }

    public BallDirection getDirectionY() {
        return directionY;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
