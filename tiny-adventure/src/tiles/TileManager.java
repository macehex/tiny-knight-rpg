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
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[20]; // types of tiles
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];  // the array thats the axiom of .txt array
        getTitleImage();
        loadMap("/maps/maptesttxt.txt"); //load your map here
    }

    public void getTitleImage() {

        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream(("/tiles/yellow_ground.png")));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream(("/tiles/water.png")));
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
    public void loadMap(String mapPath){
        try{
            InputStream is = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); //read the content of .txt files

            int col = 0;
            int row =0;

            while(col < gp.maxScreenCol && row < gp.maxScreenRow){
                String line = br.readLine();  // read a single line
                while(col < gp.maxScreenCol){
                    String numbers[] = line.split(" "); //numbers is the array of one line

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol){
                    col = 0; //reset collumn index
                    row++;
                }
            }
            br.close(); // close buffer reader
        }catch (Exception e){

        }
    }
    public void draw(Graphics2D g2){
        int col =0;
        int row = 0;
        int x = 0;
        int y = 0;
        while(col < gp.maxScreenCol && row< gp.maxScreenRow){
            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image,x ,y , gp.tileSize, gp.tileSize, null);
            col++;
            x+= gp.tileSize;
            if(col== gp.maxScreenCol){
                col = 0;
                x = 0 ;
                row++;
                y+= gp.tileSize;
            }
        }
    }
}
