package org.example.model;

import java.awt.*;

public class Button {
    private int x;
    private int y;
    private int width;
    private int height;
    private String name;
    private boolean isSelect;
    private Color color;
    private Color color2;
    private Image img;

    public Button() {
    }

    public Button(int x, int y, int width, int height, String name, boolean isSelect, Color color, Color color2, Image img) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;
        this.isSelect = isSelect;
        this.color = color;
        this.color2 = color2;
        this.img = img;
    }

    public Button(int x, int y, int width, int height, String name, boolean isSelect, Image img) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;
        this.isSelect = isSelect;
        this.img = img;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }
}
