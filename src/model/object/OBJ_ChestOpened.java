package model.object;
import controller.GamePanel;
import view.AnimatedEntity;


public class OBJ_ChestOpened extends AnimatedEntity {
    private final GamePanel gp;
    public OBJ_ChestOpened(GamePanel gp){
        super(gp);
        this.gp = gp;
        name ="chestOpened";
        imageIcon = setupAnimatedGif("/object/chest/chest_32", gp.tileSize, gp.tileSize);
        collision = true;
    }
}
