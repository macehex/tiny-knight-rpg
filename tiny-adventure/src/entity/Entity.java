package entity;

import main.GamePanel;
import main.UltilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {
    // blueprint for all chars, monsters, objects
    GamePanel gp;
    public BufferedImage image, image2,image3,image4,image5,image6;
    public BufferedImage dead1, dead2, dead3, dead4, dead5, dead6,dead7,dead8,dead9,dead10,dead11,dead12,dead13,dead14;
    public BufferedImage attackUp1, attackUp2, attackUp3, attackUp4, attackUp5, attackUp6,
                            attackDown1, attackDown2, attackDown3, attackDown4, attackDown5, attackDown6;
    public BufferedImage attackRight1, attackRight2, attackRight3, attackRight4, attackRight5, attackRight6;
    public BufferedImage attackLeft1, attackLeft2, attackLeft3, attackLeft4, attackLeft5, attackLeft6;
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public String name;
    public boolean collision = false ;
    public boolean invincible = false;
    //COUNTER
    int invincibleCounter = 0;
    public int spriteCounter = 0;
    int dyingCounter = 0;
    public int worldX, worldY; // game camera
    public int speed;

    public BufferedImage up1, up2, up3, up4, up5, up6,
            down1, down2, down3, down4, down5, down6,
            left1, left2, left3, left4, left5, left6,
            right1, right2, right3, right4, right5, right6,
            idle1, idle2, idle3, idle4, idle5, idle6;
    public String direction = "down";

    public int spriteNum = 1;

    public int solidAreaDefaultX, solidAreaDefaultY;
    // STATE hit box
    public boolean collisionOn = false;
    boolean attacking = false;
    //default entity hitbox
    public Rectangle solidArea = new Rectangle(0, 0, 32, 32);

    //NPC variables
    public int actionLockCounter = 0;
    String[] dialogues = new String [20];
    int dialogueIndex = 0;

    //Character and monster  status
    public int type; // 0 - player, 1 = npc, 2 = shroom

    public int maxLife;
    public int life;
    public boolean alive = true;
    public boolean dying = false;
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

        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);

        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        if(this.type ==2 && contactPlayer){
            // damage
            gp.player.life -= 1;
            gp.player.invincible = true;
        }
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
        if(invincible){
            invincibleCounter++;
            if(invincibleCounter > 30){
                invincible =false;
                invincibleCounter = 0;
            }
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
            //make transparent
            if(invincible){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            }
            g2.drawImage(image, screenX , screenY , gp.tileSize, gp.tileSize, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

//        // collision trouble shoot
            g2.setColor(Color.red);
            g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
            if(dying){
                dyingAnimation(g2);
            }

        }
    }
    public void dyingAnimation(Graphics2D g2){
        dyingCounter++;
        if (dyingCounter >= 1 && dyingCounter <= 3) {
            image = dead1;
        } else if (dyingCounter >= 4 && dyingCounter <= 6) {
            image = dead2;
        } else if (dyingCounter >= 7 && dyingCounter <= 9) {
            image = dead3;
        } else if (dyingCounter >= 10 && dyingCounter <= 12) {
            image = dead4;
        } else if (dyingCounter >= 13 && dyingCounter <= 15) {
            image = dead5;
        } else if (dyingCounter >= 16 && dyingCounter <= 18) {
            image = dead6;
        } else if (dyingCounter >= 19 && dyingCounter <= 21) {
            image = dead7;
        } else if (dyingCounter >= 22 && dyingCounter <= 24) {
            image = dead8;
        } else if (dyingCounter >= 25 && dyingCounter <= 27) {
            image = dead9;
        } else if (dyingCounter >= 28 && dyingCounter <= 30) {
            image = dead10;
        } else if (dyingCounter >= 31 && dyingCounter <= 33) {
            image = dead11;
        } else if (dyingCounter >= 34 && dyingCounter <= 36) {
            image = dead12;
        } else if (dyingCounter >= 37 && dyingCounter <= 39) {
            image = dead13;
        } else if (dyingCounter >= 40 && dyingCounter <= 42) {
            image = dead14;
        } else if (dyingCounter > 42) {
            dying = false;
            alive = false;
        }
    }
    public BufferedImage setup(String imagePath, int width, int height) {
        UltilityTool uTool = new UltilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaleImage(image, width, height);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public BufferedImage setup(String imagePath) {
        UltilityTool uTool = new UltilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void getDyingImages() {
        dead1 = setup("/effects/dead/Dead1");
        dead2 = setup("/effects/dead/Dead2");
        dead3 = setup("/effects/dead/Dead3");
        dead4 = setup("/effects/dead/Dead4");
        dead5 = setup("/effects/dead/Dead5");
        dead6 = setup("/effects/dead/Dead6");
        dead7 = setup("/effects/dead/Dead7");
        dead8 = setup("/effects/dead/Dead8");
        dead9 = setup("/effects/dead/Dead9");
        dead10 = setup("/effects/dead/Dead10");
        dead11 = setup("/effects/dead/Dead11");
        dead12 = setup("/effects/dead/Dead12");
        dead13 = setup("/effects/dead/Dead13");
        dead14 = setup("/effects/dead/Dead14");

    }
}
