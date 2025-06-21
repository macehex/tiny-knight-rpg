package controller;

import model.AssetSetter;
import main.Main;
import model.CollisionChecker;
import model.Config;
import model.EventHandler;
import model.ai.PathFinder;
import model.entity.Entity;
import model.entity.Player;
import view.UI;
import view.tiles.TileManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    public final int originalTileSize = 16; // 16x16 tile
    //scale to screen
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    //remember to scale characters and objects too
    // how many view.tiles can be displayed on a screen
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12; // ~ 16:9
    public final int screenWidth = tileSize * maxScreenCol;  //16*16*3 = 768
    public final int screenHeight = tileSize * maxScreenRow; //12*16*3 = 576
//    FULLSCREEN MODE
    public boolean fullScreenOn = false;
    public int screenWidth2 = screenWidth;
    public int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;

    // GAME WORLD SETTINGS:
    public final int maxWorldCol = 100;
    public final int maxWorldRow = 55;
//    public final int worldWidth = tileSize * maxWorldCol;
//    public final int worldHeight = tileSize * maxWorldRow;
    public final int maxMap = 10;
    public int currentMap = 0;

    //Setting the
    int FPS = 60;

    //Tile
    public TileManager tileM = new TileManager(this);

    //creating a game clock
    public KeyHandler keyH = new KeyHandler(this);
    // System
    public PathFinder pFinder = new PathFinder(this);
    // Sound
    public Sound music = new Sound();
    public Sound sound_effect = new Sound();

    public CollisionChecker cChecker = new CollisionChecker(this);
    public EventHandler eHandler = new EventHandler(this);
    //setting stuffs
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);

    public Entity[][] npc = new Entity[maxMap][10];
    public Entity[][] obj = new Entity[maxMap][100];
    public Entity[][] monster = new Entity[maxMap][30];

    ArrayList<Entity> entityList = new ArrayList<>();
    //UI
    public UI ui = new UI(this);
    Thread gameThread;

    // CREATING GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int optionsState = 5;
    public final int gameOverState = 6;
    public final int characterState = 4;
    //Config
    public static Config config ;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //
        this.addKeyListener(keyH);
        this.setFocusable(true);
        config = new Config(this);
    }

    public void setupGame() {

//        playMusic(6);
//        stopMusic();
        //DEFAULT
//        gameState = titleState;
        gameState = titleState;

//        aSetter.setObject(); //calling setobject method
//        aSetter.setNPC();
////        aSetter.setMonster();

        //fullscreen processing
        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)tempScreen.getGraphics();
//        setFullScreen();
        setSmallScreen();
    }


    public void startGameThread() {
        gameThread = new Thread(this); // this : passing the GamePanel class to the constructor
        gameThread.start();
    }
    public void retry(){
        player.setDefaultPosition();
        player.restoreLife();
        aSetter.setMonster();
        aSetter.setNPC();
        aSetter.setPotion();
    }
    public void restart(){
        player.setDefaultValues();
        player.setDefaultPosition();
        player.restoreLife();
        aSetter.setMonster();
        aSetter.setNPC();
        player.setItems();
        aSetter.setObject();
    }
    @Override
    // run automatically called when running startGameThread()
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta > 1) {
                update();
//                repaint();
                drawToTempScreen(); //draw everything to the buffered image
                drawToScreen(); // draw the buffered image to the screen
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
//                System.out.printf("FPS: "+drawCount+"\n");
                System.out.println("Playerx: " + player.worldX / this.tileSize + " PlayerY: " + player.worldY / this.tileSize);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        switch(gameState){
            case playState:
                player.update();
                //NPC update
                for (int i = 0; i < npc[1].length; i++) {
                    if (npc[currentMap][i] != null) {
                        npc[currentMap][i].update();
                    }
                }
                for (int i = 0; i < monster[1].length; i++) {
                    if (monster[currentMap][i] != null&&monster[currentMap][i].alive) {

                        monster[currentMap][i].update();

                        if(monster[currentMap][i].dying){
                            monster[currentMap][i].speed=0;
                        }
                    }
                    if(monster[currentMap][i] != null&&!monster[currentMap][i].alive){
                        monster[currentMap][i]= null;
                    }
                }
                break;
            case pauseState:
                //nothing yet
                // update UI?
                break;
        }
    }
    //getter and setter for gameState
    public int getCurrentMap() {
        return currentMap;
    }

    public void drawToTempScreen(){
        // DEBUGGING
        long drawStart = 0;
        if (keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }
        // TITLE SCREEN
        if(gameState == titleState){
            ui.draw(g2);
        }
        // NOT TITLE SCREEN
//        else if(gameState == playState){
        else{
            tileM.draw(g2); //title is the layer before player
            entityList.add(player);
            for(int i =0; i<npc[1].length;i++){
                if(npc[currentMap][i]!=null){

                    entityList.add(npc[currentMap][i]);

                }
            }
            for(int i =0; i< obj[1].length;i++){
                if(obj[currentMap][i]!=null){
                    entityList.add(obj[currentMap][i]);
                }
            }
            for(int i =0; i< monster[1].length;i++){
                if(monster[currentMap][i]!=null){
                    entityList.add(monster[currentMap][i]);
                }
            }



            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity o1, Entity o2) {
                    int result = Integer.compare(o1.worldY,o2.worldY);
                    return result;
                }
            });
            //draw entities
            for(int i = 0 ; i< entityList.size();i++){
                entityList.get(i).draw(g2);
            }
            //empty
            entityList.clear();

            ui.draw(g2);

            // the next layer could be effects and items ?
            // DEBUGGING
            if (keyH.checkDrawTime == true) {
                long drawEnd = System.nanoTime();
                long passed = drawEnd - drawStart;
                g2.setColor(Color.white);
                g2.drawString("Draw Time: " + passed, 10, 400);
                System.out.println("Draw Time: " + passed);
            }
        }
    }
    public void drawToScreen(){
        Graphics g = getGraphics();
        g.drawImage(tempScreen,0,0,screenWidth2,screenHeight2,null);
        g.dispose();
    }
    public void setFullScreen(){
        // set local screen device so it can work on any monitor
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        // get FULLSCREEN height , width
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
    }
    public void setSmallScreen(){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(null);
        // get normal height , width
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
    }
    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSoundEffect(int i) {
        sound_effect.setFile(i);
        sound_effect.play();
    }

    public int getScreenWidth() {
        return screenWidth;
    }
    public int getScreenHeight(){
        return screenHeight;
    }
}
