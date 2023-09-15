package org.example.model;

import java.awt.*;

public class Brick {
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isVisible;
    private Item item;
    private int count;
    private Image image;

    private final Point upperLeftCorner;
    private final Point upperRightCorner;
    private final Point lowerLeftCorner;
    private final Point lowerRightCorner;

    public Brick(int x, int y, int width, int height, int count, Image image, Item item) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.count = count;
        this.isVisible = true;
        this.image = image;

        this.upperLeftCorner = new Point(x - width / 2, y - height / 2);
        this.upperRightCorner = new Point(x + width / 2, y - height / 2);

        this.lowerLeftCorner = new Point(x - width / 2, y + height / 2);
        this.lowerRightCorner = new Point(x + width / 2, y + height / 2);
        this.item = item;
    }

    public Brick(int x, int y, int width, int height, Image image, Item item) {
        this(x, y, width, height, 0, image, item);
    }

    public Brick(int x, int y, int width, int height, Image image) {
        this(x, y, width, height, 0, image, null);
    }

    public int getX() {
        return x;
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

    public Image getImage() {
        return image;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public Item getItem() {
        return item;
    }

    public Point getUpperLeftCorner() {
        return upperLeftCorner;
    }

    public Point getUpperRightCorner() {
        return upperRightCorner;
    }

    public Point getLowerLeftCorner() {
        return lowerLeftCorner;
    }

    public Point getLowerRightCorner() {
        return lowerRightCorner;
    }
}
