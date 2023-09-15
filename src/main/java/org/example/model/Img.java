package org.example.model;

import java.awt.*;

public class Img {
    private int x;
    private int y;
    private int width;
    private int height;
    private String name;
    private Image image;
    private int index;// Xác định vị trí image

    public Img(Image image) {
        this(10, 10, 10, 10, "null", image, 0);
    }

    public Img(String name, int width, int height, Image image) {
        this(10, 10, width, height, name, image, 0);
    }

    public Img(int width, int height, Image image) {
        this(10, 10, width, height, "null", image, 0);
    }

    public Img(int x, int y, int width, int height, Image image) {
        this(x, y, width, height, "", image, 0);
    }

    public Img(int x, int y, int width, int height, Image image, int index) {
        this(x, y, width, height, "", image, index);
    }

    public Img(int x, int y, int width, int height, String name, Image image, int index) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;
        this.image = image;
        this.index = index;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Img{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", image=" + image +
                ", index=" + index +
                '}';
    }
}
