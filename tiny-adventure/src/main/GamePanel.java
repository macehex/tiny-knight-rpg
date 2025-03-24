package main;
import entity.Player;
import tiles.TileManager;
import tiles.Tile;
import java.awt.*;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    //scale to screen
    final int scale = 6;
    public final int tileSize = originalTileSize * scale;
    //remember to scale characters and objects too
    // how many tiles can be displayed on a screen
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12; //4:3
    public final int screenWidth = tileSize * maxScreenCol;  //16*16*3 = 768
    public final int screenHeight = tileSize * maxScreenRow; //12*16*3 = 576

    //Setting the
    int FPS = 60;

    //Tile
    TileManager tileM = new TileManager(this);
    //creating a game clock
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);



    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this); // this : passing the GamePanel class to the constructor
        gameThread.start();
    }
    @Override
    // run automatically called when running startGameThread()
    public void run(){
        double drawInterval = (double) 1000000000 /FPS;
        double delta = 0 ;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(delta > 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                System.out.printf("FPS: "+drawCount+"\n");
                drawCount =0;
                timer = 0;
            }
        }
    }
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        //Jpanel drawing standard
        super.paintComponent(g); // super = parent class of this class -> Jpanel
        Graphics2D g2 = (Graphics2D)g; // change for more functionalities
        tileM.draw(g2); //title is the layer before player
        player.draw(g2);
        // the next layer could be effects and items ?
        g2.dispose();//dispose graphics context and release any system resources that it is using
    }
}
