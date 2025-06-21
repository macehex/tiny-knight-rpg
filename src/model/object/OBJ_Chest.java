package model.object;

import model.entity.Entity;
import controller.GamePanel;
import java.util.Random;

public class OBJ_Chest extends Entity {
    private final GamePanel gp;

    public OBJ_Chest(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "Chest";
        down1 = setup("/object/chest/chest");
        collision = true;
    }


    public void randomReward() {
        Random rand = new Random();
        int reward = rand.nextInt(4); // 0 to 3
        switch (reward) {
            case 0 ->{
                if(gp.player.life < gp.player.maxLife){
                    gp.player.life++;
                    gp.ui.addMessage("Life increased!");
                }else{
                    gp.ui.addMessage("You gained 20 golds!");
                    gp.player.gold += 20;
                }
            }
            case 1 -> {
                if(gp.player.life < gp.player.maxLife-1){
                gp.player.life+=2;
                    gp.ui.addMessage("Life increased by 2!");

                }else{
                    gp.ui.addMessage("You gained 30 golds!");
                    gp.player.gold += 30;
                }
            }
            case 2 -> {
                if(gp.player.life>1){
                    gp.player.life-=1;
                    gp.ui.addMessage("Life decreased!");
                }else{
                    gp.ui.addMessage("You gained 40 golds!");
                    gp.player.gold += 40;
                }

            }
            case 3 -> {
                gp.player.maxLife++;
                gp.player.life = gp.player.maxLife;
                gp.ui.addMessage("Increased max life!");

            }
        }
    }
}
