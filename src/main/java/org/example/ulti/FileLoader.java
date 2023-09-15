package org.example.ulti;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class FileLoader {
    public static final String DEFAULT_DATA = "{\"name\":\"tuyenngoc\",\"level\":1,\"star\":0,\"ballImageStatus\":[true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false],\"paddleImageStatus\":[false,false,true,false,false,false,false,false],\"indexPaddleImage\":0,\"indexBallImage\":0}";
    public static final String DEFAULT_PRICE = "{\"nextLevelPrice\":20,\"ballImagePrice\":[10,5,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,30,20,20,10,100,20,20,20,10],\"paddleImagePrice\":[10,100,5,20,5,50,10,10]}";

    public static BufferedImage loadImageFromResource(String resourceName) {
        try {
            // Sử dụng ClassLoader để lấy InputStream từ tài nguyên trong thư mục resources
            InputStream inputStream = FileLoader.class.getResourceAsStream("/images/" + resourceName);
            // Đảm bảo tài nguyên tồn tại
            if (inputStream == null) {
                System.err.println("Không tìm thấy tài nguyên: " + resourceName);
                System.exit(0);
            }
            // Đọc hình ảnh từ InputStream
            return ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Clip loadSound(String resourceName) {
        try {
            // Sử dụng ClassLoader để lấy InputStream từ tài nguyên trong thư mục resources
            InputStream inputStream = FileLoader.class.getResourceAsStream("/wav/" + resourceName + ".wav");
            // Đảm bảo tài nguyên tồn tại
            if (inputStream == null) {
                System.err.println("Không tìm thấy tài nguyên: " + resourceName);
                System.exit(0);
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            return clip;
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String readFile(String fileName, String obj) {
        StringBuilder content = new StringBuilder();

        try {
            File file = new File("data/" + fileName);
            if (!file.exists()) {
                writeFile(obj, fileName);
            }
            FileReader fileReader = new FileReader("data/" + fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public static void writeFile(String content, String fileName) {
        try {
            FileWriter fileWriter = new FileWriter("data/" + fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
