package view;

import controller.GamePanel;
import model.entity.Entity;
import model.object.OBJ_Gold;
import model.object.OBJ_Heart;
import model.object.OBJ_Key;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.ArrayList;
public class UI {
    // all the onscreen ui
    BufferedImage inventoryFrame;
    GamePanel gp;
    Graphics2D g2;
    int messageCounter = 0;
    Font Consolas_40, TimeNewsRoman_80B, TimeNewsRoman_25, Pixeloid_80, Pixeloid_40;
    BufferedImage keyImage;
    ImageIcon goldImage;
    BufferedImage titleScreenImage, titleButtonImage;
    public boolean messageOn = false;
    public int slotCol = 0;
    public int slotRow = 0;

    public String currentDialogue = "";
    ArrayList<String> messages = new ArrayList<>();
    ArrayList<Integer> messageCounterList = new ArrayList<>();
    // title
    public int commandNum = 0;
    public boolean gameFinished = false;
    UltilityTool utool = new UltilityTool();
    int arrowOffset;
    // gameState = playState UI
    BufferedImage heart_full, heart_half, heart_blank, heart_background, emblem;
    // gameState = optionsState
    public int subState = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        Pixeloid_40 = new Font("Pixeloid Sans", Font.PLAIN, 40);

        Pixeloid_80 = new Font("Pixeloid Sans", Font.PLAIN, 80);
        Consolas_40 = new Font("Consolas", Font.PLAIN, 40);
        TimeNewsRoman_80B = new Font("Times New Roman", Font.BOLD, 40);
        TimeNewsRoman_25 = new Font("Times New Roman", Font.PLAIN, 25);

        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
        OBJ_Gold gold = new OBJ_Gold(gp);
        goldImage = gold.imageIcon;
            //CREATE 2D OBJECT
        Entity heart = new OBJ_Heart(gp);
        heart_blank = heart.image;
        heart_full = heart.image2;
        heart_background = heart.image3;
        emblem = heart.image4;
        arrowOffset = gp.tileSize / 2;
    }

    public void drawPauseScreen() {
        String text = "PAUSED";
        int x = getXforCenteredText(text), y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {
        //Window
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 6;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 5;
        drawSubWindow(x, y, width, height);

        //draw texts
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 22F));
        x += gp.tileSize;
        y += gp.tileSize;
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawGameOverScreen() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80f));

        text = "Game Over";
//Shadow
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);
// Main
        g2.setColor(Color.red);
        g2.drawString(text, x - 4, y - 4);
        // Retry
        g2.setFont(g2.getFont().deriveFont(25f));
        g2.setColor(Color.white);
        text = "Retry";
        x = getXforCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - arrowOffset, y);
        }
// Back to the title screen
        text = "Exit to menu";
        x = getXforCenteredText(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - arrowOffset, y);
        }

    }

        public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(87, 72, 82, 240);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(185, 145, 88);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
        System.out.println("subwindow drawn");
    }

    // notification
    public void addMessage(String text) {
        messages.add(text);
        messageCounterList.add(0);

    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(Pixeloid_80);
        g2.setColor(Color.white);
        switch (gp.gameState) {
            //TITLE state
            case 0 -> drawTitleScreen(g2);
            case 1 -> {
                // ìf playState
                // display playing ui
                // set font
//
                drawMessages();
                drawPlayerLife();
                drawGold();

            }
            case 2 -> {
                //if pausing state
                drawPlayerLife();
                drawPauseScreen();

            }
            //DIALOGUE state
            case 3 -> {
                //ìf dialogue state
                drawPlayerLife();
                drawDialogueScreen();
            }
            case 4 -> {
                drawPlayerLife();
                try {
                    inventoryFrame = ImageIO.read(getClass().getResourceAsStream("/object/Inventory.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                drawInventory();
            }
            case 5 -> drawOptionsScreen();
            case 6 -> drawGameOverScreen();
        }
    }
    private void drawGold(){
        int x = gp.tileSize * 16;
        int y = gp.tileSize/2;

        g2.drawImage(goldImage.getImage(),x, y-8, gp.tileSize, gp.tileSize, null);


        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 31f));
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.white);
        x += gp.tileSize+10;
        y += gp.tileSize/2+2;
        g2.drawString("x "+gp.player.gold, x, y);

        g2.setColor(Color.RED);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30f));
        g2.setStroke(new BasicStroke(1));


        g2.drawString("x "+gp.player.gold, x-1, y-1);

    }
    private void drawMessages() {
        int messageX = gp.tileSize;
        int messageY = gp.tileSize * 3;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 18f));
        for(int i = 0 ; i<messages.size();i++){
            if(messages.get(i)!=null){
                g2.setColor(Color.black);
                g2.drawString(messages.get(i),messageX+2,messageY+2);

                g2.setColor(Color.white);
                g2.drawString(messages.get(i),messageX,messageY);
                int counter = messageCounterList.get(i)+1;
                messageCounterList.set(i,counter); //set the counter to the list
                messageY+= gp.tileSize;
                if(messageCounterList.get(i) > 120){ //if the message is shown for 120 frames
                    messages.remove(i);
                    messageCounterList.remove(i);//reset the counter
                }
            }

        }
    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

    public void drawPlayerLife() {
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;
        //blank max heart
        g2.drawImage(emblem, x - gp.tileSize / 4, y - gp.tileSize / 4, null);
        g2.drawImage(heart_background, x + gp.tileSize + gp.tileSize / 4, y, null);

        double oneScale = (double) (gp.player.maxLife * gp.tileSize + gp.tileSize * 1.5) / gp.player.maxLife;
        double hpBarValue = oneScale * gp.player.life;

        x = gp.tileSize * 2 + 1;
        y = y + gp.tileSize / 5;

        g2.drawImage(heart_full, x, y, (int) hpBarValue, gp.tileSize / 3 + 4, null);
    }

    public void drawOptionsScreen() {
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(20F));

        // Create sub windows
        int frameX = gp.tileSize * 6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        switch (subState) {
            case 0 -> option_top(frameX, frameY);
            case 1 -> option_control(frameX, frameY);
            case 2 -> option_endGameConfirmation(frameX, frameY);
        }
        gp.keyH.enterPressed = false;
    }

    public void option_top(int frameX, int frameY) {
        int textX;
        int textY;
        // TITLE
        String text = "Options";

        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);
        //FULLSCREEN ON?OFF
        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("FullScreen", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - arrowOffset, textY);
            if (gp.keyH.enterPressed) {
                if (!gp.fullScreenOn) {
                    gp.fullScreenOn = true;
                    gp.setFullScreen();
                } else {
                    gp.fullScreenOn = false;
                    gp.setSmallScreen();
                }

            }
        }
        //MUSIC
        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Music", textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - arrowOffset, textY);
        }
        // SOUND
        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Sound Effects", textX, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX - arrowOffset, textY);
        }
        // CONTROL
        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Control", textX, textY);
        if (commandNum == 3) {
            g2.drawString(">", textX - arrowOffset, textY);
            if (gp.keyH.enterPressed) {
                subState = 1;
                commandNum = 0;
            }
        }
        //End game
        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("End Game", textX, textY);
        if (commandNum == 4) {
            g2.drawString(">", textX - arrowOffset, textY);
            if (gp.keyH.enterPressed) {
                subState = 2;
                commandNum = 0;
            }
        }
        //Back
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 2;
        g2.drawString("Back", textX, textY);
        if (commandNum == 5) {
            g2.drawString("> ", textX - arrowOffset, textY);
            if (gp.keyH.enterPressed) {
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }

        //FULLSCREEN checkbox
        textX = frameX + (int) (gp.tileSize * 4.5);
        textY = frameY + gp.tileSize * 2 - 20;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 20, 20);
        if (gp.fullScreenOn) {
            g2.fillRect(textX, textY, 20, 20);
        }
        //MUSIC
        textY += gp.tileSize;
        g2.drawRect(textX, textY, gp.tileSize * 3, 20);
        int volumeWidth = 29 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 20);

        //Sound effect

        textY += gp.tileSize;
        g2.drawRect(textX, textY, gp.tileSize * 3, 20);

        volumeWidth = 29 * gp.sound_effect.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 20);

        gp.config.saveConfig();

    }

    public void drawTitleScreen(Graphics2D g2) {

        try {
            titleScreenImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title_screen/Start_Menu.png")));
            titleButtonImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title_screen/title_button.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2.setColor(Color.black);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));
        titleScreenImage = utool.scaleImage(titleScreenImage, gp.screenWidth, gp.screenHeight);
        g2.drawImage(titleScreenImage, 0, 0, null);

        int button_x = gp.screenWidth / 2 - gp.screenHeight / 5 + gp.screenWidth / (640);
        int button_y = gp.screenHeight / 2;
        int space = gp.screenHeight / 4 - gp.screenHeight / 7;

        g2.drawImage(titleButtonImage, button_x, button_y, null);

        int textspace = gp.screenHeight / 14;
        String text1 = "New Game";
        int x = getXforCenteredText(text1);
        int x_choose = x;
        int y = button_y + textspace;
        g2.drawString(text1, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }
        button_y += space;

        g2.drawImage(titleButtonImage, button_x, button_y, null);

        String text2 = "Load Game";
        y = button_y + textspace;
        g2.drawString(text2, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }
        button_y += space;

        g2.drawImage(titleButtonImage, button_x, button_y, null);


        String text3 = "Settings";
        x += textspace / 3;
        y = button_y + textspace;
        g2.drawString(text3, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x_choose, y);
        }
        button_y += space;

        g2.drawImage(titleButtonImage, button_x, button_y, null);
        String text4 = "Exit";
        x += textspace / 2;
        y = button_y + textspace;
        if (commandNum == 3) {
            g2.drawString(">", x_choose, y);
        }
        g2.drawString(text4, x, y);
    }

    public void option_control(int frameX, int frameY) {
        int textX;
        int textY;
        //Title
        String text = "Control";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Confirm/Interact", textX, textY);
        textY += gp.tileSize;

        g2.drawString("Attack", textX, textY);
        textY += gp.tileSize;

        g2.drawString("Pause", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Options", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Inventory", textX, textY);
        textY += gp.tileSize;

        textX = frameX + gp.tileSize * 5;
        textY = frameY + gp.tileSize * 2;
        g2.drawString("WASD", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Enter", textX, textY);
        textY += gp.tileSize;

        g2.drawString("K", textX, textY);
        textY += gp.tileSize;

        g2.drawString("F1", textX, textY);
        textY += gp.tileSize;

        g2.drawString("ESC", textX, textY);
        textY += gp.tileSize;

        g2.drawString("C", textX, textY);
        textY += gp.tileSize;

        //going back to the main option menu
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize * 9;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - arrowOffset, textY);
            if (gp.keyH.enterPressed) {
                subState = 0;
                commandNum = 3;
            }
        }

    }

    public void option_endGameConfirmation(int frameX, int frameY) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize;
        currentDialogue = "Quit the game and \nreturn to the title screen?";
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }
        //Confirm
        String text = "yes";
        textX = getXforCenteredText(text);
        textY += gp.tileSize * 3;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - arrowOffset, textY);
            if (gp.keyH.enterPressed) {
                subState = 0;
                gp.stopMusic();
                gp.gameState = gp.titleState;

            }
        }
        //Cancel
        text = "No";
        textX = getXforCenteredText(text);
        textY += gp.tileSize * 3;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - arrowOffset, textY);
            if (gp.keyH.enterPressed) {
                subState = 0;
                commandNum = 4;
            }
        }

    }

    public void drawInventory() {
        //FRAME
        int frameX = gp.tileSize * 7;
        int frameY = gp.tileSize*3;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 5;
        g2.drawImage(inventoryFrame, frameX, frameY, frameWidth, frameHeight, null);
        //SLOT
        final int slotXstart = frameX + 34;
        final int slotYstart = frameY + 35;
        int slotX = slotXstart;
        int slotY = slotYstart;


        //CURSOR
        int cursorX = slotXstart + ((gp.tileSize + 10) * slotCol);
        int cursorY = slotYstart + ((gp.tileSize - 2) * slotRow);
        int cursorWidth = gp.tileSize - 5;
        int cursorHeight = gp.tileSize - 8;
        //DRAW ITEMS
        for (int i = 0; i < gp.player.inventory.size(); i++) {
            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, cursorWidth, cursorHeight, null);
            slotX += (gp.tileSize + 10);
            if (i == 3 || i == 8 || i == 13) {
                slotX = slotXstart;
                slotY += (gp.tileSize - 2);
            }
        }
        //DRAW CURSOR

        g2.setColor(Color.white);

        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
        //DESCRIPTION FRAME
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize * 3;

        //DRAW DESCRIPTION TEXT
        int textX = dFrameX + 20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(28F));
        int itemIndex = getItemIndexOnSlot();
        if (itemIndex < gp.player.inventory.size()) {
            drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
            for (String line : gp.player.inventory.get(itemIndex).description.split("\n")) {
                g2.drawString(line, textX, textY);
                textY += 32;
            }
        }

    }

    public int getItemIndexOnSlot() {
        int itemIndex = slotCol + (slotRow * 5);
        return itemIndex;
    }
}
