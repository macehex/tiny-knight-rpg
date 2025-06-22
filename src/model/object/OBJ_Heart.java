package model.object;

import model.entity.Entity;
import controller.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Heart extends Entity {
    public OBJ_Heart(GamePanel gp){
        super(gp);
        int bar_height = gp.getTileSize()/2 + gp.getTileSize()/3;
        int bar_width = gp.player.maxLife* gp.getTileSize()+gp.getTileSize()*2;
        int heart_sizeX = bar_width/gp.player.maxLife+4;
        int heart_sizeY = gp.getTileSize()/3+4;
        name = "Heart";
        image = setup("/player/heart/heart_empty", heart_sizeX, heart_sizeY);
//        image2 = setup("/player/heart/heart_paneof_five", heart_sizeX, heart_sizeY);
        try {
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/heart/heart_paneof_five.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        image3 = setup("/player/heart/bar", bar_width, bar_height);
        image4 =setup("/player/heart/EMBLEM", gp.getTileSize(), gp.getTileSize());
    }

}
