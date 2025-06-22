package model.entity;

import controller.GamePanel;
import controller.KeyHandler;
import model.object.OBJ_Chest;
import model.object.OBJ_ChestOpened;
import model.object.OBJ_DoorOpened;
import model.object.OBJ_SWORD;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity {
    KeyHandler keyH;
    public final int screenX;
    public final int screenY; // background scroll
    public boolean hasLadder = false;
    public int damage;
    public int gold;
    // key item slot
    private int hasKey = 0;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 24;
    private boolean isSwim;
    private int counterWater=0;
    private int tempX;
    private int tempY;
    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        isSwim = false;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - gp.getTileSize() / 2;
        screenY = gp.screenHeight / 2 - gp.getTileSize() / 2;

        solidArea = new Rectangle();
        // PLAYER HIT BOX
        solidArea.x = 40;
        solidArea.y = 28;
        solidArea.width = 16;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea.width = 48;
        attackArea.height = 48;
        setDefaultValues();
        knockBackPower = 5;
        getPlayerImage();
        getPlayerAttackImage();
        getPlayerSwimmingImage();
        setItems();
    }

    public void setDefaultValues() {
        // PLACEHOLDER
        worldX = gp.getTileSize() * 12; //CHANGE LATER ASAP
        worldY = gp.getTileSize()  * 33;
        speed = 8;
        direction = "idle";
        // PLAYER STATUS
        maxLife = 5;
        life = maxLife;
        currentWeapon = new OBJ_SWORD(gp);
        hasKey = 0;
        damage = 1;
        gold = 0;

    }
    public void setDefaultPosition(){
        worldX = gp.getTileSize()  * 12; //CHANGE LATER ASAP
        worldY = gp.getTileSize()  * 33;
        direction = "idle";
    }
    public void restoreLife(){
        life = maxLife;
        invincible =false;
    }
    public void setItems() {
        inventory.add(currentWeapon);
    }

    private void getPlayerImage() {
        up1 = setup("/player/warrior_runu1", gp.getTileSize()  * 2, gp.getTileSize()  * 2);
        up2 = setup("/player/warrior_runu2", gp.getTileSize()  * 2, gp.getTileSize()  * 2);
        up3 = setup("/player/warrior_runu3", gp.getTileSize()  * 2, gp.getTileSize()  * 2);
        up4 = setup("/player/warrior_runu4", gp.getTileSize()  * 2, gp.getTileSize()  * 2);
        up5 = setup("/player/warrior_runu5", gp.getTileSize()  * 2, gp.getTileSize()  * 2);
        up6 = setup("/player/warrior_runu6", gp.getTileSize()  * 2, gp.getTileSize()  * 2);

        down1 = setup("/player/warrior_rund1", gp.getTileSize() * 2, gp.getTileSize() * 2);
        down2 = setup("/player/warrior_rund2", gp.getTileSize()  * 2, gp.getTileSize()  * 2);
        down3 = setup("/player/warrior_rund3", gp.getTileSize() * 2, gp.getTileSize()  * 2);
    down4 = setup("/player/warrior_rund4", gp.getTileSize()  * 2, gp.getTileSize()  * 2);
        down5 = setup("/player/warrior_rund5", gp.getTileSize()  * 2, gp.getTileSize()  * 2);
        down6 = setup("/player/warrior_rund6", gp.getTileSize() * 2, gp.getTileSize()  * 2);

        left1 = setup("/player/warrior_runl1", gp.getTileSize()  * 2, gp.getTileSize()  * 2);
        left2 = setup("/player/warrior_runl2", gp.getTileSize()  * 2, gp.getTileSize()  * 2);
        left3 = setup("/player/warrior_runl3", gp.getTileSize()  * 2, gp.getTileSize()  * 2);
        left4 = setup("/player/warrior_runl4", gp.getTileSize()  * 2, gp.getTileSize()  * 2);
        left5 = setup("/player/warrior_runl5", gp.getTileSize()  * 2, gp.getTileSize()  * 2);
        left6 = setup("/player/warrior_runl6", gp.getTileSize() * 2, gp.getTileSize()  * 2);

        right1 = setup("/player/warrior_runr1", gp.getTileSize()  * 2, gp.getTileSize()  * 2);
        right2 = setup("/player/warrior_runr2", gp.getTileSize()  * 2, gp.getTileSize()  * 2);
        right3 = setup("/player/warrior_runr3", gp.getTileSize()  * 2, gp.getTileSize()  * 2);
        right4 = setup("/player/warrior_runr4", gp.getTileSize()  * 2, gp.getTileSize()  * 2);
        right5 = setup("/player/warrior_runr5", gp.getTileSize()  * 2, gp.getTileSize()  * 2);
        right6 = setup("/player/warrior_runr6", gp.getTileSize()  * 2, gp.getTileSize()  * 2);

        idle1 = setup("/player/idle/Warrior_idle1", gp.getTileSize()  * 2, gp.getTileSize()  * 2);
        idle2 = setup("/player/idle/Warrior_idle2", gp.getTileSize()  * 2, gp.getTileSize()  * 2);
        idle3 = setup("/player/idle/Warrior_idle3", gp.getTileSize()  * 2, gp.getTileSize()  * 2);
        idle4 = setup("/player/idle/Warrior_idle4", gp.getTileSize()  * 2, gp.getTileSize()  * 2);
        idle5 = setup("/player/idle/Warrior_idle1", gp.getTileSize()  * 2, gp.getTileSize()  * 2);
        idle6 = setup("/player/idle/Warrior_idle1", gp.getTileSize()  * 2, gp.getTileSize()  * 2);
    }

private void getPlayerSwimmingImage() {
    swim_up1 = setup("/player/swim/u1", gp.getTileSize() * 2, gp.getTileSize() * 2);
    swim_up2 = setup("/player/swim/u2", gp.getTileSize() * 2, gp.getTileSize() * 2);
    swim_up3 = setup("/player/swim/u3", gp.getTileSize() * 2, gp.getTileSize() * 2);
    swim_up4 = setup("/player/swim/u4", gp.getTileSize() * 2, gp.getTileSize() * 2);
    swim_up5 = setup("/player/swim/u5", gp.getTileSize() * 2, gp.getTileSize() * 2);
    swim_up6 = setup("/player/swim/u6", gp.getTileSize() * 2, gp.getTileSize() * 2);

    swim_down1 = setup("/player/swim/d1", gp.getTileSize() * 2, gp.getTileSize() * 2);
    swim_down2 = setup("/player/swim/d2", gp.getTileSize() * 2, gp.getTileSize() * 2);
    swim_down3 = setup("/player/swim/d3", gp.getTileSize() * 2, gp.getTileSize() * 2);
    swim_down4 = setup("/player/swim/d4", gp.getTileSize() * 2, gp.getTileSize() * 2);
    swim_down5 = setup("/player/swim/d5", gp.getTileSize() * 2, gp.getTileSize() * 2);
    swim_down6 = setup("/player/swim/d6", gp.getTileSize() * 2, gp.getTileSize() * 2);

    swim_left1 = setup("/player/swim/l1", gp.getTileSize() * 2, gp.getTileSize() * 2);
    swim_left2 = setup("/player/swim/l2", gp.getTileSize() * 2, gp.getTileSize() * 2);
    swim_left3 = setup("/player/swim/l3", gp.getTileSize() * 2, gp.getTileSize() * 2);
    swim_left4 = setup("/player/swim/l4", gp.getTileSize() * 2, gp.getTileSize() * 2);
    swim_left5 = setup("/player/swim/l5", gp.getTileSize() * 2, gp.getTileSize() * 2);
    swim_left6 = setup("/player/swim/l6", gp.getTileSize() * 2, gp.getTileSize() * 2);

    swim_right1 = setup("/player/swim/r1", gp.getTileSize() * 2, gp.getTileSize() * 2);
    swim_right2 = setup("/player/swim/r2", gp.getTileSize() * 2, gp.getTileSize() * 2);
    swim_right3 = setup("/player/swim/r3", gp.getTileSize() * 2, gp.getTileSize() * 2);
    swim_right4 = setup("/player/swim/r4", gp.getTileSize() * 2, gp.getTileSize() * 2);
    swim_right5 = setup("/player/swim/r5", gp.getTileSize() * 2, gp.getTileSize() * 2);
    swim_right6 = setup("/player/swim/r6", gp.getTileSize() * 2, gp.getTileSize() * 2);

    swim_idle1 = setup("/player/swim/d1", gp.getTileSize() * 2, gp.getTileSize() * 2);
    swim_idle2 = setup("/player/swim/d2", gp.getTileSize() * 2, gp.getTileSize() * 2);
    swim_idle3 = setup("/player/swim/d3", gp.getTileSize() * 2, gp.getTileSize() * 2);
    swim_idle4 = setup("/player/swim/d4", gp.getTileSize() * 2, gp.getTileSize() * 2);
    swim_idle5 = setup("/player/swim/d5", gp.getTileSize() * 2, gp.getTileSize() * 2);
    swim_idle6 = setup("/player/swim/d6", gp.getTileSize() * 2, gp.getTileSize() * 2);
}
    private void getPlayerAttackImage() {
        attackUp1 = setup("/player/attack/upa/u1", gp.getTileSize() * 2, gp.getTileSize() * 2);
        attackUp2 = setup("/player/attack/upa/u2", gp.getTileSize() * 2, gp.getTileSize() * 2);
        attackUp3 = setup("/player/attack/upa/u3", gp.getTileSize() * 2, gp.getTileSize() * 2);
        attackUp4 = setup("/player/attack/upa/u4", gp.getTileSize() * 2, gp.getTileSize() * 2);
        attackUp5 = setup("/player/attack/upa/u5", gp.getTileSize() * 2, gp.getTileSize() * 2);
        attackUp6 = setup("/player/attack/upa/u6", gp.getTileSize() * 2, gp.getTileSize() * 2);

        attackRight1 = setup("/player/attack/righta/r1", gp.getTileSize() * 2, gp.getTileSize() * 2);
        attackRight2 = setup("/player/attack/righta/r2", gp.getTileSize() * 2, gp.getTileSize() * 2);
        attackRight3 = setup("/player/attack/righta/r3", gp.getTileSize() * 2, gp.getTileSize() * 2);
        attackRight4 = setup("/player/attack/righta/r4", gp.getTileSize() * 2, gp.getTileSize() * 2);
        attackRight5 = setup("/player/attack/righta/r5", gp.getTileSize() * 2, gp.getTileSize() * 2);
        attackRight6 = setup("/player/attack/righta/r6", gp.getTileSize() * 2, gp.getTileSize() * 2);

        attackLeft1 = setup("/player/attack/lefta/l1", gp.getTileSize() * 2, gp.getTileSize() * 2);
        attackLeft2 = setup("/player/attack/lefta/l2", gp.getTileSize() * 2, gp.getTileSize() * 2);
        attackLeft3 = setup("/player/attack/lefta/l3", gp.getTileSize() * 2, gp.getTileSize() * 2);
        attackLeft4 = setup("/player/attack/lefta/l4", gp.getTileSize() * 2, gp.getTileSize() * 2);
        attackLeft5 = setup("/player/attack/lefta/l5", gp.getTileSize() * 2, gp.getTileSize() * 2);
        attackLeft6 = setup("/player/attack/lefta/l6", gp.getTileSize() * 2, gp.getTileSize() * 2);

        attackDown1 = setup("/player/attack/downa/d1", gp.getTileSize() * 2, gp.getTileSize() * 2);
        attackDown2 = setup("/player/attack/downa/d2", gp.getTileSize() * 2, gp.getTileSize() * 2);
        attackDown3 = setup("/player/attack/downa/d3", gp.getTileSize() * 2, gp.getTileSize() * 2);
        attackDown4 = setup("/player/attack/downa/d4", gp.getTileSize() * 2, gp.getTileSize() * 2);
        attackDown5 = setup("/player/attack/downa/d5", gp.getTileSize() * 2, gp.getTileSize() * 2);
        attackDown6 = setup("/player/attack/downa/d6", gp.getTileSize() * 2, gp.getTileSize() * 2);
    }
    public void update() {
        checkSwimming();
        //idle state
        if (attacking) {
            attacking();
        }else if (!(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed)) {
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

        BufferedImage image = null;
        if(!isSwim){
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

        }else{
            switch (direction) {
                case "idle":
                    image = switch (spriteNum) {
                        case 1 -> swim_idle1;
                        case 2 -> swim_idle2;
                        case 3 -> swim_idle3;
                        case 4 -> swim_idle4;
                        case 5 -> swim_idle5;
                        case 6 -> swim_idle6;
                        default -> image;
                    };
                    break;

                case "up":
                    image = switch (spriteNum) {
                        case 1 -> swim_up1;
                        case 2 -> swim_up2;
                        case 3 -> swim_up3;
                        case 4 -> swim_up4;
                        case 5 -> swim_up5;
                        case 6 -> swim_up6;
                        default -> image;
                    };
                    break;

                case "down":
                    image = switch (spriteNum) {
                        case 1 -> swim_down1;
                        case 2 -> swim_down2;
                        case 3 -> swim_down3;
                        case 4 -> swim_down4;
                        case 5 -> swim_down5;
                        case 6 -> swim_down6;
                        default -> image;
                    };
                    break;

                case "left":
                    image = switch (spriteNum) {
                        case 1 -> swim_left1;
                        case 2 -> swim_left2;
                        case 3 -> swim_left3;
                        case 4 -> swim_left4;
                        case 5 -> swim_left5;
                        case 6 -> swim_left6;
                        default -> image;
                    };
                    break;

                case "right":
                    image = switch (spriteNum) {
                        case 1 -> swim_right1;
                        case 2 -> swim_right2;
                        case 3 -> swim_right3;
                        case 4 -> swim_right4;
                        case 5 -> swim_right5;
                        case 6 -> swim_right6;
                        default -> image;
                    };
                    break;
            }

        }


        g2.drawImage(image, screenX, screenY, null);
//        // HIT BOX troubleshooting
        g2.setColor(Color.red);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);


    }
    @Override
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

    private void pickUpObject(int i) {
        if (i != 999) {
            //if i not equal to object index
            String objectName = gp.obj[gp.getCurrentMap()][i].name;
            if (inventory.size() != maxInventorySize)
                switch (objectName) {
                    case "Gold":
                        gp.playSoundEffect(5);

                        gp.obj[gp.getCurrentMap()][i] = null;
                        gold +=100;
                        gp.ui.addMessage("You got 100 golds!");
                        break;
                    case "Key":
                        gp.playSoundEffect(5);
                        hasKey++;
                        inventory.add(gp.obj[gp.getCurrentMap()][i]);
                        gp.obj[gp.getCurrentMap()][i] = null; // delete touched object
                        gp.ui.addMessage("Picked up key!");
                        break;
                    case "Door":
                        if (hasKey > 0) {
                            gp.playSoundEffect(3);
//                            gp.obj[gp.getCurrentMap()][i] = null;
                            tempX = gp.obj[gp.getCurrentMap()][i].worldX;
                            tempY = gp.obj[gp.getCurrentMap()][i].worldY;
                            gp.obj[gp.getCurrentMap()][i] = new OBJ_DoorOpened(gp);
                            gp.obj[gp.getCurrentMap()][i].worldX = tempX;
                            gp.obj[gp.getCurrentMap()][i].worldY = tempY;


                            for (int j = 0; j < inventory.size(); j++) {
                                if (inventory.get(j).name.equals("Key")) {
                                    inventory.remove(j);
                                    break;
                                }
                            }
                            hasKey--;
                            gp.ui.addMessage("Door opened!");
                        } else {
                            gp.ui.addMessage("Find a key!");
                        }
                        break;
                    case "Chest":
                        gp.ui.addMessage("Opening a chest!");
                        gp.playSoundEffect(0);
//                    gp.ui.gameFinished = true;
                        ((OBJ_Chest) gp.obj[gp.getCurrentMap()][i]).randomReward();
                        tempX = gp.obj[gp.getCurrentMap()][i].worldX;
                        tempY = gp.obj[gp.getCurrentMap()][i].worldY;
                        gp.obj[gp.getCurrentMap()][i] = new OBJ_ChestOpened(gp);
                        gp.obj[gp.getCurrentMap()][i].worldX = tempX;
                        gp.obj[gp.getCurrentMap()][i].worldY = tempY;

                        break;
                    case "Speed Potion": //increase movement speed
                        gp.playSoundEffect(4);
                        speed += 1;
                        gp.obj[gp.getCurrentMap()][i] = null;
                        gp.ui.addMessage("Picked up speed potion!");
                        break;
                    case "Strength Potion":
                        //adding extra damage to monster later
                        gp.playSoundEffect(4);
                        maxLife += 2;
                        gp.ui.drawPlayerLife();
                        gp.obj[gp.getCurrentMap()][i] = null;
                        gp.ui.addMessage("Picked up strength potion!");
                        gp.ui.addMessage("You got stronger!");
                        gp.player.damage++;
                        gp.ui.addMessage("Your damage increased by 1!");
                        break;
                    case "Health Potion 2":
                        gp.playSoundEffect(4);
                        if (gp.player.life < gp.player.maxLife - 1) {
                            gp.player.life += 2;
                        } else if(gp.player.life < gp.player.maxLife){
                            gp.player.life++;
                        }else{
                            gp.ui.addMessage("You are already at max life!");
                            break;
                        }
                        gp.obj[gp.getCurrentMap()][i] = null;
                        gp.ui.addMessage("Picked up health+2 potion!");
                        break;
                    case "Spikes":
                        if (!gp.player.invincible) {
                            // damage
                            gp.playSoundEffect(8);
                            gp.player.life -= 1;
                            gp.player.invincible = true;
                            gp.ui.addMessage("Get out of the spikes!");
                        }
                        break;
                    case "Ladder":
                        gp.aSetter.setLadder();
                        hasLadder = true;
                        gp.playSoundEffect(4);
                        inventory.add(gp.obj[gp.getCurrentMap()][i]);
                        gp.obj[gp.getCurrentMap()][i] = null;
                        break;
                }
            else gp.ui.addMessage("You cannot carry any more!");
        }

    }

    private void contactMonster(int i) {
        if (i != 999) {
            // resume  for 2 sec
            if (!invincible && !gp.monster[gp.getCurrentMap()][i].dying && gp.monster[gp.getCurrentMap()][i].alive) {
                gp.playSoundEffect(8);
                life -= 1;
                invincible = true;
            }
        }
        if (gp.keyH.kPressed) {
            attacking = true;
        }
    }
    private void contactWater(int count){
        if (!invincible && count > 50) {
            gp.playSoundEffect(8);
            life -= 1;
            invincible = true;
        }

    }
    private void interactNPC(int i) {
        if (i != 999) {
            if (gp.keyH.enterPressed) {
                gp.gameState = gp.dialogueState;
                gp.npc[gp.getCurrentMap()][i].speak();
            }
            gp.keyH.enterPressed = false;
        }
    }

    private void interactKing(int i) {
        gp.gameState = gp.dialogueState;
    }

    private void checkSwimming() {
        int tileNum = gp.tileM.mapTileNum[gp.getCurrentMap()][worldX / gp.getTileSize()][worldY / gp.getTileSize()];
        if (gp.tileM.tile[tileNum].isWater) {
            isSwim = true;
            speed = 4;
            counterWater++;
            contactWater(counterWater);
        } else {
            isSwim = false;
            speed = 8;
            counterWater = 0;
        }
    }    private void checkAttacking() {
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
        gp.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);


        worldX = currentWorldX;
        worldY = currentWorldY;
        solidArea.width = solidAreaWidth;
        solidArea.height = solidAreaHeight;
    }
    public void knockBack(Entity entity){
        entity.direction = direction;
        entity.speed += 10;
        entity.knockBack = true;
    }
//    private void damageMonster(int i) {
//        if (i != 999) {
//            if (!gp.monster[gp.getCurrentMap()][i].invincible) {
//                gp.playSoundEffect(9);
//                gp.monster[gp.getCurrentMap()][i].life -= gp.player.damage;
//                gp.monster[gp.getCurrentMap()][i].invincible = true;
//                gp.monster[gp.getCurrentMap()][i].damageReaction();
//                if (gp.monster[gp.getCurrentMap()][i].life <= 0) {
//                    gp.monster[gp.getCurrentMap()][i].dying = true;
//                }
//            }
//        } else {
//            System.out.println("Missed attack!");
//        }
//    }
    public void damageMonster(int i, Entity attacker, int attack, int knockBackPower)
    {
        if(i != 999)
        {
            if(gp.monster[gp.getCurrentMap()][i].invincible == false)
            {
                gp.playSoundEffect(7);   //hitmonster.wav

                if(knockBackPower > 0)
                {
                    setKnockBack(gp.monster[gp.getCurrentMap()][i], attacker, knockBackPower);
                }
                if(gp.monster[gp.getCurrentMap()][i].offBalance == true)
                {
                    attack *= 2;
                }
                int damage = attack - gp.monster[gp.getCurrentMap()][i].defense;
                if(damage <= 0 )
                {
                    damage = 1;
                }
                gp.monster[gp.getCurrentMap()][i].life -= damage;
                gp.ui.addMessage(damage + " damage!");
                gp.monster[gp.getCurrentMap()][i].invincible = true;
                gp.monster[gp.getCurrentMap()][i].damageReaction();  //run away from player

                if(gp.monster[gp.getCurrentMap()][i].life <= 0)
                {
                    gp.monster[gp.getCurrentMap()][i].dying = true;
                    gp.ui.addMessage("Killed the " + gp.monster[gp.getCurrentMap()][i].name + "!");
                    gp.ui.addMessage("Exp +" + gp.monster[gp.getCurrentMap()][i].exp + "!");
                    exp += gp.monster[gp.getCurrentMap()][i].exp;
                }
            }
        }
    }
    public String getDirection() {
        return direction;
    }

}

