package model.object;
import controller.GamePanel;
import view.AnimatedEntity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_DoorOpened extends AnimatedEntity {
    private final GamePanel gp;
    public OBJ_DoorOpened(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = "DoorOpened";
        collision = false;
        imageIcon = setupAnimatedGif("/object/door/dooropen", gp.getTileSize(), gp.getTileSize());

    }
    @Override
    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.getTileSize() > gp.player.worldX - gp.player.screenX &&
                worldX - gp.getTileSize() < gp.player.worldX + gp.player.screenX &&
                worldY + gp.getTileSize() > gp.player.worldY - gp.player.screenY &&
                worldY - gp.getTileSize() < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(imageIcon.getImage(), screenX, screenY, gp.getTileSize()*2, gp.getTileSize()*3, null);
        }
    }

}
