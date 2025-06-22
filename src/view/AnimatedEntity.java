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

        if (worldX + gp.getTileSize() > gp.player.worldX - gp.player.screenX &&
                worldX - gp.getTileSize() < gp.player.worldX + gp.player.screenX &&
                worldY + gp.getTileSize() > gp.player.worldY - gp.player.screenY &&
                worldY - gp.getTileSize() < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(imageIcon.getImage(), screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
        }
    }

}
