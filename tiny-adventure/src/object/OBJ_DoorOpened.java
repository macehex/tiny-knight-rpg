package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class OBJ_DoorOpened extends Entity {
    private final GamePanel gp;
    public OBJ_DoorOpened(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = "DoorOpened";
        collision = false;
        imageIcon = setupAnimatedGif("/object/dooropen",48,32);
    }
    @Override
    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(imageIcon.getImage(), screenX, screenY, gp.tileSize*3, gp.tileSize*2, null);
        }
    }

}
