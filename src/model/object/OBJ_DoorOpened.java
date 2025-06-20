package model.object;
import controller.GamePanel;
import java.awt.image.BufferedImage;

public class OBJ_DoorOpened extends OBJ_Door {
    private final GamePanel gp;
    public OBJ_DoorOpened(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = "DoorOpened";
        collision = false;
        BufferedImage topLeft = loadTile("/object/door/open/top");
        BufferedImage topRight = loadTile("/object/door/open/top");
        BufferedImage midLeft = loadTile("/object/door/open/midleft");
        BufferedImage midRight = loadTile("/object/door/open/midright");
        BufferedImage bottomLeft = loadTile("/object/door/open/bottomleft");
        BufferedImage bottomRight = loadTile("/object/door/open/bottomright");

        image = composeDoor(topLeft, topRight, midLeft, midRight, bottomLeft, bottomRight, gp.tileSize);
        collision = false;
    }
}
