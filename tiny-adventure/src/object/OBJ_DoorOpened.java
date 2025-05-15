package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_DoorOpened extends Entity {
    public OBJ_DoorOpened(GamePanel gp){
        super(gp);
        name = "DoorOpened";
        down1 = setup("/object/door/door");
        collision = false;
        //  HIT BOX
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = gp.tileSize;
        solidArea.height = gp.tileSize;

    }

}
