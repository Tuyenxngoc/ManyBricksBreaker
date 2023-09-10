package org.example.model;

public class Paddle {
    private int x;
    private int y;
    private int width;
    private int height;


    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getXMin() {
        return x - width / 2;
    }

    public int getXMax() {
        return x + width / 2;
    }

    public int getXDraw() {
        return x - width / 2;
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

}
