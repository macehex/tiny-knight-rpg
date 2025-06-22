package model.entity;

import controller.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC extends Entity {
    public NPC(GamePanel gp) {
        super(gp);
        direction = "idle";
        speed = 0;
        getNPCImage();
        setDialogue();
        solidArea = new Rectangle();
        // Monster HIT BOX
        solidArea.x = 7;
        solidArea.y = 7;
        solidArea.width = gp.getTileSize()/2;
        solidArea.height = gp.getTileSize()/2;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

    private void getNPCImage() {

        up1 = setup("/npc/u1", gp.getTileSize() , gp.getTileSize() * 2);
        up2 = setup("/npc/u2", gp.getTileSize() , gp.getTileSize() * 2);
        up3 = setup("/npc/u3", gp.getTileSize() , gp.getTileSize() * 2);
        up4 = setup("/npc/u4", gp.getTileSize() , gp.getTileSize() * 2);
        up5 = up1;
        up6 = up2;

        down1 = setup("/npc/d1", gp.getTileSize() , gp.getTileSize() * 2);
        down2 = setup("/npc/d2", gp.getTileSize() , gp.getTileSize() * 2);
        down3 = setup("/npc/d3", gp.getTileSize() , gp.getTileSize() * 2);
        down4 = setup("/npc/d4", gp.getTileSize() , gp.getTileSize() * 2);
        down5 = down1;
        down6 = down2;

        left1 = setup("/npc/l1", gp.getTileSize() , gp.getTileSize() * 2);
        left2 = setup("/npc/l2", gp.getTileSize() , gp.getTileSize() * 2);
        left3 = setup("/npc/l3", gp.getTileSize() , gp.getTileSize() * 2);
        left4 = setup("/npc/l4", gp.getTileSize() , gp.getTileSize() * 2);
        left5=left1;
        left6 = left2;

        right1 = setup("/npc/r1", gp.getTileSize() , gp.getTileSize() * 2);
        right2 = setup("/npc/r2", gp.getTileSize() , gp.getTileSize() * 2);
        right3 = setup("/npc/r3", gp.getTileSize() , gp.getTileSize() * 2);
        right4 = setup("/npc/r4", gp.getTileSize() , gp.getTileSize() * 2);
        right5 = right1;
        right6 = right2;

        idle1 = setup("/npc/idle1", gp.getTileSize() , gp.getTileSize() * 2);
        idle2 = setup("/npc/idle2", gp.getTileSize() , gp.getTileSize() * 2);
        idle3 = setup("/npc/idle3", gp.getTileSize() , gp.getTileSize() * 2);
        idle4 = setup("/npc/idle4", gp.getTileSize() , gp.getTileSize() * 2);
        idle5 = idle1;
        idle6 = idle2;
    }


    private void setDialogue() {
        dialogues[0] = "Greeting young knight. \nI'm the king of this beautiful land";
        dialogues[1] = "and we welcome your arrival";

        dialogues[2] = "As you may have heard \nmy dearest daughter has been kidnapped and locked away";
        dialogues[3] = "We don't know where she is now\nso I ask of you to bring her back safe and sounds";
        dialogues[4] = "If you successfully do so,\nI will make sure to reward you handsomely";
    }

    public void setAction() {
        //set NPC behavior & AI
        if (onPath) {
            speed =1;
            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.getTileSize();
            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.getTileSize();

            searchPath(goalCol, goalRow);


        }
    }

    public void speak() {
        onPath = true;
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        switch (gp.player.direction) {
            case "up":
                direction = "up";
                break;
            case "down":
                direction = "down";
                break;
            case "right":
                direction = "right";
                break;
            case "left":
                direction = "left";
                break;
        }
//        direction = gp.player.direction;
    }
}
