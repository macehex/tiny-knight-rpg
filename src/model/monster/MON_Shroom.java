package model.monster;

import controller.GamePanel;
import model.object.OBJ_Gold;

import java.awt.*;

public class MON_Shroom extends MON {
    GamePanel gp;

    public MON_Shroom(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = 2; // DIFFERENT for each monster
        name = "shroom";
        speed = 1;
        maxLife = 3;
        life = maxLife;
        attack = 1;
        solidArea = new Rectangle();
        // Monster HIT BOX
        solidArea.x = 15;
        solidArea.y = 20;
        solidArea.width = 16;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }

    private void getImage() {
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
    private void getAngryImage(){
        up1 = setup("/enemies/shroom/angry/shroom1");
        up2 = setup("/enemies/shroom/angry/shroom2");
        up3 = setup("/enemies/shroom/angry/shroom3");
        up4 = setup("/enemies/shroom/angry/shroom4");
        up5 = setup("/enemies/shroom/angry/shroom5");
        up6 = setup("/enemies/shroom/angry/shroom6");

        down1 = setup("/enemies/shroom/angry/shroom1");
        down2 = setup("/enemies/shroom/angry/shroom2");
        down3 = setup("/enemies/shroom/angry/shroom3");
        down4 = setup("/enemies/shroom/angry/shroom4");
        down5 = setup("/enemies/shroom/angry/shroom5");
        down6 = setup("/enemies/shroom/angry/shroom6");

        left1 = setup("/enemies/shroom/angry/shroom1");
        left2 = setup("/enemies/shroom/angry/shroom2");
        left3 = setup("/enemies/shroom/angry/shroom3");
        left4 = setup("/enemies/shroom/angry/shroom4");
        left5 = setup("/enemies/shroom/angry/shroom5");
        left6 = setup("/enemies/shroom/angry/shroom6");

        right1 = setup("/enemies/shroom/angry/shroom1");
        right2 = setup("/enemies/shroom/angry/shroom2");
        right3 = setup("/enemies/shroom/angry/shroom3");
        right4 = setup("/enemies/shroom/angry/shroom4");
        right5 = setup("/enemies/shroom/angry/shroom5");
        right6 = setup("/enemies/shroom/angry/shroom6");
    }
// model.ai
    @Override
    public void damageReaction() {
        actionLockCounter = 0;
         speed =2;
        onPath = true;
        getAngryImage();
    }

}
