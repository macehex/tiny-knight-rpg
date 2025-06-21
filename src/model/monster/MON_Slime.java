package model.monster;

import controller.GamePanel;

import java.awt.*;

public class MON_Slime extends MON{

    GamePanel gp;

    public MON_Slime(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = 2; // DIFFERENT for each monster
        name = "slime";
        speed = 1;
        maxLife = 2;
        attack =  1;
        life = maxLife;
        solidArea = new Rectangle();
        // Monster HIT BOX
        solidArea.x = 5;
        solidArea.y = 5;
        solidArea.width = 30;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }

    private void getImage() {

        left1 = setup("/enemies/slime/l1");
        left2 = setup("/enemies/slime/l2");
        left3 = setup("/enemies/slime/l3");
        left4 = setup("/enemies/slime/l4");
        left5 = setup("/enemies/slime/l5");
        left6 = setup("/enemies/slime/l6");

        right1 = setup("/enemies/slime/r1");
        right2 = setup("/enemies/slime/r2");
        right3 = setup("/enemies/slime/r3");
        right4 = setup("/enemies/slime/r4");
        right5 = setup("/enemies/slime/r5");
        right6 = setup("/enemies/slime/r6");

        idle1 = setup("/enemies/slime/idle1");
        idle2 = setup("/enemies/slime/idle2");
        idle3 = setup("/enemies/slime/idle3");
        idle4 = setup("/enemies/slime/idle4");
        idle5 = setup("/enemies/slime/idle5");
        idle6 = setup("/enemies/slime/idle6");
        up1 = setup("/enemies/slime/u1");
        up2 = setup("/enemies/slime/u2");
        up3 = setup("/enemies/slime/u3");
        up4 = setup("/enemies/slime/u4");
        up5 = setup("/enemies/slime/u5");
        up6 = setup("/enemies/slime/u6");

        down1 = idle1;
        down2 = idle2;
        down3 = idle3;
        down4 = idle4;
        down5 = idle5;
        down6 = idle6;

    }

}

