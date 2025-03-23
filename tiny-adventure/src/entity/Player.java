package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends  Entity{
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        // PLACEHOLDER
        x = 100;
        y = 100;
        speed = 4;
        direction = "right";
    }
    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Warrior_run1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Warrior_run1.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Warrior_run2.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Warrior_run2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Warrior_run3.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Warrior_run4.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Warrior_run5.png")));
            right2= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Warrior_run6.png")));


        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyH.upPressed){
            direction = "up";
            y -= speed;
        }
        else if(keyH.downPressed){
            direction = "down";
            y += speed;
        }
        else if(keyH.leftPressed){
            direction = "left";
            x -= speed;
        }
        else if(keyH.rightPressed){
            direction = "right";
            x += speed;
        }
    }
    public void draw(Graphics2D g2) {

//        g2.setColor(Color.white);
//        g.fillRect(x, y, width, height);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;
        switch (direction){
            case "up":
                image = up1;
                break;
            case"down":
                image = down1;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
