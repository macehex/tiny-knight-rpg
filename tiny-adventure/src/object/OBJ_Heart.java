package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Heart extends SuperObject{
    GamePanel gp;
    public OBJ_Heart(GamePanel gp){
        this.gp = gp;
        int heart_size = gp.tileSize/2 + gp.tileSize/3;
        int bar_width = heart_size*gp.player.maxLife+ gp.tileSize/3;
        int bar_height = gp.tileSize;
        name = "Heart";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/heart/heart_empty.png")));
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/heart/heart_full1.png")));
            image3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/heart/HealthBarPanel.png")));
            image = uTool.scaleImage(image,heart_size, heart_size);
            image2 = uTool.scaleImage(image2,heart_size, heart_size);
            image3 = uTool.scaleImage(image3,bar_width, bar_height);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
