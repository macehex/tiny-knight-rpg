package object;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class OBJ_Door extends Entity {
    public OBJ_Door(GamePanel gp){
        super(gp);
            name = "Door";
            down1 = setup("/object/door/door");
            collision = true;
            solidArea = new Rectangle();
            //  HIT BOX
            solidArea.x = 0;
            solidArea.y = 0;
            solidArea.width = gp.tileSize;
            solidArea.height = gp.tileSize;

    }

}
