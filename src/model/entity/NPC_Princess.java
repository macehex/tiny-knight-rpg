package model.entity;

import controller.GamePanel;

import java.awt.*;

public class NPC_Princess extends Entity {
    public NPC_Princess(GamePanel gp) {
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

    public void getNPCImage() {


        idle1 = setup("/npc/princess/idle1", gp.getTileSize() , gp.getTileSize() * 2);
        idle2 = setup("/npc/princess/idle2", gp.getTileSize() , gp.getTileSize() * 2);
        idle3 = setup("/npc/princess/idle3", gp.getTileSize() , gp.getTileSize() * 2);
        idle4 = setup("/npc/princess/idle4", gp.getTileSize() , gp.getTileSize() * 2);
        idle5 = idle1;
        idle6 = idle2;
    }


    public void setDialogue() {
        dialogues[0] = "Thank you young and brave warrior for rescuing me,\nI will have a word with my father";
        dialogues[1] = "to make you the honored\nroyal knight of our kingdom";
    }

    public void setAction() {
    }

    public void speak() {
        onPath = true;
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
    }
}
