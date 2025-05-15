package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;


public class OBJ_Potion_Heath_Two extends SuperObject {
    GamePanel gp;
    public OBJ_Potion_Heath_Two(GamePanel gp){
        this.gp = gp;
        name ="Health Potion 2";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/food/red_potion.png")));
            uTool.scaleImage(image,gp.tileSize, gp.tileSize);

        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
