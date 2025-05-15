package object;

import entity.Entity;
import main.GamePanel;


public class OBJ_Potion_Speed extends Entity {
    public OBJ_Potion_Speed(GamePanel gp){
        super(gp);
        name ="Speed Potion";
        image = setup("/object/consumable/green_potion");
        collision = false;

    }
}
