package model.monster;


import controller.GamePanel;

import java.util.Random;

public class MON_Orc extends MON {
    GamePanel gp; // cuz of different package

    public MON_Orc(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = 2;
        name = "Orc";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 8;
        life = maxLife;
        attack = 1;
        defense = 2;
        exp = 8;
        knockBackPower = 5;

        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;
        motion1_duration = 40;
        motion2_duration = 85;

        getImage();
        getAttackImage();
    }

    public void getImage() {
        up1 = setup("/enemies/orc/orc_up_1", gp.getTileSize(), gp.getTileSize());
        up2 = setup("/enemies/orc/orc_up_2", gp.getTileSize(), gp.getTileSize());
        down1 = setup("/enemies/orc/orc_down_1", gp.getTileSize(), gp.getTileSize());
        down2 = setup("/enemies/orc/orc_down_2", gp.getTileSize(), gp.getTileSize());
        left1 = setup("/enemies/orc/orc_left_1", gp.getTileSize(), gp.getTileSize());
        left2 = setup("/enemies/orc/orc_left_2", gp.getTileSize(), gp.getTileSize());
        right1 = setup("/enemies/orc/orc_right_1", gp.getTileSize(), gp.getTileSize());
        right2 = setup("/enemies/orc/orc_right_2", gp.getTileSize(), gp.getTileSize());
    }

    public void getAttackImage() {
        attackUp1 = setup("/enemies/orc/orc_attack_up_1", gp.getTileSize(), gp.getTileSize() * 2);
        attackUp2 = setup("/enemies/orc/orc_attack_up_2", gp.getTileSize(), gp.getTileSize() * 2);
        attackDown1 = setup("/enemies/orc/orc_attack_down_1", gp.getTileSize(), gp.getTileSize() * 2);
        attackDown2 = setup("/enemies/orc/orc_attack_down_2", gp.getTileSize(), gp.getTileSize() * 2);
        attackLeft1 = setup("/enemies/orc/orc_attack_left_1", gp.getTileSize() * 2, gp.getTileSize());
        attackLeft2 = setup("/enemies/orc/orc_attack_left_2", gp.getTileSize() * 2, gp.getTileSize());
        attackRight1 = setup("/enemies/orc/orc_attack_right_1", gp.getTileSize() * 2, gp.getTileSize());
        attackRight2 = setup("/enemies/orc/orc_attack_right_2", gp.getTileSize() * 2, gp.getTileSize());
    }


    public void damageReaction() {
        actionLockCounter = 0;
        //direction = gp.player.direction;
        onPath = true; // gets aggro
    }

    @Override
    public void update() {

        if (knockBack == true) {
            checkCollision();
            if (collisionOn == true) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            } else if (collisionOn == false) {
                switch (knockBackDirection) {
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
            knockBackCounter++;
            if (knockBackCounter == 10) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
        } else if (attacking == true) {
            attacking();
        } else {
            setAction();
            checkCollision();

            if (collisionOn == false) {
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
            if (spriteCounter > 24) {
                if (spriteNum == 1)                  //Every 12 frames sprite num changes.
                {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;                  // spriteCounter reset
            }
        }
        //Like player's invincible method
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
        if (offBalance == true) {
            offBalanceCounter++;
            if (offBalanceCounter > 60) {
                offBalance = false;
                offBalanceCounter = 0;
            }
        }
    }


}
