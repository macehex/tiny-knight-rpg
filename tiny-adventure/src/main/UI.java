package main;

import entity.Entity;
import object.OBJ_Heart;
import object.OBJ_Key;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class UI {
    // all the onscreen ui
    GamePanel gp;
    Graphics2D g2;
    int messageCounter = 0;
    Font Consolas_40, TimeNewsRoman_80B, TimeNewsRoman_25, Pixeloid_80,Pixeloid_40;
    BufferedImage keyImage;
    BufferedImage titleScreenImage, titleButtonImage;
    public boolean messageOn = false;
    public String message = "";

    public String currentDialogue = "";
    // title
    public int commandNum = 0;
    public boolean gameFinished = false ;
    UltilityTool utool = new UltilityTool();

    // gameState = playState UI
    BufferedImage heart_full, heart_half, heart_blank,heart_background, emblem;
    // gameState = optionsState
    int subState = 0 ;
    public UI(GamePanel gp) {
        this.gp = gp;
        Pixeloid_40 = new Font("Pixeloid Sans", Font.PLAIN, 40);

        Pixeloid_80 = new Font("Pixeloid Sans", Font.PLAIN, 80);
        Consolas_40 = new Font("Consolas", Font.PLAIN, 40);
        TimeNewsRoman_80B = new Font("Times New Roman", Font.BOLD, 40);
        TimeNewsRoman_25 = new Font("Times New Roman", Font.PLAIN, 25);

        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
        //CREATE 2D OBJECT
        Entity heart =new OBJ_Heart(gp);
        heart_blank = heart.image;
        heart_full = heart.image2;
        heart_background = heart.image3;
        emblem = heart.image4;
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
        System.out.println("subwindow drawn");
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
        switch(gp.gameState){
        //TITLE state
            case 0:
                drawTitleScreen(g2);
                break;
            case 1:
                    // ìf playState
                    // display playing ui
                    // set font
//                    draw key
//                    g2.setFont(Pixeloid_40);
//                    g2.setColor(Color.white);
//                    g2.drawImage(keyImage, gp.tileSize / 4, gp.tileSize / 4, gp.tileSize * 1, gp.tileSize * 1, null);
//                    g2.drawString("x " + gp.player.hasKey, 60, 45);
                    drawPlayerLife();
                    break;
            case 2:
                    //if pausing state
                    drawPlayerLife();
                    drawPauseScreen();
                    break;
                    //DIALOGUE state
            case 3:
                    //ìf dialogue state
                    drawPlayerLife();
                    drawDialogueScreen();
                    break;
            case 5:
                drawOptionsScreen();
            }

    }
    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2 - length /2;
        return x;
    }
    public void drawPlayerLife(){
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;
        //blank max heart
        g2.drawImage(emblem, x- gp.tileSize/4,y- gp.tileSize/4,null);
        g2.drawImage(heart_background,x+gp.tileSize+gp.tileSize/4,y,null);

        x = gp.tileSize*2+1;
        y = y+gp.tileSize/5;
        while( i < gp.player.life){
            g2.drawImage(heart_full,x , y,null);
            i++;
            x+= gp.tileSize+gp.tileSize/4;
        }

    }
    public void drawOptionsScreen(){
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(20F));

        // Create sub windows
        int frameX = gp.tileSize*6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*8;
        int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);
        switch (subState){
            case 0 -> optionTop(frameX,frameY);
//            case 1 ->
        }
        gp.keyH.enterPressed=false;
    }
    public void optionTop(int frameX, int frameY){
        int textX;
        int textY;
        int arrowOffset = gp.tileSize/2;
        // TITLE
        String text = "Options";

        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text,textX,textY);
        //FULLSCREEN ON?OFF
        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("FullScreen",textX,textY);
        if(commandNum==0){
            g2.drawString(">",textX-arrowOffset,textY);
            if(gp.keyH.enterPressed){
                if(!gp.fullScreenOn){
                    gp.fullScreenOn=true;
                    gp.setFullScreen();
                }
                else{
                    gp.fullScreenOn = false;
                    gp.setSmallScreen();
                }

            }
        }
        //MUSIC
        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Music",textX,textY);
        if(commandNum==1){
            g2.drawString(">",textX-arrowOffset,textY);
        }
        // SOUND
        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Sound Effects",textX,textY);
        if(commandNum==2){
            g2.drawString(">",textX-arrowOffset,textY);
        }
        // CONTROL
        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Control",textX,textY);
        if(commandNum==3){
            g2.drawString(">",textX-arrowOffset,textY);
        }
        //End game
        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("End Game", textX, textY);
        if(commandNum==4){
            g2.drawString(">",textX-arrowOffset,textY);
        }
        //Back
        textX = frameX + gp.tileSize;
        textY += gp.tileSize*2;
        g2.drawString("Back",textX,textY);
        if(commandNum==5){
            g2.drawString("> ",textX-arrowOffset,textY);
        }

        //FULLSCREEN checkbox
        textX = frameX + (int)(gp.tileSize*4.5);
        textY = frameY + gp.tileSize*2 - 20;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX,textY,20,20);
        if(gp.fullScreenOn){
            g2.fillRect(textX,textY,20,20);
        }
        //MUSIC
        textY += gp.tileSize;
        g2.drawRect(textX,textY,gp.tileSize*3,20);

        //Sound effect
        textY += gp.tileSize;
        g2.drawRect(textX,textY,gp.tileSize*3,20);

    }
    public void drawTitleScreen(Graphics2D g2){

        try{
            titleScreenImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title_screen/Start_Menu.png")));
            titleButtonImage =  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title_screen/title_button.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
        g2.setColor(Color.black);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));
        titleScreenImage = utool.scaleImage(titleScreenImage,gp.screenWidth, gp.screenHeight);
        g2.drawImage(titleScreenImage, 0, 0,null);

        int button_x = gp.screenWidth/2- gp.screenHeight/5+gp.screenWidth/(640);
        int button_y = gp.screenHeight/2;
        int space = gp.screenHeight/4 - gp.screenHeight/7;

        g2.drawImage(titleButtonImage, button_x, button_y,null);

        int textspace = gp.screenHeight/14;
        String text1 = "New Game";
        int x = getXforCenteredText(text1);
        int x_choose = x;
        int y=button_y + textspace;
        g2.drawString(text1,x,y);
        if(commandNum == 0){
            g2.drawString(">",x-gp.tileSize,y);
        }
        button_y+= space;

        g2.drawImage(titleButtonImage, button_x, button_y,null);

        String text2 = "Load Game";
        y = button_y + textspace;
        g2.drawString(text2,x,y);
        if(commandNum == 1){
            g2.drawString(">",x-gp.tileSize,y);
        }
        button_y+= space;

        g2.drawImage(titleButtonImage, button_x, button_y,null);


        String text3 = "Settings";
        x += textspace/3;
        y=button_y + textspace;
        g2.drawString(text3,x,y);
        if(commandNum == 2){
            g2.drawString(">",x_choose,y);
        }
        button_y+= space;

        g2.drawImage(titleButtonImage, button_x, button_y,null);
        String text4 = "Exit";
        x += textspace/2;
        y=button_y + textspace;
        if(commandNum == 3){
            g2.drawString(">",x_choose,y);
        }
        g2.drawString(text4,x,y);
    }
}
