package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Chest extends SuperObject{
        public OBJ_Chest(){
            name = "Chest";
            try{
                image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/chest/chest1.png")));
            }catch (IOException e){
                e.printStackTrace();
            }
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
