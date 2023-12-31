package org.example.model;

import java.awt.*;

public class Item {
    private final int x;
    private int y;
    private final ItemType type;
    private boolean isVisible;
    private Image icon;
    private int time;
    private String soundName;

    public Item(int x, int y, ItemType type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.isVisible = true;
        this.setIcon(type);
        this.soundName = "power21";
    }

    private void setIcon(ItemType type) {
        switch (type) {
            case BOMB -> this.icon = GameModel.iconItem[0];
            case EXTRA_LIFE -> this.icon = GameModel.iconItem[1];
            case FIRE_BALL -> {
                this.time = 5;
                this.soundName = "fire_destroy";
                this.icon = GameModel.iconItem[2];
            }
            case KILL -> this.icon = GameModel.iconItem[3];
            case LASER -> {
                this.time = 2;
                this.icon = GameModel.iconItem[4];
            }
            case LONGER -> {
                this.time = 10;
                this.icon = GameModel.iconItem[6];
            }
            case PLUS_FIVE -> this.icon = GameModel.iconItem[7];
            case PLUS_SIX -> this.icon = GameModel.iconItem[8];
            case PLUS_THREE -> this.icon = GameModel.iconItem[9];
            case SHORTER -> {
                this.time = 10;
                this.icon = GameModel.iconItem[10];
            }
            case STAR -> this.icon = GameModel.iconItem[11];
            case TIMES_SIX -> this.icon = GameModel.iconItem[12];
            case TIMES_THREE -> this.icon = GameModel.iconItem[13];
            case WALL -> {
                this.time = 10;
                this.icon = GameModel.iconItem[14];
            }
            default -> this.icon = null;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public ItemType getType() {
        return type;
    }


    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public Image getIcon() {
        return icon;
    }


    public void increaseY() {
        if (this.y < GameModel.HEIGHT) {
            this.y += this.type.equals(ItemType.STAR) ? 4 : 2;
        }
    }

    public int getTime() {
        return time;
    }


    public String getSoundName() {
        return soundName;
    }

}
