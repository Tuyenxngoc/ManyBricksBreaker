package org.example.model;

import org.example.ulti.FileLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
    public static final int WIDTH = 960;
    public static final int HEIGHT = 720;
    public static final int SIZE = 20;
    public static final int PADDLE_WIDTH = SIZE * 10;
    public static final double TIME_DECREMENT = 0.01D;


    public static final Image[] ballImages = new Image[]{
            FileLoader.loadImageFromResource("balls/ball.png"),
            FileLoader.loadImageFromResource("balls/baseball.png"),
            FileLoader.loadImageFromResource("balls/basketball.png"),
            FileLoader.loadImageFromResource("balls/beach1.png"),
            FileLoader.loadImageFromResource("balls/beach2.png"),
            FileLoader.loadImageFromResource("balls/beach3.png"),
            FileLoader.loadImageFromResource("balls/bowling.png"),
            FileLoader.loadImageFromResource("balls/cricket.png"),
            FileLoader.loadImageFromResource("balls/earth.png"),
            FileLoader.loadImageFromResource("balls/golf.png"),
            FileLoader.loadImageFromResource("balls/iron.png"),
            FileLoader.loadImageFromResource("balls/jupiter.png"),
            FileLoader.loadImageFromResource("balls/life_ring.png"),
            FileLoader.loadImageFromResource("balls/marble_blue.png"),
            FileLoader.loadImageFromResource("balls/marble_green.png"),
            FileLoader.loadImageFromResource("balls/marble_purple.png"),
            FileLoader.loadImageFromResource("balls/marble_yellow.png"),
            FileLoader.loadImageFromResource("balls/marble_zebra.png"),
            FileLoader.loadImageFromResource("balls/mars.png"),
            FileLoader.loadImageFromResource("balls/mercury.png"),
            FileLoader.loadImageFromResource("balls/neptune.png"),
            FileLoader.loadImageFromResource("balls/pluto.png"),
            FileLoader.loadImageFromResource("balls/saturn.png"),
            FileLoader.loadImageFromResource("balls/small_ball.png"),
            FileLoader.loadImageFromResource("balls/snooker.png"),
            FileLoader.loadImageFromResource("balls/soccer.png"),
            FileLoader.loadImageFromResource("balls/star.png"),
            FileLoader.loadImageFromResource("balls/tennis.png"),
            FileLoader.loadImageFromResource("balls/uranus.png"),
            FileLoader.loadImageFromResource("balls/venus.png"),
            FileLoader.loadImageFromResource("balls/volleyball.png"),
    };

    public static final Image[] paddleImages = new Image[]{
            FileLoader.loadImageFromResource("paddles/disc.png"),
            FileLoader.loadImageFromResource("paddles/flowers.png"),
            FileLoader.loadImageFromResource("paddles/paddle.png"),
            FileLoader.loadImageFromResource("paddles/rainbow.png"),
            FileLoader.loadImageFromResource("paddles/rounded.png"),
            FileLoader.loadImageFromResource("paddles/spectrum.png"),
            FileLoader.loadImageFromResource("paddles/sunset.png"),
            FileLoader.loadImageFromResource("paddles/zebra.png"),
    };
    public static final Image[] backgroundImages = new Image[]{
            FileLoader.loadImageFromResource("themes/background (1).jpg"),
            FileLoader.loadImageFromResource("themes/background (2).jpg"),
            FileLoader.loadImageFromResource("themes/background (3).jpg"),
            FileLoader.loadImageFromResource("themes/background (4).jpg"),
            FileLoader.loadImageFromResource("themes/background (5).jpg"),
            FileLoader.loadImageFromResource("themes/background (6).jpg"),
            FileLoader.loadImageFromResource("themes/background (7).jpg"),
            FileLoader.loadImageFromResource("themes/background (8).jpg"),
    };
    public static final Image[] iconItem = new Image[]{
            FileLoader.loadImageFromResource("powerups/bomb.png"),
            FileLoader.loadImageFromResource("powerups/extra_life.png"),
            FileLoader.loadImageFromResource("powerups/fire_ball.png"),
            FileLoader.loadImageFromResource("powerups/kill.png"),
            FileLoader.loadImageFromResource("powerups/laser.png"),
            FileLoader.loadImageFromResource("powerups/laser2.png"),
            FileLoader.loadImageFromResource("powerups/longer.png"),
            FileLoader.loadImageFromResource("powerups/plus_five.png"),
            FileLoader.loadImageFromResource("powerups/plus_six.png"),
            FileLoader.loadImageFromResource("powerups/plus_three.png"),
            FileLoader.loadImageFromResource("powerups/shorter.png"),
            FileLoader.loadImageFromResource("powerups/star.png"),
            FileLoader.loadImageFromResource("powerups/times_six.png"),
            FileLoader.loadImageFromResource("powerups/times_three.png"),
            FileLoader.loadImageFromResource("powerups/wall.png"),
    };
    public static final String[] detailItem = new String[]{
            "Bắn thêm 3 quả bóng",
            "Biến mỗi quả bóng hiện có thành 3",
            "Tăng thêm một mạng",
            "Thêm một bức tường dưới thanh đỡ để bảo vệ các quả bóng khỏi rơi ra khỏi màn hình trong vài giây",
            "Làm thanh đỡ dài hơn trong vài giây",
            "Cộng thêm 1 sao (Bạn có thể dùng chúng để mua skin hoặc bỏ qua các cấp độ mà bạn không thể đánh bại)",
            "Quả bóng phá hủy tất cả các viên gạch trên đường đi của nó trong vài giây",
            "Trùm tia laze bắn ra từ thanh đỡ phá hủy toàn bộ một cột gạch",
            "Cộng thêm 5 giây",
            "Biến mỗi quả bóng hiện có thành 6",
            "Xóa bỏ ngẫu nhiên một quả bóng đang có",
            "Làm thanh đỡ ngắn hơn trong vài giây",
            "Phát nổ và mất một mạng"};

    public static final Image[][] blockImage = new Image[9][4];

    // Khối static initializer để khởi tạo biến static
    static {
        BufferedImage bufferedImage = FileLoader.loadImageFromResource("blocks/Untitled.png");
        if (bufferedImage != null) {
            int width = 81;
            int height = 41;

            for (int i = 0; i < 8; i++) {
                int yOffset = i * 42;
                blockImage[i] = new Image[]{
                        bufferedImage.getSubimage(0, yOffset, width, height),
                        bufferedImage.getSubimage(82, yOffset, width, height),
                        bufferedImage.getSubimage(164, yOffset, width, height),
                        bufferedImage.getSubimage(246, yOffset, width, height)
                };
            }
            blockImage[8][0] = bufferedImage.getSubimage(328, 0, width, height);
            blockImage[8][1] = bufferedImage.getSubimage(328, 42, width, height);
            blockImage[8][2] = bufferedImage.getSubimage(328, 84, width, height);

        }
    }

    public static final Font inkFree60 = new Font("Ink Free", Font.BOLD, 60);
    public static final Font tahoma = new Font("Tahoma", Font.BOLD, 15);
    public static final Font arial = new Font("Arial", Font.BOLD, 15);

    public static final Color white = new Color(255, 255, 255, 130);
    public static final Color blue = new Color(15, 41, 90);

    private final Paddle paddle;
    private List<Ball> balls;
    private List<Brick> bricks;
    private List<Item> items;
    private List<Button> buttons;

    private int indexBallImage;
    private int indexPaddleImage;
    private int indexBackgroundImage;

    private int level;
    private int star;
    private double time;
    private int life;

    private boolean musicOn;//Bật tắt âm thanh
    private boolean running;//Bắt đầu di chuyển và kiểm tra bóng va chạm
    private boolean gameOver;//Kiểm tra thua
    private boolean nextLevel;//Chờ chuyển lv
    private boolean pause;//Chờ


    public GameModel() {
        balls = new ArrayList<>();
        bricks = new ArrayList<>();
        items = new ArrayList<>();
        buttons = new ArrayList<>();
        paddle = new Paddle((WIDTH / 2) - (PADDLE_WIDTH / 2), HEIGHT - SIZE * 2, PADDLE_WIDTH, SIZE);

        level = 2;
        life = 3;
        indexPaddleImage = 1;
        indexBallImage = 0;
        indexBackgroundImage = 0;

        musicOn = true;
        gameOver = false;
        running = false;
        nextLevel = false;
        pause = false;
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public void setBalls(List<Ball> balls) {
        this.balls = balls;
    }

    public void setBricks(List<Brick> bricks) {
        this.bricks = bricks;
    }

    public Image[] getIconItem() {
        return iconItem;
    }

    public Image[][] getBlockImage() {
        return blockImage;
    }

    public Image getBallImage() {
        return ballImages[indexBallImage];
    }

    public Image getPaddleImage() {
        return paddleImages[indexPaddleImage];
    }

    public Image getBackgroundImage() {
        return backgroundImages[indexBackgroundImage];
    }

    public int getLevel() {
        return level;
    }

    public void increaseLevel() {
        this.level++;
    }

    public int getStar() {
        return star;
    }

    public void increaseStar() {
        star++;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public boolean isNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(boolean nextLevel) {
        this.nextLevel = nextLevel;
    }

    public Font getInkFree() {
        return inkFree60;
    }

    public int getTim() {
        return life;
    }

    public void setTim(int tim) {
        this.life = tim;
    }

    public void reduceTim() {
        this.life--;
    }

    public boolean isMusicOn() {
        return musicOn;
    }

    public double getTime() {
        return time;
    }

    public String getTimeDraw() {
        int p = (int) (time / 60);
        int s = (int) (time % 60);
        return String.format("%d:%d", p, s);
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void increaseTime(double time) {
        this.time += time;
    }

    public void reduceTime() {
        time -= TIME_DECREMENT;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }

    public void extraLife() {
        life++;
    }

    public void setIndexBackgroundImage(int indexBackgroundImage) {
        this.indexBackgroundImage = indexBackgroundImage;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }
}
