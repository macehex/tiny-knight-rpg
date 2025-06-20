package model.object;

import model.entity.Entity;
import controller.GamePanel;

public class OBJ_SWORD extends Entity {
    public OBJ_SWORD(GamePanel gp){
        super(gp);
        name ="Sword";
        down1 = setup("/object/sword");
        attackValue = 1 ;
        description = "[" + name + "]\nAn old sword.";
        collision = false;

    }
}
