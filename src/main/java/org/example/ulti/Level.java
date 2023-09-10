package org.example.ulti;

import org.example.model.Brick;
import org.example.model.GameModel;
import org.example.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.example.model.GameModel.blockImage;
import static org.example.model.ItemType.CONG3;

public class Level {
    private static final Random random = new Random();

    public static List<Brick> getListBricks(int lv) {
        return switch (lv) {
            case 1 -> getListLevel_1();
            case 2 -> getListLevel_2();
            case 3 -> getListLevel_3();
            case 4 -> getListLevel_4();
            case 5 -> getListLevel_5();
            case 6 -> getListLevel_6();
            case 7 -> getListLevel_7();
            default -> getListLevel_Test();
        };
    }

    private static List<Brick> getListLevel_Test() {
        List<Brick> bricks = new ArrayList<>();
        Brick brick = new Brick(430, 10, 60, 20, GameModel.blockImage[0][0]);
        bricks.add(brick);
        return bricks;
    }

    private static List<Brick> getListLevel_1() {
        double dropChance = 0.3D;
        List<Brick> tmp = new ArrayList<>();

        int k = 0;
        for (int i = 4; i < 48; i += 4) {
            for (int j = 5; j < 21; j += 2) {
                Brick g;
                if (random.nextDouble() < dropChance) {
                    g = new Brick(i * 20, j * 20, 60, 20, blockImage[k][3],
                            new Item(i * 20, j * 20, CONG3));
                } else {
                    g = new Brick(i * 20, j * 20, 60, 20, blockImage[k][0]);
                }
                tmp.add(g);
            }
            k = (k + 1) % 8;
        }
        return tmp;
    }

    private static List<Brick> getListLevel_2() {
        double dropChance = 0.3D;
        List<Brick> tmp = new ArrayList<>();

        int k = 0;
        for (int i = 3; i < 20; i++) {
            for (int j = 3; j < 48; j += 3) {
                Brick g;
                if (random.nextDouble() < dropChance) {
                    g = new Brick(j * 20, i * 20, 60, 20, blockImage[k][0], new Item(j * 20, i * 20, CONG3));
                } else {
                    g = new Brick(j * 20, i * 20, 60, 20, blockImage[k][0]);
                }
                tmp.add(g);
            }
            k = (k + 1) % 8;
        }

        return tmp;
    }

    private static List<Brick> getListLevel_3() {
        double dropChance = 0.3D;
        List<Brick> tmp = new ArrayList<>();

        for (int i = 3; i < 48; i += 3) {
            for (int j = 5; j < 21; j += 2) {
                Brick g;
                if (random.nextDouble() < dropChance) {
                    g = new Brick(i * 20, j * 20, 60, 20, blockImage[0][1], new Item(i * 20, j * 20, CONG3));
                } else {
                    g = new Brick(i * 20, j * 20, 60, 20, blockImage[0][0]);
                }
                tmp.add(g);
            }
        }
        return tmp;
    }

    private static List<Brick> getListLevel_4() {
        double dropChance = 0.3D;
        List<Brick> tmp = new ArrayList<>();

        for (int i = 4; i < 48; i += 6) {
            for (int j = 5; j < 21; j++) {
                Brick g;
                if (random.nextDouble() < dropChance) {
                    g = new Brick(i * 20, j * 20, 60, 20, blockImage[0][0], new Item(i * 20, j * 20, CONG3));
                } else {
                    g = new Brick(i * 20, j * 20, 60, 20, blockImage[0][3]);
                }
                tmp.add(g);
            }
        }
        return tmp;

    }

    private static List<Brick> getListLevel_5() {
        double dropChance = 0.3D;
        List<Brick> tmp = new ArrayList<>();

        int dem = 0;
        for (int i = 3; i < 48; i += 3) {
            for (int j = 3; j < 21; j++) {
                dem = 1 - dem;
                if (dem != 0) {
                    Brick g;
                    if (random.nextDouble() < dropChance) {
                        g = new Brick(i * 20, j * 20, 60, 20, blockImage[0][1], new Item(i * 20, j * 20, CONG3));
                    } else {
                        g = new Brick(i * 20, j * 20, 60, 20, blockImage[0][0]);
                    }
                    tmp.add(g);
                }
            }
            dem = 1 - dem;
        }
        return tmp;
    }

    private static List<Brick> getListLevel_6() {
        double dropChance = 0.3D;
        List<Brick> tmp = new ArrayList<>();

        int dem = 5;
        int dem2 = 15;
        boolean trai = true;
        for (int i = 3; i < 48; i += 3) {
            for (int j = dem; j < 36 - dem2; j++) {
                Brick g;
                if (random.nextDouble() < dropChance) {
                    g = new Brick(i * 20, j * 20, 60, 20, blockImage[1][1], new Item(i * 20, j * 20, CONG3));
                } else {
                    g = new Brick(i * 20, j * 20, 60, 20, blockImage[1][0]);
                }
                tmp.add(g);
            }
            if (trai) {
                dem++;
                dem2++;
            } else {
                dem--;
                dem2--;
            }
            if (dem == 12 && dem2 == 22) {
                trai = false;
            }
        }
        return tmp;
    }

    private static List<Brick> getListLevel_7() {
        double dropChance = 0.3D;
        List<Brick> tmp = new ArrayList<>();

        for (int i = 3; i < 48; i += 3) {
            for (int j = 3; j < 21; j++) {
                Brick g;
                if (random.nextDouble() < dropChance) {
                    g = new Brick(i * 20, j * 20, 60, 20, blockImage[3][1], new Item(i * 20, j * 20, CONG3));
                } else {
                    g = new Brick(i * 20, j * 20, 60, 20, blockImage[3][0]);
                }
                tmp.add(g);
            }
        }
        return tmp;
    }
}
