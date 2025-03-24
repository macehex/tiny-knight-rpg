package main;

import object.OBJ_Chest;
import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    // all the onscreen ui
    GamePanel gp;
    Font Consolas_40;
    BufferedImage keyImage;
    public UI(GamePanel gp) {
        this.gp = gp;
        Consolas_40 = new Font("Consolas", Font.PLAIN, 40);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }
    public void draw(Graphics2D g2){
        // set font
        g2.setFont(Consolas_40);
        g2.setColor(Color.white);
        g2.drawImage(keyImage,gp.tileSize/4,gp.tileSize/4,gp.tileSize*2,gp.tileSize*2,null);
        g2.drawString("x "+ gp.player.hasKey,100,70);

    }
}
