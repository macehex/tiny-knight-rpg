package object;

import entity.Entity;
import main.GamePanel;


public class OBJ_Potion_Heath_Two extends Entity {
    public OBJ_Potion_Heath_Two(GamePanel gp){
        super(gp);
        name ="Health Potion 2";
        image = setup("/object/consumable/red_potion");
        collision = false;

    }
}
