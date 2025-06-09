package object;

import entity.Entity;
import main.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_Portal extends Entity {
    GamePanel gp = new GamePanel();
    public OBJ_Portal(GamePanel gp){
        super(gp);
        name = "Portal";
//        down1 = setup("/object/extra/tile_portal");
        imageIcon = setupAnimatedGif("/object/extra/greenportal",32,32);
        collision = false;
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
