package main;

import object.OBJ_Chest;
import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    // all the onscreen ui
    GamePanel gp;
    int messageCounter = 0;
    Font Consolas_40, TimeNewsRoman_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";

    public boolean gameFinished = false ;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    // play time
    double playTime;

    public UI(GamePanel gp) {
        this.gp = gp;
        Consolas_40 = new Font("Consolas", Font.PLAIN, 40);
        TimeNewsRoman_80B = new Font("Times New Roman", Font.BOLD, 40);
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
    }
    // notification
    public void showMessage(String text){
        message = text ;
        messageOn = true;
    }
    public void draw(Graphics2D g2){
        if(gameFinished){

            g2.setFont(Consolas_40);
            g2.setColor(Color.white);
            int textLength;
            int x;
            int y;

            String text = "Treasure found" ;
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*3) ;
            g2.drawString(text, x, y);
            // second message
            g2.setFont(TimeNewsRoman_80B);
            g2.setColor(Color.BLUE);
            text = "Congratulations!" ;
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*2) ;
            g2.drawString(text, x, y);

            // stop game
            gp.gameThread = null;
        }else {
            // set font
            g2.setFont(Consolas_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize / 4, gp.tileSize / 4, gp.tileSize * 2, gp.tileSize * 2, null);
            g2.drawString("x " + gp.player.hasKey, 100, 70);

//            // Show time
            playTime += (double)1/60; // draw method get called 60/sec
            g2.drawString("Time: "+dFormat.format(playTime), gp.tileSize*11, 65);
            //showMessage
            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(23F));
                // g2.drawImage(...) to draw the UI in the background
                g2.drawString(message, gp.tileSize * 6 - 10, gp.tileSize * 11);
                messageCounter++;
                if (messageCounter > 60 * 1) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
