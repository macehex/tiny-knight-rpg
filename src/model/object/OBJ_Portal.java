package model.object;

import controller.GamePanel;
import view.AnimatedEntity;

public class OBJ_Portal extends AnimatedEntity {
    GamePanel gp = new GamePanel();
    public OBJ_Portal(GamePanel gp){
        super(gp);
        name = "Portal";
//        down1 = setup("/object/extra/tile_portal");
        imageIcon = setupAnimatedGif("/object/extra/greenportal",32,32);
        collision = false;
        this.gp = gp;
    }
}
