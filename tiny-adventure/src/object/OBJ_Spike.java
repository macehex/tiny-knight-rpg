package object;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Spike extends SuperObject{
    GamePanel gp;
    public OBJ_Spike(GamePanel gp){
        this.gp = gp;
        name = "Spike";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/spike/.png")));
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/spike/.png")));
            image3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/spike/.png")));
            image4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/spike/.png")));
            image5 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/spike/.png")));
            image6 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/spike/.png")));


            uTool.scaleImage(image,gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        collision = false;

    }
    //        image = switch (spriteNum) {
//            case 1 -> chest1;
//            case 2 -> chest2;
//            case 3 -> chest3;
//            case 4 -> chest4;
//            case 5 -> chest5;
//            case 6 -> chest6;
//            default -> image;
//        };


}
