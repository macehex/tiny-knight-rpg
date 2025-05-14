package main;

import object.OBJ_Chest;
import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    // all the onscreen ui
    GamePanel gp;
    Graphics2D g2;
    int messageCounter = 0;
    Font Consolas_40, TimeNewsRoman_80B, TimeNewsRoman_25, Pixeloid_80,Pixeloid_40;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";

    public String currentDialogue = "";
    public boolean gameFinished = false ;


    public UI(GamePanel gp) {
        this.gp = gp;
        Pixeloid_40 = new Font("Pixeloid Sans", Font.PLAIN, 40);

        Pixeloid_80 = new Font("Pixeloid Sans", Font.PLAIN, 80);
        Consolas_40 = new Font("Consolas", Font.PLAIN, 40);
        TimeNewsRoman_80B = new Font("Times New Roman", Font.BOLD, 40);
        TimeNewsRoman_25 = new Font("Times New Roman", Font.PLAIN, 25);

        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
    }
    public void drawPauseScreen(){

        String text = "PAUSED";
        int x = getXforCenteredText(text), y = gp.screenHeight/2;
        g2.drawString(text,x, y);
    }
    public void drawDialogueScreen(){
        //Window
        int x= gp.tileSize*2;
        int  y=gp.tileSize*6;
        int width= gp.screenWidth-(gp.tileSize*4);
        int height=gp.tileSize*5;
        drawSubWindow(x, y,  width, height);

        //draw texts
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,22F));
        x+=gp.tileSize;
        y+= gp.tileSize;
        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y+= 40;
        }
    }
    public void drawSubWindow(int x, int y, int width,int height)
    {
        Color c = new Color(87,72,82,240);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,35);
        c = new Color(185,145,88);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
    }
    // notification
    public void showMessage(String text){
        message = text ;
        messageOn = true;
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(Pixeloid_80);
        g2.setColor(Color.white);
        // PLAY state
        switch(gp.gameState){
            case 1:
                // ìf playState
                // display playing ui
                // set font
                g2.setFont(Pixeloid_40);
                g2.setColor(Color.white);
                g2.drawImage(keyImage, gp.tileSize / 4, gp.tileSize / 4, gp.tileSize * 1, gp.tileSize * 1, null);
                g2.drawString("x " + gp.player.hasKey, 60, 45);
            break;
            //PAUSE state
            case 2:
                //if pausing state
                drawPauseScreen();
                break;
            //DIALOGUE state
            case 3:
                //ìf dialogue state
                drawDialogueScreen();
                break;
        }
    }
    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2 - length /2;
        return x;
    }
}
