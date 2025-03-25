package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_DoorOpened extends SuperObject {
    GamePanel gp;
    public OBJ_DoorOpened(GamePanel gp){
        this.gp = gp;
        name = "DoorOpened";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/door/door.png")));
            uTool.scaleImage(image,gp.tileSize, gp.tileSize);

        }catch (IOException e){
            e.printStackTrace();
        }
        collision = true;

    }

}
