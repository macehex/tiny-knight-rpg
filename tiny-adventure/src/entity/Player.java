package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UltilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
        solidAreaDefaultY = solidArea.y;

        attackArea.width = 48;
        attackArea.height = 48;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues() {
        // PLACEHOLDER
        worldX = gp.tileSize * 25; //CHANGE LATER ASAP
        worldY = gp.tileSize * 11;
        speed = 4;
        direction = "idle";
        // PLAYER STATUS
        maxLife = 5;
        life = maxLife;
    }

    public void getPlayerImage() {
        up1 = setup("/player/warrior_runu1", gp.tileSize * 2, gp.tileSize * 2);
        up2 = setup("/player/warrior_runu2", gp.tileSize * 2, gp.tileSize * 2);
        up3 = setup("/player/warrior_runu3", gp.tileSize * 2, gp.tileSize * 2);
        up4 = setup("/player/warrior_runu4", gp.tileSize * 2, gp.tileSize * 2);
        up5 = setup("/player/warrior_runu5", gp.tileSize * 2, gp.tileSize * 2);
        up6 = setup("/player/warrior_runu6", gp.tileSize * 2, gp.tileSize * 2);

        down1 = setup("/player/warrior_rund1", gp.tileSize * 2, gp.tileSize * 2);
        down2 = setup("/player/warrior_rund2", gp.tileSize * 2, gp.tileSize * 2);
        down3 = setup("/player/warrior_rund3", gp.tileSize * 2, gp.tileSize * 2);
        down4 = setup("/player/warrior_rund4", gp.tileSize * 2, gp.tileSize * 2);
        down5 = setup("/player/warrior_rund5", gp.tileSize * 2, gp.tileSize * 2);
        down6 = setup("/player/warrior_rund6", gp.tileSize * 2, gp.tileSize * 2);

        left1 = setup("/player/warrior_runl1", gp.tileSize * 2, gp.tileSize * 2);
        left2 = setup("/player/warrior_runl2", gp.tileSize * 2, gp.tileSize * 2);
        left3 = setup("/player/warrior_runl3", gp.tileSize * 2, gp.tileSize * 2);
        left4 = setup("/player/warrior_runl4", gp.tileSize * 2, gp.tileSize * 2);
        left5 = setup("/player/warrior_runl5", gp.tileSize * 2, gp.tileSize * 2);
        left6 = setup("/player/warrior_runl6", gp.tileSize * 2, gp.tileSize * 2);

        right1 = setup("/player/warrior_runr1", gp.tileSize * 2, gp.tileSize * 2);
        right2 = setup("/player/warrior_runr2", gp.tileSize * 2, gp.tileSize * 2);
        right3 = setup("/player/warrior_runr3", gp.tileSize * 2, gp.tileSize * 2);
        right4 = setup("/player/warrior_runr4", gp.tileSize * 2, gp.tileSize * 2);
        right5 = setup("/player/warrior_runr5", gp.tileSize * 2, gp.tileSize * 2);
        right6 = setup("/player/warrior_runr6", gp.tileSize * 2, gp.tileSize * 2);

        idle1 = setup("/player/idle/Warrior_idle1", gp.tileSize * 2, gp.tileSize * 2);
        idle2 = setup("/player/idle/Warrior_idle2", gp.tileSize * 2, gp.tileSize * 2);
        idle3 = setup("/player/idle/Warrior_idle3", gp.tileSize * 2, gp.tileSize * 2);
        idle4 = setup("/player/idle/Warrior_idle4", gp.tileSize * 2, gp.tileSize * 2);
        idle5 = setup("/player/idle/Warrior_idle1", gp.tileSize * 2, gp.tileSize * 2);
        idle6 = setup("/player/idle/Warrior_idle1", gp.tileSize * 2, gp.tileSize * 2);
    }

    public void getPlayerAttackImage() {
        attackUp1 = setup("/player/attack/upa/u1", gp.tileSize * 2, gp.tileSize * 2);
        attackUp2 = setup("/player/attack/upa/u2", gp.tileSize * 2, gp.tileSize * 2);
        attackUp3 = setup("/player/attack/upa/u3", gp.tileSize * 2, gp.tileSize * 2);
        attackUp4 = setup("/player/attack/upa/u4", gp.tileSize * 2, gp.tileSize * 2);
        attackUp5 = setup("/player/attack/upa/u5", gp.tileSize * 2, gp.tileSize * 2);
        attackUp6 = setup("/player/attack/upa/u6", gp.tileSize * 2, gp.tileSize * 2);

        attackRight1 = setup("/player/attack/righta/r1", gp.tileSize * 2, gp.tileSize * 2);
        attackRight2 = setup("/player/attack/righta/r2", gp.tileSize * 2, gp.tileSize * 2);
        attackRight3 = setup("/player/attack/righta/r3", gp.tileSize * 2, gp.tileSize * 2);
        attackRight4 = setup("/player/attack/righta/r4", gp.tileSize * 2, gp.tileSize * 2);
        attackRight5 = setup("/player/attack/righta/r5", gp.tileSize * 2, gp.tileSize * 2);
        attackRight6 = setup("/player/attack/righta/r6", gp.tileSize * 2, gp.tileSize * 2);

        attackLeft1 = setup("/player/attack/lefta/l1", gp.tileSize * 2, gp.tileSize * 2);
        attackLeft2 = setup("/player/attack/lefta/l2", gp.tileSize * 2, gp.tileSize * 2);
        attackLeft3 = setup("/player/attack/lefta/l3", gp.tileSize * 2, gp.tileSize * 2);
        attackLeft4 = setup("/player/attack/lefta/l4", gp.tileSize * 2, gp.tileSize * 2);
        attackLeft5 = setup("/player/attack/lefta/l5", gp.tileSize * 2, gp.tileSize * 2);
        attackLeft6 = setup("/player/attack/lefta/l6", gp.tileSize * 2, gp.tileSize * 2);

        attackDown1 = setup("/player/attack/downa/d1", gp.tileSize * 2, gp.tileSize * 2);
        attackDown2 = setup("/player/attack/downa/d2", gp.tileSize * 2, gp.tileSize * 2);
        attackDown3 = setup("/player/attack/downa/d3", gp.tileSize * 2, gp.tileSize * 2);
        attackDown4 = setup("/player/attack/downa/d4", gp.tileSize * 2, gp.tileSize * 2);
        attackDown5 = setup("/player/attack/downa/d5", gp.tileSize * 2, gp.tileSize * 2);
        attackDown6 = setup("/player/attack/downa/d6", gp.tileSize * 2, gp.tileSize * 2);
    }

    public void update() {
        //idle state
        if (attacking) {
            attacking();
        } else if (!(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed)) {
            direction = "idle";
            //CHECK NPC collision for idle case
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);


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
            //Check Monster collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);
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
        //outside of key statement
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 100) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if (life <= 0) {
            gp.playSoundEffect(11);
            gp.gameState = gp.gameOverState;
        }
    }

    public void draw(Graphics2D g2) {

//        g2.setColor(Color.white);
//        g.fillRect(x, y, width, height);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;
        switch (direction) {
            case "idle":
                if (!attacking) {
                    image = switch (spriteNum) {
                        case 1 -> idle1;
                        case 2 -> idle2;
                        case 3 -> idle3;
                        case 4 -> idle4;
                        case 5 -> idle5;
                        case 6 -> idle6;
                        default -> image;
                    };
                } else {
                    direction = "right";
                    image = switch (spriteNum) {
                        case 1 -> attackRight1;
                        case 2 -> attackRight2;
                        case 3 -> attackRight3;
                        case 4 -> attackRight4;
                        case 5 -> attackRight5;
                        case 6 -> attackRight6;
                        default -> image;
                    };
                }
                break;

            case "up":
                if (!attacking) {
                    image = switch (spriteNum) {
                        case 1 -> up1;
                        case 2 -> up2;
                        case 3 -> up3;
                        case 4 -> up4;
                        case 5 -> up5;
                        case 6 -> up6;
                        default -> image;
                    };
                } else {
                    image = switch (spriteNum) {
                        case 1 -> attackUp1;
                        case 2 -> attackUp2;
                        case 3 -> attackUp3;
                        case 4 -> attackUp4;
                        case 5 -> attackUp5;
                        case 6 -> attackUp6;
                        default -> image;
                    };
                }
                break;

            case "down":
                if (!attacking) {
                    image = switch (spriteNum) {
                        case 1 -> down1;
                        case 2 -> down2;
                        case 3 -> down3;
                        case 4 -> down4;
                        case 5 -> down5;
                        case 6 -> down6;
                        default -> image;
                    };
                } else {
                    image = switch (spriteNum) {
                        case 1 -> attackDown1;
                        case 2 -> attackDown2;
                        case 3 -> attackDown3;
                        case 4 -> attackDown4;
                        case 5 -> attackDown5;
                        case 6 -> attackDown6;
                        default -> image;
                    };
                }
                break;

            case "left":
                if (!attacking) {
                    image = switch (spriteNum) {
                        case 1 -> left1;
                        case 2 -> left2;
                        case 3 -> left3;
                        case 4 -> left4;
                        case 5 -> left5;
                        case 6 -> left6;
                        default -> image;
                    };
                } else {
                    image = switch (spriteNum) {
                        case 1 -> attackLeft1;
                        case 2 -> attackLeft2;
                        case 3 -> attackLeft3;
                        case 4 -> attackLeft4;
                        case 5 -> attackLeft5;
                        case 6 -> attackLeft6;
                        default -> image;
                    };
                }
                break;

            case "right":
                if (!attacking) {
                    image = switch (spriteNum) {
                        case 1 -> right1;
                        case 2 -> right2;
                        case 3 -> right3;
                        case 4 -> right4;
                        case 5 -> right5;
                        case 6 -> right6;
                        default -> image;
                    };
                } else {
                    image = switch (spriteNum) {
                        case 1 -> attackRight1;
                        case 2 -> attackRight2;
                        case 3 -> attackRight3;
                        case 4 -> attackRight4;
                        case 5 -> attackRight5;
                        case 6 -> attackRight6;
                        default -> image;
                    };
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, null);
//        // collision trouble shoot
        g2.setColor(Color.red);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);


    }

    public void attacking() {
        spriteCounter++;
        if (spriteCounter <= 3) {
            spriteNum = 1;
        }
        if (spriteCounter > 3 && spriteCounter <= 8) {
            spriteNum = 2;
        }
        if (spriteCounter > 12 && spriteCounter <= 15) {
            spriteNum = 3;
        }
        if (spriteCounter > 15 && spriteCounter <= 22) {
            spriteNum = 4;
            checkAttacking();
        }
        if (spriteCounter > 22 && spriteCounter <= 28) {
            spriteNum = 5;
            checkAttacking();
        }
        if (spriteCounter > 28 && spriteCounter <= 30) {
            spriteNum = 6;
        }
        if (spriteCounter > 30) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            //if i not equal to object index
            String objectName = gp.obj[gp.currentMap][i].name;
            switch (objectName) {
                case "Key":
                    gp.playSoundEffect(5);
                    hasKey++;
                    gp.obj[gp.currentMap][i] = null; // delete touched object
                    gp.ui.showMessage("Picked up key");
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.playSoundEffect(3);
                        gp.obj[gp.currentMap][i] = null;
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
                    gp.obj[gp.currentMap][i] = null;
                    gp.ui.showMessage("Picked up speed potion");
                    break;
                case "Health Potion 2":
                    gp.playSoundEffect(4);
                    if (gp.player.life < gp.player.maxLife + 2) {
                        gp.player.life += 2;
                    } else {
                        gp.player.life = gp.player.maxLife;
                    }
                    gp.obj[gp.currentMap][i] = null;
                    gp.ui.showMessage("Picked up health+2 potion");
                    break;

            }
        }

    }

    public void contactMonster(int i) {
        if (i != 999) {
            // resume  for 2 sec
            if (!invincible && !gp.monster[gp.currentMap][i].dying && gp.monster[gp.currentMap][i].alive) {
                gp.playSoundEffect(8);
                life -= 1;
                invincible = true;
            }
        }
        if (gp.keyH.kPressed) {
            attacking = true;
        }
    }

    public void interactNPC(int i) {
        if (i != 999) {
            System.out.println("Hitting an npc");
            if (gp.keyH.fPressed) {
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
            }
            gp.keyH.fPressed = false;
        }
        if (gp.keyH.kPressed) {
            gp.playSoundEffect(7);
            attacking = true;
        }
    }

    public void interactKing(int i) {
        gp.gameState = gp.dialogueState;
    }


    public void checkAttacking() {
        int currentWorldX = worldX;
        int currentWorldY = worldY;
        int solidAreaWidth = solidArea.width;
        int solidAreaHeight = solidArea.height;

        switch (direction) {
            case "up":
                worldY -= attackArea.height;
                solidArea.height = attackArea.height;
                break;
            case "down":
                worldY += attackArea.height;
                solidArea.height = attackArea.height;
                break;
            case "right":
                worldX += attackArea.width; // should add for right
                solidArea.width = attackArea.width;
                break;
            case "left":
                worldX -= attackArea.width; // should subtract for left
                solidArea.width = attackArea.width;
                break;
        }
        if (direction.equals("up") || direction.equals("down")) {
            solidArea.width = attackArea.width;
        } else {
            solidArea.height = attackArea.height;
        }

        int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
        damageMonster(monsterIndex);

        worldX = currentWorldX;
        worldY = currentWorldY;
        solidArea.width = solidAreaWidth;
        solidArea.height = solidAreaHeight;
    }

    public void damageMonster(int i) {
        if (i != 999) {
            System.out.println("Attack dealt!");
            if (!gp.monster[gp.currentMap][i].invincible) {
                gp.playSoundEffect(9);
                gp.monster[gp.currentMap][i].life -= 1;
                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();
                if (gp.monster[gp.currentMap][i].life <= 0) {
                    gp.monster[gp.currentMap][i].dying = true;
                }
            }
        } else {
            System.out.println("Missed attack!");
        }
    }
}
