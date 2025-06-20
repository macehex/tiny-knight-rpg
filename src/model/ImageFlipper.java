package model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageFlipper {
    public static BufferedImage flipImage90Degrees(BufferedImage originalImage) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        BufferedImage rotatedImage = new BufferedImage(height, width, originalImage.getType());
        Graphics2D g2d = rotatedImage.createGraphics();
        g2d.rotate(Math.toRadians(90), height / 2.0, height / 2.0);
        g2d.drawImage(originalImage, 0, -width, null);
        g2d.dispose();
        return rotatedImage;
    }
}