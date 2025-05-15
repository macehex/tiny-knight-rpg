package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Heart extends SuperObject{
    GamePanel gp;
    public OBJ_Heart(GamePanel gp){
        this.gp = gp;
        int bar_height = gp.tileSize/2 + gp.tileSize/3;
        int bar_width = gp.player.maxLife* gp.tileSize+gp.tileSize*2;
        int heart_sizeX = bar_width/gp.player.maxLife+4;
        int heart_sizeY = gp.tileSize/3+4;
        name = "Heart";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/heart/heart_empty.png")));
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/heart/heart_paneof_five.png")));
            image3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/heart/bar.png")));
            image4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/heart/EMBLEM.png")));
            image = uTool.scaleImage(image,heart_sizeX, heart_sizeY*2);
            image2 = uTool.scaleImage(image2,heart_sizeX, heart_sizeY);
            image3 = uTool.scaleImage(image3,bar_width, bar_height);
            image4 = uTool.scaleImage(image4,gp.tileSize+gp.tileSize/3, gp.tileSize+gp.tileSize/3);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
