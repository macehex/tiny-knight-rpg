package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UltilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Objects;

public class Player extends Entity {
    KeyHandler keyH;
    public final int screenX;
    public final int screenY; // background scroll
    // key item slot
    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        solidArea = new Rectangle();
        // PLAYER HIT BOX
        solidArea.x = 40;
        solidArea.y = 25;
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
        // PLAYER STATUS
        maxLife = 5;
        life = maxLife;
    }

    public void getPlayerImage() {
        up1 = setup("/player/warrior_runu1");
        up2 = setup("/player/warrior_runu2");
        up3 = setup("/player/warrior_runu3");
        up4 = setup("/player/warrior_runu4");
        up5 = setup("/player/warrior_runu5");
        up6 = setup("/player/warrior_runu6");
        down1 = setup("/player/warrior_rund1");
        down2 = setup("/player/warrior_rund2");
        down3 = setup("/player/warrior_rund3");
        down4 = setup("/player/warrior_rund4");
        down5 = setup("/player/warrior_rund5");
        down6 = setup("/player/warrior_rund6");
        left1 = setup("/player/warrior_runl1");
        left2 = setup("/player/warrior_runl2");
        left3 = setup("/player/warrior_runl3");
        left4 = setup("/player/warrior_runl4");
        left5 = setup("/player/warrior_runl5");
        left6 = setup("/player/warrior_runl6");
        right1 = setup("/player/warrior_runr1");
        right2 = setup("/player/warrior_runr2");
        right3 = setup("/player/warrior_runr3");
        right4 = setup("/player/warrior_runr4");
        right5 = setup("/player/warrior_runr5");
        right6 = setup("/player/warrior_runr6");
        idle1 = setup("/player/idle/Warrior_idle1");
        idle2 = setup("/player/idle/Warrior_idle2");
        idle3 = setup("/player/idle/Warrior_idle3");
        idle4 = setup("/player/idle/Warrior_idle4");
        idle5 = setup("/player/idle/Warrior_idle1");
        idle6 = setup("/player/idle/Warrior_idle1");

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
            //CHECK EVENT
            gp.eHandler.checkEvent();
            //CHECK NPC collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

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
        g2.drawImage(image, screenX, screenY, null);
//        // collision trouble shoot
        g2.setColor(Color.red);
        g2.drawRect(screenX + solidArea.x,screenY + solidArea.y, solidArea.width, solidArea.height);


    }

    public void pickUpObject(int i) {
        if (i != 999) {
            //if i not equal to object index
            String objectName = gp.obj[i].name;
            switch (objectName) {
                case "Key":
                    gp.playSoundEffect(5);
                    hasKey++;
                    gp.obj[i] = null; // delete touched object
                    gp.ui.showMessage("Picked up key");
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.playSoundEffect(3);
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("Door opened");
                    } else {
                        gp.ui.showMessage("Find a key");
                    }
                    break;
                case "Chest":
                    gp.playSoundEffect(0);
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSoundEffect(0);
                    break;
                case "Speed Potion": //increase movement speed
                    gp.playSoundEffect(4);
                    speed += 1;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Picked up speed potion");
                    break;
                case "Health Potion 2":
                    gp.playSoundEffect(4);
                    gp.player.life+=2;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Picked up health+2 potion");
                    break;

            }
        }
    }
    public void interactNPC(int i){
        if(i!=999){

            System.out.println("Hitting an npc");
            if(gp.keyH.fPressed){
                gp.gameState=gp.dialogueState;
                gp.npc[i].speak();
            }
            gp.keyH.fPressed = false;
        }
    }
    public void interactKing(int i){
        gp.gameState=gp.dialogueState;
    }
}
