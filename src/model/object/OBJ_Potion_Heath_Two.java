package model.object;
import controller.GamePanel;
import view.AnimatedEntity;


public class OBJ_Potion_Heath_Two extends AnimatedEntity {
    private final GamePanel gp;
    public OBJ_Potion_Heath_Two(GamePanel gp){
        super(gp);
        this.gp = gp;
        name ="Health Potion 2";
        imageIcon = setupAnimatedGif("/object/consumable/red_potion", gp.getTileSize(), gp.getTileSize());
        collision = false;
    }
}
