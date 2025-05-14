package entity;
import main.GamePanel;
import main.UltilityTool;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class King extends Entity{
        public King(GamePanel gp) {
            super(gp);
            direction = "idle";
            speed = 1;
            getKingImage();
        }
        public void getKingImage() {

            idle1 = setup("/king/King_idle1");
            idle2 = setup("/king/King_idle2");
            idle3 = setup("/king/King_idle3");
            idle4 = setup("/king/King_idle1");
            idle5 = setup("/king/King_idle2");
            idle6 = setup("/king/King_idle3");

        }


        public void setAction() {
            direction = "idle";
        }

}
