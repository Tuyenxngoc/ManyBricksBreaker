package org.example.model;

import static org.example.model.GameModel.PADDLE_WIDTH;

public class Paddle {
    private int x;
    private int y;
    private int width;
    private int height;

    //LASER
    private boolean laser;
    private int laserTime;

    //LONGER
    private int longerTime;

    //SHORTER
    private int shorterTime;

    //WALL
    private int wallTime;


    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.laser = false;
        this.laserTime = -1;

        this.longerTime = -1;
        this.shorterTime = -1;
        this.wallTime = -1;
    }

    public void handleEvent() {
        increaseLaserTime();
        increaseLongerTime();
        increaseShorterTime();
        increaseWallTime();
    }

    public int getX() {
        return x;
    }

    public int getXMax() {
        return x + width / 2;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setX(int x) {
        this.x = x - width / 2;
    }

    public int getMidpoint() {
        return x + width / 2;
    }

    public void wall(double x) {
        width = (int) (PADDLE_WIDTH + PADDLE_WIDTH * x);
        if (wallTime == -1) {
            this.x = (int) (this.x - (PADDLE_WIDTH * x / 2));
        }
    }

    public void longer(double x) {
        width = (int) (PADDLE_WIDTH + PADDLE_WIDTH * x);
        if (longerTime == -1) {
            this.x = (int) (this.x - (PADDLE_WIDTH * x / 2));
        }
    }

    public void shorter(double x) {
        width = (int) (PADDLE_WIDTH - PADDLE_WIDTH * x);
        if (shorterTime == -1) {
            this.x = (int) (this.x + (PADDLE_WIDTH * x / 2));
        }
    }

    public double getLaserTime() {
        return laserTime;
    }

    public void setLaserTime(int laserTime) {
        if (this.laserTime == -1) {
            this.laserTime = laserTime * 100;
        }
    }

    public void reset() {
        this.laserTime = -1;
        this.longerTime = -1;
        this.shorterTime = -1;
        this.wallTime = -1;
        resetPaddleWidth();
    }

    public void increaseLaserTime() {
        if (laserTime > -1) {
            laserTime--;
            laser = (laserTime == 0);
        }
    }

    public boolean isLaser() {
        return laser;
    }

    public void setLaser(boolean laser) {
        this.laser = laser;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public double getLongerTime() {
        return longerTime;
    }

    public void increaseLongerTime() {
        if (longerTime > -1) {
            longerTime--;
            if (longerTime == 0) {
                resetPaddleWidth();
            }
        }
    }

    public void increaseShorterTime() {
        if (shorterTime > -1) {
            shorterTime--;
            if (shorterTime == 0) {
                resetPaddleWidth();
            }
        }
    }

    public void increaseWallTime() {
        if (wallTime > -1) {
            wallTime--;
        }
    }

    public void resetPaddleWidth() {
        double x = (double) (width - PADDLE_WIDTH) / PADDLE_WIDTH;
        width = PADDLE_WIDTH;
        this.x = (int) (this.x + (PADDLE_WIDTH * x / 2));
    }

    public void setLongerTime(int longerTime) {
        this.longerTime = longerTime * 100;
    }

    public void setShorterTime(int shorterTime) {
        this.shorterTime = shorterTime * 100;
    }

    public double getShorterTime() {
        return shorterTime;
    }

    public double getWallTime() {
        return wallTime;
    }

    public void setWallTime(int wallTime) {
        this.wallTime = wallTime * 100;
    }
}
