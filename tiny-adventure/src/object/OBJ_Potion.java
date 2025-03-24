package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Potion extends SuperObject {
    public OBJ_Potion(){
        name ="Potion";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/food/potion.png")));
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
