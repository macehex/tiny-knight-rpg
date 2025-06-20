package view;

import model.entity.Entity;
import controller.GamePanel;

import java.awt.*;

public class AnimatedEntity extends Entity {
    private final GamePanel gp;

    public AnimatedEntity(GamePanel gp) {
        super(gp);
        this.gp = gp;
    }

    @Override
    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(imageIcon.getImage(), screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

}
