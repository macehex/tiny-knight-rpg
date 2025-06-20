package model.object;

import controller.GamePanel;
import model.entity.Entity;

public class OBJ_LadderInWorld extends Entity {
    public OBJ_LadderInWorld(GamePanel gp){
        super(gp);
        name = "LadderInWorld";
        down1 = setup("/object/ladderInWorld");
        collision = false;
    }
}
