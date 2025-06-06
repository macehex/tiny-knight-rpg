package tiles;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UI;
import main.UltilityTool;

import java.awt.*;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[20]; // types of tiles
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];  // the array thats the axiom of .txt array
        getTitleImage();
        loadMap("/maps/maptesttxt.txt"); //load your map here
    }

    public void getTitleImage() {
        // name here
        setup(0, "yellow_ground", false);
        setup(1, "water", true);
        setup(2, "water", true);
        setup(3, "yellow_ground", false);
        setup(4, "Outdoors_22", false);
        setup(5, "yellow_ground", false);


//            tile[1] = new Tile();
//            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(("/tiles/water.png"))));
//            tile[1].collision =true;
//            tile[2] = new Tile();
//            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(("/tiles/water.png"))));



    }

    public void setup(int index, String imageName, boolean collision) {
        UltilityTool uTool = new UltilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(("/tiles/" + imageName + ".png"))));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String mapPath) {
        try {
            InputStream is = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); //read the content of .txt files

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();  // read a single line
                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" "); //numbers is the array of one line

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0; //reset collumn index
                    row++;
                }
            }
            br.close(); // close buffer reader
        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            // dont draw all of the tiles
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY,null);
            }
            //draw tiles
            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
