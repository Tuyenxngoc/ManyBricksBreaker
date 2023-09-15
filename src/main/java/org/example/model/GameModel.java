package org.example.model;

import org.example.ulti.FileLoader;
import org.example.ulti.Shop;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
    public static final int WIDTH = 960;// Chiều rộng cửa sổ
    public static final int HEIGHT = 720;// Chiều cao cửa sổ
    public static final int SIZE = 20;// Kích thước của khối
    public static final int PADDLE_WIDTH = SIZE * 10;// Chiều rộng thanh điều khiển (paddle)
    public static final double TIME_DECREMENT = 0.01D; // Giảm thời gian
    public static final int BUTTON_WIDTH = 100;// Chiều rộng nút (button)
    public static final int BUTTON_HEIGHT = 50;// Chiều cao nút (button)

    public static final String DATA_FILE_NAME = "data";
    public static final String PRICE_FILE_NAME = "price";


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
            FileLoader.loadImageFromResource("themes/background (9).jpg"),
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

    public static final Image[][] blockImages = new Image[9][4];
    public static final Image[] shopImages = new Image[10];
    public static final Image[] menuImages = new Image[10];

    // Khối static initializer để khởi tạo biến static
    static {
        BufferedImage bufferedImage = FileLoader.loadImageFromResource("blocks/Untitled.png");
        if (bufferedImage != null) {
            int width = 81;
            int height = 41;

            for (int i = 0; i < 8; i++) {
                int yOffset = i * 42;
                blockImages[i] = new Image[]{
                        bufferedImage.getSubimage(0, yOffset, width, height),
                        bufferedImage.getSubimage(82, yOffset, width, height),
                        bufferedImage.getSubimage(164, yOffset, width, height),
                        bufferedImage.getSubimage(246, yOffset, width, height)
                };
            }
            blockImages[8][0] = bufferedImage.getSubimage(328, 0, width, height);
            blockImages[8][1] = bufferedImage.getSubimage(328, 42, width, height);
            blockImages[8][2] = bufferedImage.getSubimage(328, 84, width, height);
        }
        bufferedImage = FileLoader.loadImageFromResource("buttons/Untitled.png");
        if (bufferedImage != null) {
            shopImages[0] = bufferedImage.getSubimage(0, 0, 195, 127);
            shopImages[1] = bufferedImage.getSubimage(0, 131, 77, 67);
            shopImages[2] = bufferedImage.getSubimage(78, 131, 77, 67);
            shopImages[3] = bufferedImage.getSubimage(0, 201, 101, 43);

            menuImages[0] = bufferedImage.getSubimage(0, 244, 348, 77);
            menuImages[1] = bufferedImage.getSubimage(348, 244, 78, 76);
        }
    }

    public static final Image[] buttonImages = new Image[]{
            FileLoader.loadImageFromResource("/buttons/btn (1).png"),
            FileLoader.loadImageFromResource("/buttons/btn (2).png"),
            FileLoader.loadImageFromResource("/buttons/btn (3).png"),
            FileLoader.loadImageFromResource("/buttons/close.png"),
    };

    public static final Image image_menu = FileLoader.loadImageFromResource("buttons/menu.png");

    public static final Font inkFree60Font = new Font("Ink Free", Font.BOLD, 60);
    public static final Font tahomaFont = new Font("Tahoma", Font.BOLD, 15);
    public static final Font arialFont = new Font("Arial", Font.BOLD, 15);

    public static final Color whiteColor = new Color(255, 255, 255, 130);
    public static final Color blueColor = new Color(15, 41, 90);
    public static final Color blackColor = new Color(0, 0, 0, 100);
    public static final Color colorShop = new Color(4, 65, 107, 255);
    public static final Color lightBlueColor = new Color(0, 112, 224, 110);
    public static final Color grayColor = new Color(53, 59, 59, 255);
    public static final Color orangeColor = new Color(252, 185, 5, 255);


    private final Paddle paddle;
    private List<Ball> balls;
    private List<Brick> bricks;
    private List<Item> items;

    private final List<ButtonGame> gameControlButtons; // Danh sách các nút điều khiển trong trò chơi
    private final List<ButtonGame> mainMenuButtons; // Danh sách các nút trong màn hình chính (Bắt đầu)
    private final List<ButtonGame> shopButtons; // Danh sách các nút trong màn hình cửa hàng
    private final List<ButtonGame> infoButtons; // Danh sách các nút trong màn hình thông tin

    private int selectedBallImageIndex; // Chỉ mục của hình ảnh quả bóng được chọn
    private int selectedPaddleImageIndex; // Chỉ mục của hình ảnh thanh điều khiển (paddle) được chọn
    private int selectedBackgroundImageIndex; // Chỉ mục của hình ảnh nền được chọn

    private int level;//Cấp độ hiện tại
    private int star; // Số lượng sao hiện tại
    private double remainingTime;// Thời gian còn lại trong một ván chơi
    private int remainingLives;// Số lượng mạng còn lại trong một ván chơi

    public boolean[] ballImageStatus;// Trạng thái đã mua các hình ảnh quả bóng hay chưa
    public boolean[] paddleImageStatus;// Trạng thái đã mua các hình ảnh thanh điều khiển (paddle) hay chưa
    public int[] ballImagePrice;// Giá của các hình ảnh quả bóng
    public int[] paddleImagePrice; // Giá của các hình ảnh thanh điều khiển (paddle)
    private int nextLevelPrice;// Giá để mở khóa cấp độ tiếp theo

    private boolean isMusicOn; // Điều khiển âm thanh (Enable/Disable âm thanh)
    private boolean isRunning; // Đang trong quá trình di chuyển và kiểm tra va chạm cho quả bóng
    private boolean isGameOver; // Kiểm tra trạng thái thua cuộc
    private boolean isNextLevel; // Đang chờ chuyển đổi cấp độ mới
    private boolean isPaused; // Đang trong trạng thái tạm dừng
    private final boolean isDebug; // Đang trong trạng thái gỡ lỗi


    private boolean isPlayingGame; // Màn hình chơi game
    private boolean isMainMenu; // Màn hình chính (Bắt đầu)
    private boolean isShop; // Màn hình cửa hàng
    private boolean isShoppingForBall; // Màn hình cửa hàng chọn quả bóng
    private boolean isInfo; // Màn hình thông tin


    private final Desktop desktop;
    private final Cursor customCursor; // Thiết lập con trỏ tùy chỉnh (Custom Cursor) - Sử dụng khi di chuột vào JPanel
    private final Cursor defaultCursor; // Thiết lập con trỏ mặc định (Default Cursor) - Sử dụng mũi tên

    public GameModel() {
        //Khởi tạo list
        balls = new ArrayList<>();
        bricks = new ArrayList<>();
        items = new ArrayList<>();

        gameControlButtons = new ArrayList<>();
        mainMenuButtons = new ArrayList<>();
        shopButtons = new ArrayList<>();
        infoButtons = new ArrayList<>();

        //Khởi tạo destop và con trỏ chuột
        this.desktop = Desktop.getDesktop();
        this.customCursor = new Cursor(Cursor.HAND_CURSOR);
        this.defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
        //Khởi tạo thanh đỡ
        paddle = new Paddle((WIDTH / 2) - (PADDLE_WIDTH / 2), HEIGHT - SIZE * 2, PADDLE_WIDTH, SIZE);
        //Khởi tạo chỉ số game ban đầu
        remainingLives = 3;
        selectedBackgroundImageIndex = 0;

        isMusicOn = true;
        isGameOver = false;
        isRunning = false;
        isNextLevel = false;
        isPaused = false;
        isDebug = false;

        isMainMenu = true;
        isShop = false;
        isShoppingForBall = true;
        isInfo = false;

        this.readDataInFile();
        this.addButtons();
    }

    private void readDataInFile() {
        try {
            String data = FileLoader.readFile(DATA_FILE_NAME, FileLoader.DEFAULT_DATA);
            JSONObject object = (JSONObject) JSONValue.parse(data);
            //Đọc chỉ số lv và sao
            level = ((Long) object.get("level")).intValue();
            star = ((Long) object.get("star")).intValue();
            //Đọc mảng bóng đã mua
            JSONArray jsonArray = (JSONArray) object.get("ballImageStatus");
            int length = jsonArray.size();
            ballImageStatus = new boolean[length];
            for (int i = 0; i < length; i++) {
                ballImageStatus[i] = (boolean) jsonArray.get(i);
            }
            //Đọc mảng paddle đã mua
            jsonArray = (JSONArray) object.get("paddleImageStatus");
            length = jsonArray.size();
            paddleImageStatus = new boolean[length];
            for (int i = 0; i < length; i++) {
                paddleImageStatus[i] = (boolean) jsonArray.get(i);
            }
            //Đọc chỉ số bóng và thanh đỡ đang sử dụng
            selectedPaddleImageIndex = ((Long) object.get("indexPaddleImage")).intValue();
            selectedBallImageIndex = ((Long) object.get("indexBallImage")).intValue();

            //Đọc giá tiền
            data = FileLoader.readFile(PRICE_FILE_NAME, FileLoader.DEFAULT_PRICE);
            JSONObject price = (JSONObject) JSONValue.parse(data);
            //Giá bán ball
            jsonArray = (JSONArray) price.get("ballImagePrice");
            length = jsonArray.size();
            ballImagePrice = new int[length];
            for (int i = 0; i < length; i++) {
                ballImagePrice[i] = ((Long) jsonArray.get(i)).intValue();
            }
            //Giá bán price
            jsonArray = (JSONArray) price.get("paddleImagePrice");
            length = jsonArray.size();
            paddleImagePrice = new int[length];
            for (int i = 0; i < length; i++) {
                paddleImagePrice[i] = ((Long) jsonArray.get(i)).intValue();
            }
            this.nextLevelPrice = ((Long) price.get("nextLevelPrice")).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void saveData() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "tuyenngoc");
        jsonObject.put("level", level);
        jsonObject.put("star", star);

        JSONArray balls = new JSONArray();
        for (boolean imageStatus : ballImageStatus) {
            balls.add(imageStatus);
        }
        jsonObject.put("ballImageStatus", balls);

        JSONArray paddles = new JSONArray();
        for (boolean imageStatus : paddleImageStatus) {
            paddles.add(imageStatus);
        }
        jsonObject.put("paddleImageStatus", paddles);

        jsonObject.put("indexPaddleImage", selectedPaddleImageIndex);
        jsonObject.put("indexBallImage", selectedBallImageIndex);
        FileLoader.writeFile(jsonObject.toJSONString(), DATA_FILE_NAME);
    }

    private void addButtons() {
        //Game control buttons
        //Pause
        ButtonGame resumeButton = new ButtonGame(330, 240, 300, 40, "Resume", ButtonType.GAME_CONTROL_PAUSE);
        ButtonGame restartButton = new ButtonGame(330, 290, 300, 40, "Restart", ButtonType.GAME_CONTROL_PAUSE);
        ButtonGame soundButton = new ButtonGame(330, 340, 300, 40, "Sound on", ButtonType.GAME_CONTROL_PAUSE);
        ButtonGame nextLevelButton = new ButtonGame(330, 390, 300, 40, "Next level",
                null,
                new Img(" -" + nextLevelPrice, 15, 15, ballImages[26]),
                ButtonType.GAME_CONTROL_PAUSE);
        ButtonGame homeButton = new ButtonGame(330, 440, 300, 40, "Home", ButtonType.GAME_CONTROL_PAUSE);
        gameControlButtons.add(resumeButton);
        gameControlButtons.add(restartButton);
        gameControlButtons.add(soundButton);
        gameControlButtons.add(nextLevelButton);
        gameControlButtons.add(homeButton);
        //Game lost
        ButtonGame restartButton2 = new ButtonGame(65, 390, 200, 40, "Restart", ButtonType.GAME_CONTROL_LOST);
        ButtonGame soundButton2 = new ButtonGame(275, 390, 200, 40, "Sound on", ButtonType.GAME_CONTROL_LOST);
        ButtonGame nextLevelButton2 = new ButtonGame(485, 390, 200, 40, "Next level",
                null,
                new Img(" -" + nextLevelPrice, 15, 15, ballImages[26]),
                ButtonType.GAME_CONTROL_LOST);
        ButtonGame homeButton2 = new ButtonGame(695, 390, 200, 40, "Home", ButtonType.GAME_CONTROL_LOST);
        gameControlButtons.add(restartButton2);
        gameControlButtons.add(soundButton2);
        gameControlButtons.add(nextLevelButton2);
        gameControlButtons.add(homeButton2);

        //Button main menu
        ButtonGame play = new ButtonGame(160, 300, BUTTON_WIDTH, BUTTON_HEIGHT, "play",
                new Img(buttonImages[0]),
                ButtonType.MAIN_MENU);

        ButtonGame options = new ButtonGame(160, 360, BUTTON_WIDTH, BUTTON_HEIGHT, "options",
                new Img(buttonImages[1]),
                ButtonType.MAIN_MENU);

        ButtonGame quests = new ButtonGame(160, 420, BUTTON_WIDTH, BUTTON_HEIGHT, "quests",
                new Img(buttonImages[2]),
                ButtonType.MAIN_MENU);
        mainMenuButtons.add(play);
        mainMenuButtons.add(options);
        mainMenuButtons.add(quests);

        //Button info
        ButtonGame close = new ButtonGame(900, 20, 40, 40, "close",
                new Img(buttonImages[3]),
                ButtonType.CLOSE);
        ButtonGame link = new ButtonGame(435, 640, 90, 25, "link", ButtonType.LINK);
        infoButtons.add(link);
        infoButtons.add(close);

        //Button shop
        ButtonGame tabBall = new ButtonGame(0, 68, 60, 30, "ShoppingForBall", true, false,
                null,
                new Img(20, 73, 20, 20, ballImages[2]),
                null,
                ButtonType.SHOP_CONTROL);
        ButtonGame tabPaddle = new ButtonGame(60, 68, 60, 30, "paddleShow", false, false,
                null,
                new Img(66, 80, 50, 5, paddleImages[4]),
                null,
                ButtonType.SHOP_CONTROL);
        shopButtons.add(tabBall);
        shopButtons.add(tabPaddle);
        shopButtons.addAll(Shop.listButtonBallShop());
        shopButtons.addAll(Shop.listButtonPaddleShop());
        shopButtons.add(new ButtonGame(900, 20, 40, 40, "close", new Img(10, 10, 10, 10, menuImages[1], 0), null, null, ButtonType.CLOSE));
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

    public Image getBallImage() {
        return ballImages[selectedBallImageIndex];
    }

    public Image getPaddleImage() {
        return paddleImages[selectedPaddleImageIndex];
    }

    public Image getBackgroundImage() {
        return backgroundImages[selectedBackgroundImageIndex];
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

    public void setStar(int star) {
        this.star = star;
    }

    public void increaseStar() {
        star++;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        this.isRunning = running;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.isGameOver = gameOver;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public boolean isNextLevel() {
        return isNextLevel;
    }

    public void setNextLevel(boolean nextLevel) {
        this.isNextLevel = nextLevel;
    }

    public Font getInkFree() {
        return inkFree60Font;
    }

    public int getTim() {
        return remainingLives;
    }

    public void setTim(int tim) {
        this.remainingLives = tim;
    }

    public void reduceTim() {
        this.remainingLives--;
    }

    public boolean isMusicOn() {
        return isMusicOn;
    }

    public double getRemainingTime() {
        return remainingTime;
    }

    public String getTimeDraw() {
        int p = (int) (remainingTime / 60);
        int s = (int) (remainingTime % 60);
        return String.format("%d:%d", p, s);
    }

    public void setRemainingTime(double remainingTime) {
        this.remainingTime = remainingTime;
    }

    public void increaseTime(double time) {
        this.remainingTime += time;
    }

    public void reduceTime() {
        remainingTime -= TIME_DECREMENT;
    }

    public List<ButtonGame> getButtonsMainMenu() {
        return mainMenuButtons;
    }


    public void extraLife() {
        remainingLives++;
    }

    public void setSelectedBackgroundImageIndex(int selectedBackgroundImageIndex) {
        this.selectedBackgroundImageIndex = selectedBackgroundImageIndex % backgroundImages.length;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        this.isPaused = paused;
    }

    public boolean isShop() {
        return isShop;
    }

    public boolean isMainMenu() {
        return isMainMenu;
    }

    public boolean isInfo() {
        return isInfo;
    }

    public boolean isPlayingGame() {
        return isPlayingGame;
    }

    public List<ButtonGame> getButtonShop() {
        return shopButtons;
    }

    public List<ButtonGame> getButtonInfo() {
        return infoButtons;
    }

    public void browse(String str) {
        try {
            this.desktop.browse(new URI(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean[] getBallImageStatus() {
        return ballImageStatus;
    }

    public int getSelectedBallImageIndex() {
        return selectedBallImageIndex;
    }

    public void setSelectedBallImageIndex(int selectedBallImageIndex) {
        this.selectedBallImageIndex = selectedBallImageIndex;
    }

    public boolean[] getPaddleImageStatus() {
        return paddleImageStatus;
    }

    public void setSelectedPaddleImageIndex(int selectedPaddleImageIndex) {
        this.selectedPaddleImageIndex = selectedPaddleImageIndex;
    }

    public boolean isShoppingForBall() {
        return isShoppingForBall;
    }

    public void setShoppingForBall(boolean shoppingForBall) {
        isShoppingForBall = shoppingForBall;
    }

    public List<ButtonGame> getShopButtons() {
        return shopButtons;
    }

    public List<ButtonGame> getGameControlButtons() {
        return gameControlButtons;
    }

    public void setMusicOn(boolean musicOn) {
        this.isMusicOn = musicOn;
    }

    public int[] getBallImagePrice() {
        return ballImagePrice;
    }

    public int[] getPaddleImagePrice() {
        return paddleImagePrice;
    }

    public int getNextLevelPrice() {
        return nextLevelPrice;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public void setHome() {
        this.isRunning = false;
        this.isPlayingGame = false;
        this.isMainMenu = true;
        this.isShop = false;
        this.isInfo = false;
    }

    public void setInfo() {
        this.isRunning = false;
        this.isPlayingGame = false;
        this.isMainMenu = false;
        this.isShop = false;
        this.isInfo = true;
    }

    public void setPlayGame() {
        this.isRunning = false;
        this.isPlayingGame = true;
        this.isMainMenu = false;
        this.isShop = false;
        this.isInfo = false;
    }

    public void setClose() {
        this.isRunning = false;
        this.isPlayingGame = false;
        this.isMainMenu = true;
        this.isShop = false;
        this.isInfo = false;
    }

    public void setShop() {
        this.isRunning = false;
        this.isPlayingGame = false;
        this.isMainMenu = false;
        this.isShop = true;
        this.isInfo = false;
    }
}
