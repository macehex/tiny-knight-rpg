package object;

import main.GamePanel;
import main.UltilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false ;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48); //all objects have the same hitbox
    public int solidAreaDefaultX =0;
    public int solidAreaDefaultY =0;
    // animation
    public int spriteCounter = 0;
    public int spriteNum = 1;
    UltilityTool uTool = new UltilityTool();


    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        // dont draw all of the tiles
        if(worldX +gp.tileSize >gp.player.worldX - gp.player.screenX&&
                worldX - gp.tileSize< gp.player.worldX + gp.player.screenX&&
                worldY + gp.tileSize> gp.player.worldY - gp.player.screenY&&
                worldY -  gp.tileSize < gp.player.worldY + gp.player.screenY)
        {


            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
