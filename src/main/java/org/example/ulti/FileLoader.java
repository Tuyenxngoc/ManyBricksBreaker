package org.example.ulti;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class FileLoader {

    public static BufferedImage loadImageFromResource(String resourceName) {
        try {
            // Sử dụng ClassLoader để lấy InputStream từ tài nguyên trong thư mục resources
            InputStream inputStream = FileLoader.class.getResourceAsStream("/images/" + resourceName);
            // Đảm bảo tài nguyên tồn tại
            if (inputStream == null) {
                System.err.println("Không tìm thấy tài nguyên: " + resourceName);
                return null;
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
                return null;
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

    public static void writeFile(String fileName, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
