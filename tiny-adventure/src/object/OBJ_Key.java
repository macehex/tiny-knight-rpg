package object;

import entity.Entity;
import main.GamePanel;
import main.UltilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Key extends Entity {
    public OBJ_Key(GamePanel gp){
        super(gp);
        name = "Key";
        down1 = setup("/object/key");
        //  HIT BOX
        solidArea.x = 12;
        solidArea.y = 12;
        solidArea.width = 32;
        solidArea.height = 32;
        collision = false;

    }
}
