package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Door extends Entity {
    private final GamePanel gp;
    public OBJ_Door(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = "Door";
        BufferedImage topLeft = loadTile("/object/door/top");
        BufferedImage topRight = loadTile("/object/door/top");
        BufferedImage midLeft = loadTile("/object/door/midleft");
        BufferedImage midRight = loadTile("/object/door/midright");
        BufferedImage bottomLeft = loadTile("/object/door/bottomleft");
        BufferedImage bottomRight = loadTile("/object/door/bottomright");

        image = composeDoor(topLeft, topRight, midLeft, midRight, bottomLeft, bottomRight, gp.tileSize);
        collision = true;
        solidArea = new Rectangle(0, 0, gp.tileSize * 2, gp.tileSize * 3);
    }

    private BufferedImage loadTile(String path) {
        try {
            return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + ".png")));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private BufferedImage composeDoor(
            BufferedImage tl, BufferedImage tr,
            BufferedImage ml, BufferedImage mr,
            BufferedImage bl, BufferedImage br,
            int tileSize) {
        BufferedImage door = new BufferedImage(tileSize * 2, tileSize * 3, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = door.createGraphics();

        int y =-15; // Adjust y position to align the door correctly

        g2.drawImage(tl, 0, y, tileSize, tileSize, null);
        g2.drawImage(tr, tileSize, y, tileSize, tileSize, null);
        g2.drawImage(ml, 0, tileSize+y, tileSize, tileSize, null);
        g2.drawImage(mr, tileSize, tileSize+y, tileSize, tileSize, null);
        g2.drawImage(bl, 0, tileSize * 2+y, tileSize, tileSize, null);
        g2.drawImage(br, tileSize, tileSize * 2+y, tileSize, tileSize, null);
        g2.dispose();
        return door;
    }

    @Override
    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if (worldX + gp.tileSize * 2 > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize * 2 < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize * 3 > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize * 3 < gp.player.worldY + gp.player.screenY) {
            g2.drawImage(image, screenX, screenY, gp.tileSize * 2, gp.tileSize * 3, null);
        }
    }
}