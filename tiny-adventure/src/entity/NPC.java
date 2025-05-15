package entity;

import main.GamePanel;
import main.UltilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class NPC extends Entity {
    public NPC(GamePanel gp) {
        super(gp);
        direction = "right";
        speed = 1;
        getGoblinImage();
        setDialogue();
    }

    public void getGoblinImage() {

        up1 = setup("/npc/Torch_Red_idle1");
        up2 = setup("/npc/Torch_Red_idle2");
        up3 = setup("/npc/Torch_Red_idle3");
        up4 = setup("/npc/Torch_Red_idle4");
        up5 = setup("/npc/Torch_Red_idle5");
        up6 = setup("/npc/Torch_Red_idle6");

        down1 = setup("/npc/Torch_Red_idle1");
        down2 = setup("/npc/Torch_Red_idle2");
        down3 = setup("/npc/Torch_Red_idle3");
        down4 = setup("/npc/Torch_Red_idle4");
        down5 = setup("/npc/Torch_Red_idle5");
        down6 = setup("/npc/Torch_Red_idle6");

        left1 = setup("/npc/Torch_Red_idle1");
        left2 = setup("/npc/Torch_Red_idle2");
        left3 = setup("/npc/Torch_Red_idle3");
        left4 = setup("/npc/Torch_Red_idle4");
        left5 = setup("/npc/Torch_Red_idle5");
        left6 = setup("/npc/Torch_Red_idle6");

        right1 = setup("/npc/Torch_Red_runr1");
        right2 = setup("/npc/Torch_Red_runr2");
        right3 = setup("/npc/Torch_Red_runr3");
        right4 = setup("/npc/Torch_Red_runr4");
        right5 = setup("/npc/Torch_Red_runr5");
        right6 = setup("/npc/Torch_Red_runr6");

        idle1 = setup("/npc/Torch_Red_idle1");
        idle2 = setup("/npc/Torch_Red_idle2");
        idle3 = setup("/npc/Torch_Red_idle3");
        idle4 = setup("/npc/Torch_Red_idle4");
        idle5 = setup("/npc/Torch_Red_idle5");
        idle6 = setup("/npc/Torch_Red_idle6");
    }
    public void setDialogue(){
        dialogues[0] = "Greeting young knight. \nI'm the king of this beautiful land";
        dialogues[1]="and we welcome your arrival";

//        dialogues[1] = "As you may have heard my dearest daughter has been kidnapped and locked away";
//        dialogues[2] = "We don't know where she is now so I ask of you to bring her back safe and sounds";
//        dialogues[3] = "If you successfully do so, I will make sure to reward you handsomely";
//        dialogues[4] = "Thank you young and brave warrior for rescuing me, I will have a word with my father";
//        dialogues[5] = "to make you the honored royal knight of our kingdom";
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
    @Override
    public BufferedImage setup(String imagePath) {
        UltilityTool uTool = new UltilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaleImage(image, gp.tileSize * 2, gp.tileSize * 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    // ENTITY already hava an update function!
    public void speak(){
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0 ;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        switch (gp.player.direction){
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
