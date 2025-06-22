package model.object;
import controller.GamePanel;
import view.AnimatedEntity;


public class OBJ_Ladder extends AnimatedEntity {
    private final GamePanel gp;
    public OBJ_Ladder(GamePanel gp){
        super(gp);
        this.gp = gp;
        name ="Ladder";
        down1 = setup("/object/ladder");
        imageIcon = setupAnimatedGif("/object/ladder", gp.getTileSize(), gp.getTileSize());
        collision = false;
        description = "[" + name + "]\nUse this ladder\nto get over big gaps.";

    }
}
