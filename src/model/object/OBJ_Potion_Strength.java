package model.object;

import controller.GamePanel;
import view.AnimatedEntity;

public class OBJ_Potion_Strength extends AnimatedEntity {
    public OBJ_Potion_Strength(GamePanel gp) {
        super(gp);
        name = "Strength Potion";
        imageIcon = setupAnimatedGif("/object/consumable/blue_potion", gp.getTileSize(), gp.getTileSize());
        collision = false;
    }
}
