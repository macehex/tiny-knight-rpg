package entity;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    // blueprint for all chars, monsters
    public int worldX, worldY; // game camera
    public int speed;

    public BufferedImage up1, up2, up3, up4, up5, up6,
                        down1, down2, down3, down4, down5, down6,
                        left1, left2, left3,left4,left5,left6,
                         right1, right2, right3, right4, right5, right6,
                        idle1, idle2, idle3, idle4, idle5, idle6;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public int solidAreaDefaultX, SolidAreaDefaultY;
    // hit box
    public Rectangle solidArea; // x y width and height
    public boolean collisionOn = false;

}
