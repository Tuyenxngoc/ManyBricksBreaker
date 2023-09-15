package org.example.ulti;

import org.example.model.ButtonGame;
import org.example.model.ButtonType;
import org.example.model.GameModel;
import org.example.model.Img;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    public static List<ButtonGame> listButtonBallShop() {
        List<ButtonGame> list_button_2_shop = new ArrayList<>();
        String[] name = new String[]{"ball", "baseball", "basketball", "beach1", "beach2", "beach3", "bowling", "cricket", "earth", "golf", "iron", "jupiter", "life_ring", "marble_blue", "marble_green", "marble_purple", "marble_yellow", "marble_zebra", "mars", "mercury", "neptune", "pluto", "saturn", "small_ball", "snooker", "soccer", "star", "tennis", "uranus", "venus", "volleyball",};
        int index = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                ButtonGame button2 = new ButtonGame(i * 130 + 10, j * 90 + 100, 120, 80, name[index], false, false,
                        new Img(GameModel.shopImages[0]),
                        new Img(i * 130 + 52, j * 90 + 115, 30, 30, GameModel.ballImages[index], index),
                        null,
                        ButtonType.SHOP_BALLS);
                list_button_2_shop.add(button2);
                index++;
                if (index == 31) {
                    break;
                }
            }
        }
        return list_button_2_shop;
    }

    public static List<ButtonGame> listButtonPaddleShop() {
        List<ButtonGame> list_button_2_shop = new ArrayList<>();
        String[] name = new String[]{"disc", "flowers", "paddle", "rainbow", "rounded", "spectrum", "sunset", "zebra",};
        int index = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                ButtonGame button2 = new ButtonGame(i * 130 + 10, j * 90 + 100, 120, 80, name[index], false, false,
                        new Img(GameModel.shopImages[0]),
                        new Img(i * 130 + 20, j * 90 + 125, 100, 10, GameModel.paddleImages[index], index),
                        null, ButtonType.SHOP_PADDLE);
                list_button_2_shop.add(button2);
                index++;
                if (index == 8) {
                    break;
                }
            }
        }
        return list_button_2_shop;
    }
}
