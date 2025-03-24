package tiles;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

import java.awt.*;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    Tile[] tile;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[20]; // types of tiles
        getTitleImage();
    }

    public void getTitleImage() {

        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream(("/tiles/yellow_ground.png")));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream(("/tiles/yellow_ground.png")));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream(("/tiles/yellow_ground.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void draw(Graphics2D g2) {
//        g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image, 96, 0, gp.tileSize, gp.tileSize, null);
//
//    }
    public void draw(Graphics2D g2){
        int col =0;
        int row = 0;
        int x = 0;
        int y = 0;
        while(col < gp.maxScreenCol && row< gp.maxScreenRow){
            g2.drawImage(tile[0].image, x, y, gp.tileSize, gp.tileSize,null);
            col++;
            x += gp.tileSize;
            if(col == gp.maxScreenCol){
                col = 0 ;
                x = 0;
                row++;
                y+= gp.tileSize;
            }
        }
    }
}
