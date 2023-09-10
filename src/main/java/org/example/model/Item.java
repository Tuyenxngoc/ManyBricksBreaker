package org.example.model;

import java.awt.*;

public class Item {
    private int x;
    private int y;
    private ItemType type;
    private boolean isVisible;
    private Image icon;

    public Item(int x, int y, ItemType type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.isVisible = true;
        this.setIcon(type);
    }

    private void setIcon(ItemType type) {
        switch (type) {
            case CONG3 -> {
                this.icon = GameModel.iconItem[0];
            }
            case THEMMANG -> {
                this.icon = GameModel.iconItem[2];
            }
            case THEMTG -> {
                this.icon = null;
            }
            case TANGTHANHDO -> {
                this.icon = GameModel.iconItem[4];
            }
            case THUNHOTHANHDO -> {
                this.icon = GameModel.iconItem[11];
            }
            case BOM -> {
                this.icon = GameModel.iconItem[12];
            }
            case MAXTHANHDO -> {
                this.icon = GameModel.iconItem[3];
            }
            case NHAN3 -> {
                this.icon = GameModel.iconItem[1];
            }
            case XOABONG -> {
                this.icon = GameModel.iconItem[10];
            }
            case BONGLUA -> {
                this.icon = GameModel.iconItem[6];
            }
            case LAZE -> {
                this.icon = GameModel.iconItem[7];
            }
            case SAO -> {
                this.icon = GameModel.iconItem[5];
            }
            default -> {
                this.icon = null;
            }
        }
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

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
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

    public void setIcon(Image icon) {
        this.icon = icon;
    }

    public void increaseY() {
        if (this.y < GameModel.HEIGHT) {
            this.y += this.type.equals(ItemType.SAO) ? 4 : 2;
        }
    }

}
