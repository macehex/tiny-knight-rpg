package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY; // background scroll
    // key item slot
    public int hasKey = 0 ;
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 16;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        SolidAreaDefaultY = solidArea.y;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        // PLACEHOLDER
        worldX = gp.tileSize * 23; //CHANGE LATER ASAP
        worldY = gp.tileSize * 7;
        speed = 4;
        direction = "right";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/warrior_runu1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/warrior_runu2.png")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/warrior_runu3.png")));
            up4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/warrior_runu4.png")));
            up5 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/warrior_runu5.png")));
            up6 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/warrior_runu6.png")));

            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/warrior_rund1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/warrior_rund2.png")));
            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/warrior_rund3.png")));
            down4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/warrior_rund4.png")));
            down5 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/warrior_rund5.png")));
            down6 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/warrior_rund6.png")));

            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/warrior_runl1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/warrior_runl2.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/warrior_runl3.png")));
            left4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/warrior_runl4.png")));
            left5 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/warrior_runl5.png")));
            left6 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/warrior_runl6.png")));

            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/warrior_runr1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/warrior_runr2.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/warrior_runr3.png")));
            right4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/warrior_runr4.png")));
            right5 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/warrior_runr5.png")));
            right6 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/warrior_runr6.png")));

            idle1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/idle/Warrior_idle1.png")));
            idle2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/idle/Warrior_idle2.png")));
            idle3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/idle/Warrior_idle3.png")));
            idle4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/idle/Warrior_idle4.png")));
            idle5 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/idle/Warrior_idle1.png")));
            idle6 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/idle/Warrior_idle1.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        //idle state
        if (!(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed)) {
            direction = "idle";
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
        } else {
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }
            //   check tile collision state
            collisionOn = false;
            gp.cChecker.checkTile(this);
            // check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
            // if collision false. player can move
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
    }

    public void draw(Graphics2D g2) {

//        g2.setColor(Color.white);
//        g.fillRect(x, y, width, height);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;
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
        g2.drawImage(image, screenX, screenY, gp.tileSize * 2, gp.tileSize * 2, null);
    }

    public void pickUpObject(int i){
        if(i != 999){
            //if i not equal to object index
            String objectName = gp.obj[i].name;
            switch (objectName){
                case "Key":
                    gp.playSoundEffect(5);
                    hasKey++;
                    gp.obj[i] = null; // delete touched object
                    System.out.println("Inventory.key: "+ hasKey);
                    break;
                case "Door":
                    if(hasKey>0){
                        gp.playSoundEffect(3);
                        gp.obj[i]=null;
                        hasKey--;
                        System.out.println("Inventory.key: "+ hasKey);
                    }
                    break;
                case "Chest":
                    gp.playSoundEffect(0);
                    break;
                case "Potion": //increase movement speed
                    gp.playSoundEffect(4);
                    speed += 1;
                    gp.obj[i] = null ;
                    break;
            }
        }
    }
}
