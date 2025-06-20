package model.object;

import controller.GamePanel;
import view.AnimatedEntity;

public class OBJ_DoorOpened extends AnimatedEntity {
    private final GamePanel gp;
    public OBJ_DoorOpened(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = "DoorOpened";
        collision = false;
        imageIcon = setupAnimatedGif("/object/dooropen",48,32);
    }
}
