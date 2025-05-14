package main;

import entity.Entity;
import entity.King;
import entity.Player;
import object.SuperObject;
import tiles.TileManager;
import tiles.Tile;

import java.awt.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    //scale to screen
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    //remember to scale characters and objects too
    // how many tiles can be displayed on a screen
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12; //4:3
    public final int screenWidth = tileSize * maxScreenCol;  //16*16*3 = 768
    public final int screenHeight = tileSize * maxScreenRow; //12*16*3 = 576

    // GAME WORLD SETTINGS:
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
//    public final int worldWidth = tileSize * maxWorldCol;
//    public final int worldHeight = tileSize * maxWorldRow;

    //Setting the
    int FPS = 60;

    //Tile
    TileManager tileM = new TileManager(this);


    //creating a game clock
    public KeyHandler keyH = new KeyHandler(this);
    // Sound
    Sound music = new Sound();
    Sound sound_effect = new Sound();

    public CollisionChecker cChecker = new CollisionChecker(this);
    //setting stuffs
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);
    public Entity npc[] = new Entity[10];
    public Entity king[] = new King[2];
    //UI
    public UI ui = new UI(this);
    Thread gameThread;
    public SuperObject obj[] = new SuperObject[10];

    // CREATING GAME STATE
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {

        aSetter.setObject(); //calling setobject method
        aSetter.setNPC();
        aSetter.setKing();
        playMusic(6);
        stopMusic();
        gameState = playState;
    }

    public void startGameThread() {
        gameThread = new Thread(this); // this : passing the GamePanel class to the constructor
        gameThread.start();
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
                repaint();
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
        if (gameState == playState) {
            player.update();
            //NPC update
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }

        }
        if (gameState == pauseState) {
            //nothing yet
            // update UI?
        }
    }

    public void paintComponent(Graphics g) {
        //Jpanel drawing standard
        super.paintComponent(g); // super = parent class of this class -> Jpanel
        Graphics2D g2 = (Graphics2D) g; // change for more functionalities
        // DEBUGGING
        long drawStart = 0;
        if (keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }


        tileM.draw(g2); //title is the layer before player

        //draw object
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }
        // play npc
        for (int i = 0; i < npc.length; i++) {
            if (npc[i] != null) {
                npc[i].draw(g2);
            }
        }
        player.draw(g2);
        //ui goes after player
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


        g2.dispose();//dispose graphics context and release any system resources that it is using
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
}
