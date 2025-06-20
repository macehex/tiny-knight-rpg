package model.object;

import model.entity.Entity;
import controller.GamePanel;
import java.util.Random;

public class OBJ_Chest extends Entity {
        private final GamePanel gp;

        public OBJ_Chest(GamePanel gp){
            super(gp);
            this.gp= gp;
            name = "Chest";
            down1 = setup("/object/chest/chest1");
            collision = false;

        }
    public void randomReward() {
        Random rand = new Random();
        int reward = rand.nextInt(4); // 0 to 3
        switch (reward) {
            case 0 ->{
                if(gp.player.life < gp.player.maxLife){
                    gp.player.life++;
                }
            }
            case 1 -> {
                if(gp.player.life < gp.player.maxLife-1){
                gp.player.life+=2;
                }
            }
            case 2 -> gp.player.life--;
            case 3 -> {
                gp.player.maxLife++;
            }
        }
    }
}
