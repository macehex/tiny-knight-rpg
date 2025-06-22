package model.object;

import controller.GamePanel;
import view.AnimatedEntity;

import java.awt.*;

public class OBJ_Gold extends AnimatedEntity {
    GamePanel gp;
    public OBJ_Gold(GamePanel gp) {
        super(gp);
        this.gp=  gp;
        name = "Gold";
        imageIcon = setupAnimatedGif("/object/coin/coin", gp.getTileSize(), gp.getTileSize());
        collision = false; // Gold does not have collision
    }
    @Override
    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.getTileSize() > gp.player.worldX - gp.player.screenX &&
                worldX - gp.getTileSize() < gp.player.worldX + gp.player.screenX &&
                worldY + gp.getTileSize() > gp.player.worldY - gp.player.screenY &&
                worldY - gp.getTileSize() < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(imageIcon.getImage(), screenX, screenY, gp.getTileSize()/2, gp.getTileSize()/2, null);
        }
    }

}
