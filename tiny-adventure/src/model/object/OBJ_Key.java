package model.object;

import model.entity.Entity;
import controller.GamePanel;

public class OBJ_Key extends Entity {
    public OBJ_Key(GamePanel gp){
        super(gp);
        name = "Key";
        down1 = setup("/object/key");
        description = "[" + name + "]\nIt opens a door.";
        //  HIT BOX
        solidArea.x = 12;
        solidArea.y = 12;
        solidArea.width = 32;
        solidArea.height = 32;
        collision = false;

    }
}
