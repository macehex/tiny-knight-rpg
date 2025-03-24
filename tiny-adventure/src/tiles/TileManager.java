package tiles;

import java.io.BufferedReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

import java.awt.*;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[20]; // types of tiles
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];  // the array thats the axiom of .txt array
        getTitleImage();
        loadMap("/maps/maptesttxt.txt"); //load your map here
    }

    public void getTitleImage() {

        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(("/tiles/yellow_ground.png"))));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(("/tiles/water.png"))));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(("/tiles/yellow_ground.png"))));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(("/tiles/yellow_ground.png"))));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(("/tiles/water.png"))));
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(("/tiles/yellow_ground.png"))));
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

            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            //draw tiles
            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
