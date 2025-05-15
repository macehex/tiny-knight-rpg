package entity;

import main.GamePanel;
import main.UltilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {
    // blueprint for all chars, monsters
    GamePanel gp;
    public int worldX, worldY; // game camera
    public int speed;

    public BufferedImage up1, up2, up3, up4, up5, up6,
            down1, down2, down3, down4, down5, down6,
            left1, left2, left3, left4, left5, left6,
            right1, right2, right3, right4, right5, right6,
            idle1, idle2, idle3, idle4, idle5, idle6;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public int solidAreaDefaultX, SolidAreaDefaultY;
    // hit box
//    public Rectangle solidArea; // x y width and height
    public boolean collisionOn = false;
    //default entity hitbox
    public Rectangle solidArea = new Rectangle(0, 0, 32, 32);

    //NPC variables
    public int actionLockCounter = 0;
    String dialogues[] = new String [20];
    int dialogueIndex = 0;

    //Character and monster  status

    public int maxLife;
    public int life;

    //different entity: npc
    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {

    }
    public void speak(){

    }
    public void update() {
        // if subclass have same method -> subclass method have the priority
        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this); //passing "NPC" class (because npc has the priority )
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);
        if (!collisionOn) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }
        spriteCounter++;
        //update() gets called 60 times per second
        if (spriteCounter > 6) {
            switch (spriteNum) {
                case 1:
                    spriteNum = 2;
                    break;
                case 2:
                    spriteNum = 3;
                    break;
                case 3:
                    spriteNum = 4;
                    break;
                case 4:
                    spriteNum = 5;
                    break;
                case 5:
                    spriteNum = 6;
                    break;
                case 6:
                    spriteNum = 1;
                    break;
            }
            spriteCounter = 0;
        }

    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        // dont draw all of the tiles
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            switch (direction) {
                case "idle":
                    image = switch (spriteNum) {
                        case 1 -> idle1;
                        case 2 -> idle2;
                        case 3 -> idle3;
                        case 4 -> idle4;
                        case 5 -> idle5;
                        case 6 -> idle6;
                        default -> image;
                    };
                    break;
                case "up":
                    image = switch (spriteNum) {
                        case 1 -> up1;
                        case 2 -> up2;
                        case 3 -> up3;
                        case 4 -> up4;
                        case 5 -> up5;
                        case 6 -> up6;
                        default -> image;
                    };
                    break;
                case "down":
                    image = switch (spriteNum) {
                        case 1 -> down1;
                        case 2 -> down2;
                        case 3 -> down3;
                        case 4 -> down4;
                        case 5 -> down5;
                        case 6 -> down6;
                        default -> image;
                    };
                    break;
                case "left":
                    image = switch (spriteNum) {
                        case 1 -> left1;
                        case 2 -> left2;
                        case 3 -> left3;
                        case 4 -> left4;
                        case 5 -> left5;
                        case 6 -> left6;
                        default -> image;
                    };
                    break;
                case "right":
                    image = switch (spriteNum) {
                        case 1 -> right1;
                        case 2 -> right2;
                        case 3 -> right3;
                        case 4 -> right4;
                        case 5 -> right5;
                        case 6 -> right6;
                        default -> image;
                    };
                    break;
            }
            g2.drawImage(image, screenX - gp.tileSize / 2, screenY - gp.tileSize / 2, gp.tileSize * 2, gp.tileSize * 2, null);
//        // collision trouble shoot
            g2.setColor(Color.red);
            g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        }
    }

    public BufferedImage setup(String imagePath) {
        UltilityTool uTool = new UltilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaleImage(image, gp.tileSize * 2, gp.tileSize * 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

}
