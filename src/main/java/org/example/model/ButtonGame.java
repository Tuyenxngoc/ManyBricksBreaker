package org.example.model;

public class ButtonGame {
    private int x;
    private int y;
    private int width;
    private int height;

    private String name;
    private boolean isSelect;
    private boolean isHover;

    private Img img;//Image chính
    private Img img2;//Image phụ thêm
    private Img img3;//Tmage thêm nếu cần

    private ButtonType type;

    public ButtonGame() {
    }

    public ButtonGame(int x, int y, int width, int height, String name, ButtonType type) {
        this(x, y, width, height, name, false, false, null, null, null, type);
    }

    public ButtonGame(int x, int y, int width, int height, String name, Img img, ButtonType type) {
        this(x, y, width, height, name, false, false, img, null, null, type);
    }

    public ButtonGame(int x, int y, int width, int height, String name, Img img, Img img2, ButtonType type) {
        this(x, y, width, height, name, false, false, img, img2, null, type);
    }

    public ButtonGame(int x, int y, int width, int height, String name, Img img, Img img2, Img img3, ButtonType type) {
        this(x, y, width, height, name, false, false, img, img2, img3, type);
    }

    public ButtonGame(int x, int y, int width, int height, String name, boolean isSelect, boolean isHover, Img img, Img img2, Img img3, ButtonType type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;
        this.isSelect = isSelect;
        this.isHover = isHover;
        this.img = img;
        this.img2 = img2;
        this.img3 = img3;
        this.type = type;
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

    public boolean isHover() {
        return isHover;
    }

    public void setHover(boolean hover) {
        isHover = hover;
    }

    public Img getImg() {
        return img;
    }

    public void setImg(Img img) {
        this.img = img;
    }

    public Img getImg2() {
        return img2;
    }

    public void setImg2(Img img2) {
        this.img2 = img2;
    }

    public Img getImg3() {
        return img3;
    }

    public void setImg3(Img img3) {
        this.img3 = img3;
    }

    public ButtonType getType() {
        return type;
    }

    public void setType(ButtonType type) {
        this.type = type;
    }

    public String getNameDraw() {
        if (name.length() > 7) {
            return (name.substring(0, 7) + "...").toUpperCase();
        } else {
            return name.toUpperCase();
        }
    }
}
