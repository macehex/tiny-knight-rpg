package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Heart extends Entity {
    public OBJ_Heart(GamePanel gp){
        super(gp);
        int bar_height = gp.tileSize/2 + gp.tileSize/3;
        int bar_width = gp.player.maxLife* gp.tileSize+gp.tileSize*2;
        int heart_sizeX = bar_width/gp.player.maxLife+4;
        int heart_sizeY = gp.tileSize/3+4;
        name = "Heart";
        image = setup("/player/heart/heart_empty");
        image2 = setup("/player/heart/heart_paneof_five");
        image3 = setup("/player/heart/bar");
        image4 =setup("/player/heart/EMBLEM");
    }

}
