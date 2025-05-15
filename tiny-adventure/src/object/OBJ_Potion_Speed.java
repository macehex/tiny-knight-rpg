package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Potion_Speed extends SuperObject {
    GamePanel gp;
    public OBJ_Potion_Speed(GamePanel gp){
        this.gp = gp;
        name ="Speed Potion";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/food/green_potion.png")));
            image = uTool.scaleImage(image, gp.tileSize/4, gp.tileSize/4);

        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
