package entity;
import main.GamePanel;



public class King extends Entity{
        public King(GamePanel gp) {
            super(gp);
            direction = "idle";
            speed = 1;
            getKingImage();
        }
        public void getKingImage() {

            idle1 = setup("/king/King_idle1", gp.tileSize, gp.tileSize);
            idle2 = setup("/king/King_idle2", gp.tileSize, gp.tileSize);
            idle3 = setup("/king/King_idle3", gp.tileSize, gp.tileSize);
            idle4 = setup("/king/King_idle1", gp.tileSize, gp.tileSize);
            idle5 = setup("/king/King_idle2", gp.tileSize, gp.tileSize);
            idle6 = setup("/king/King_idle3", gp.tileSize, gp.tileSize);

        }


        public void setAction() {
            direction = "idle";
        }

}
