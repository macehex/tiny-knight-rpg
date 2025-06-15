package object;

import entity.Entity;
import main.GamePanel;

import java.awt.*;


public class OBJ_Spikes extends Entity{
    private final GamePanel gp;
    public OBJ_Spikes(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = "Spikes";
        type = 5; // spikes

        imageIcon = setupAnimatedGif("/object/extra/greenportal",32,32);

        solidArea = new Rectangle();
        // Spikes HIT BOX
        solidArea.x = 8;
        solidArea.y = 8;
        solidArea.width = gp.tileSize-solidArea.x;
        solidArea.height = gp.tileSize-solidArea.y;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

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
