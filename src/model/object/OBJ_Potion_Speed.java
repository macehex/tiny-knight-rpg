package model.object;
import controller.GamePanel;
import view.AnimatedEntity;


public class OBJ_Potion_Speed extends AnimatedEntity {
    private final GamePanel gp;
    public OBJ_Potion_Speed(GamePanel gp){
        super(gp);
        this.gp = gp;
        name ="Health Potion 2";
        imageIcon = setupAnimatedGif("/object/consumable/green_potion", gp.tileSize, gp.tileSize);
        collision = false;
    }
}
