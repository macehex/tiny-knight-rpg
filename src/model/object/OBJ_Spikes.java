package model.object;

import controller.GamePanel;
import view.AnimatedEntity;

import java.awt.*;


public class OBJ_Spikes extends AnimatedEntity {
    private final GamePanel gp;
    public OBJ_Spikes(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = "Spikes";
        type = 5; // spikes

        imageIcon = setupAnimatedGif("/object/spike/spike",32,64);

        solidArea = new Rectangle();
        // Spikes HIT BOX
        solidArea.x = 8;
        solidArea.y = 8;
        solidArea.width = gp.tileSize-solidArea.x;
        solidArea.height = gp.tileSize-solidArea.y;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }
}
