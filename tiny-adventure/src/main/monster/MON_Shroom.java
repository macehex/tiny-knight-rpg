package main.monster;

import entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class MON_Shroom extends Entity {
    public MON_Shroom(GamePanel gp){

        super(gp);
        type = 2; // DIFFERENT for each monster
        name = "shroom";
        speed = 1;
        maxLife = 3;
        life = maxLife;
        solidArea = new Rectangle();
        // PLAYER HIT BOX
        solidArea.x = 15;
        solidArea.y = 20;
        solidArea.width = 16;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage() {

        up1 = setup("/enemies/shroom/idle/shroom1");
        up2 = setup("/enemies/shroom/idle/shroom2");
        up3 = setup("/enemies/shroom/idle/shroom3");
        up4 = setup("/enemies/shroom/idle/shroom4");
        up5 = setup("/enemies/shroom/idle/shroom5");
        up6 = setup("/enemies/shroom/idle/shroom6");

        down1 = setup("/enemies/shroom/idle/shroom1");
        down2 = setup("/enemies/shroom/idle/shroom2");
        down3 = setup("/enemies/shroom/idle/shroom3");
        down4 = setup("/enemies/shroom/idle/shroom4");
        down5 = setup("/enemies/shroom/idle/shroom5");
        down6 = setup("/enemies/shroom/idle/shroom6");

        left1 = setup("/enemies/shroom/idle/shroom1");
        left2 = setup("/enemies/shroom/idle/shroom2");
        left3 = setup("/enemies/shroom/idle/shroom3");
        left4 = setup("/enemies/shroom/idle/shroom4");
        left5 = setup("/enemies/shroom/idle/shroom5");
        left6 = setup("/enemies/shroom/idle/shroom6");

        right1 = setup("/enemies/shroom/idle/shroom1");
        right2 = setup("/enemies/shroom/idle/shroom2");
        right3 = setup("/enemies/shroom/idle/shroom3");
        right4 = setup("/enemies/shroom/idle/shroom4");
        right5 = setup("/enemies/shroom/idle/shroom5");
        right6 = setup("/enemies/shroom/idle/shroom6");

        idle1 = setup("/enemies/shroom/idle/shroom1");
        idle2 = setup("/enemies/shroom/idle/shroom2");
        idle3 = setup("/enemies/shroom/idle/shroom3");
        idle4 = setup("/enemies/shroom/idle/shroom4");
        idle5 = setup("/enemies/shroom/idle/shroom5");
        idle6 = setup("/enemies/shroom/idle/shroom6");
    }
    public void setAction() {
        //set NPC behavior & AI
        actionLockCounter ++; //increment everytime setAction is called
        if(actionLockCounter == 120){
            //  the action direction stay the same for 120 seconds
            //randomize npc state
            Random random = new Random();
            int i = random.nextInt(100) + 1; // pick up num in range[1,100]
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75) {
                direction = "right";
            }
            actionLockCounter = 0; // reset counter

        }

    }

}
