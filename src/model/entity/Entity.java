package model.entity;

import controller.GamePanel;
import model.object.OBJ_Gold;
import model.object.OBJ_Key;
import model.object.OBJ_Potion_Heath_Two;
import view.UltilityTool;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Entity {
    // blueprint for all chars, monsters, objects
    GamePanel gp;
    public BufferedImage image, image2, image3, image4, image5, image6;
    public BufferedImage dead1, dead2, dead3, dead4, dead5, dead6, dead7, dead8, dead9, dead10, dead11, dead12, dead13, dead14;
    public BufferedImage attackUp1, attackUp2, attackUp3, attackUp4, attackUp5, attackUp6,
            attackDown1, attackDown2, attackDown3, attackDown4, attackDown5, attackDown6;
    public BufferedImage attackRight1, attackRight2, attackRight3, attackRight4, attackRight5, attackRight6;
    public BufferedImage attackLeft1, attackLeft2, attackLeft3, attackLeft4, attackLeft5, attackLeft6;
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public String name;
    public boolean collision = false;
    public boolean invincible = false;
    protected int defaultSpeed = 1;
    protected int tileDistance;
    private int xDistance;
    private int yDistance;
    //COUNTER
    public int invincibleCounter = 0;
    public int spriteCounter = 0;
    public int offBalanceCounter = 0;
    public int knockBackCounter = 0;


    int dyingCounter = 0;
    public int shotAvailableCounter = 0;

    public int worldX, worldY; // game camera
    public int speed;
    int hpBarCounter = 0;
    public BufferedImage up1, up2, up3, up4, up5, up6,
            down1, down2, down3, down4, down5, down6,
            left1, left2, left3, left4, left5, left6,
            right1, right2, right3, right4, right5, right6,
            idle1, idle2, idle3, idle4, idle5, idle6;
    public BufferedImage swim_up1, swim_up2, swim_up3, swim_up4, swim_up5, swim_up6,
            swim_down1, swim_down2, swim_down3, swim_down4, swim_down5, swim_down6,
            swim_left1, swim_left2, swim_left3, swim_left4, swim_left5, swim_left6,
            swim_right1, swim_right2, swim_right3, swim_right4, swim_right5, swim_right6,
            swim_idle1, swim_idle2, swim_idle3, swim_idle4, swim_idle5, swim_idle6;
    public ImageIcon imageIcon;
    public String direction = "down";

    public int spriteNum = 1;

    public int solidAreaDefaultX, solidAreaDefaultY;
    // STATE hit box
    public boolean collisionOn = false;
    public boolean attacking = false;
    //default model.entity hitbox
    public Rectangle solidArea = new Rectangle(0, 0, 32, 32);

    //NPC variables
    public int actionLockCounter = 0;
    String[] dialogues = new String[20];
    int dialogueIndex = 0;

    //Character and monster  status
    public int type; // 0 - player, 1 = npc, 2 = monster

    public int maxLife;
    public int life;
    public boolean alive = true;
    public boolean dying = false;
    boolean hpBarOn = false;
    public Entity currentWeapon;
    //ITEMS ATTRIBUTES
    public int attackValue = 1;
    public int defenseValue;
    public String description = "";
    // AI pathfinding
    public boolean onPath = false;

    //different model.entity: npc
    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    // ATTRIBUTES
    public boolean offBalance = false;

    public int knockBackPower;
    public Entity attacker;
    public boolean knockBack = false;
    public String knockBackDirection;

    public int maxMana;
    public int mana;
    public int ammo;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public int motion1_duration;
    public int motion2_duration;
    public Entity currentShield;
    public Entity currentLight;
    public boolean boss;

    public void setAction() {

    }

    public void damageReaction() {

    }

    public void speak() {

    }

    public void update() {
        // if subclass have same method -> subclass method have the priority
        if (knockBack == true) {
            checkCollision();
            if (collisionOn) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            } else {
                switch (gp.player.getDirection()) {
                    case "up":
                        worldY -= speed;
                        break;

                    case "down":
                        worldY += speed;
                        break;

                    case "left":
                        worldX -= speed;
                        break;

                    case "right":
                        worldX += speed;
                        break;
                }
            }
            knockBackCounter++;
            if (knockBackCounter == 10) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
        } else if (attacking == true) {
            attacking();
        }
        else{
            setAction();
            checkCollision();
            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            spriteCounter++;
            //update() gets called 60 times per second
            if (spriteCounter > 6) {
                switch (spriteNum) {
                    case 1:
                        spriteNum = 2;
                        break;
                    case 2:
                        spriteNum = 3;
                        break;
                    case 3:
                        spriteNum = 4;
                        break;
                    case 4:
                        spriteNum = 5;
                        break;
                    case 5:
                        spriteNum = 6;
                        break;
                    case 6:
                        spriteNum = 1;
                        break;
                }
                spriteCounter = 0;
            }

        }
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 30) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if (offBalance == true) {
            offBalanceCounter++;
            if (offBalanceCounter > 60) {
                offBalance = false;
                offBalanceCounter = 0;
            }
        }


    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        // dont draw all of the view.tiles
        if (worldX + gp.getTileSize() > gp.player.worldX - gp.player.screenX &&
                worldX - gp.getTileSize() < gp.player.worldX + gp.player.screenX &&
                worldY + gp.getTileSize() > gp.player.worldY - gp.player.screenY &&
                worldY - gp.getTileSize() < gp.player.worldY + gp.player.screenY) {
            switch (direction) {
                case "idle":
                    image = switch (spriteNum) {
                        case 1 -> idle1;
                        case 2 -> idle2;
                        case 3 -> idle3;
                        case 4 -> idle4;
                        case 5 -> idle5;
                        case 6 -> idle6;
                        default -> image;
                    };
                    break;
                case "up":
                    image = switch (spriteNum) {
                        case 1 -> up1;
                        case 2 -> up2;
                        case 3 -> up3;
                        case 4 -> up4;
                        case 5 -> up5;
                        case 6 -> up6;
                        default -> image;
                    };
                    break;
                case "down":
                    image = switch (spriteNum) {
                        case 1 -> down1;
                        case 2 -> down2;
                        case 3 -> down3;
                        case 4 -> down4;
                        case 5 -> down5;
                        case 6 -> down6;
                        default -> image;
                    };
                    break;
                case "left":
                    image = switch (spriteNum) {
                        case 1 -> left1;
                        case 2 -> left2;
                        case 3 -> left3;
                        case 4 -> left4;
                        case 5 -> left5;
                        case 6 -> left6;
                        default -> image;
                    };
                    break;
                case "right":
                    image = switch (spriteNum) {
                        case 1 -> right1;
                        case 2 -> right2;
                        case 3 -> right3;
                        case 4 -> right4;
                        case 5 -> right5;
                        case 6 -> right6;
                        default -> image;
                    };
                    break;
            }
            // Monster health bar
            if (type == 2 && hpBarOn) {

                double oneScale = ((double) gp.getTileSize() / maxLife);
                double hpBarValue = oneScale * life;

                g2.setColor(new Color(35, 35, 35));
                g2.fillRoundRect(screenX - 1, screenY - gp.getTileSize() / 5 - 1, gp.getTileSize() + 2, gp.getTileSize() / 5 + 2, 2, 2);
                g2.setColor(new Color(219, 92, 92));
                g2.fillRoundRect(screenX, screenY - gp.getTileSize() / 5, (int) hpBarValue, gp.getTileSize() / 5, 1, 1);
                hpBarCounter++;
                if (hpBarCounter > 480) {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }


            //make transparent
            if (invincible) {
                hpBarOn = true;
                hpBarCounter = 0;
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            }
//        // HIT BOX TROUBLESHOOTING
//            g2.setColor(Color.red);
//            g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
            if (dying) {
                dyingAnimation(g2, screenX, screenY);

            } else {
                g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            }

        }
    }

    public void dyingAnimation(Graphics2D g2, int screenX, int screenY) {
        dyingCounter++;

        int frameDuration = 10; // Adjust to control animation speed

        BufferedImage[] deathFrames = {
                dead1, dead2, dead3, dead4, dead5, dead6, dead7,
                dead8, dead9, dead10, dead11, dead12, dead13, dead14
        };

        int frameIndex = dyingCounter / frameDuration;

        if (frameIndex < deathFrames.length) {
            image = deathFrames[frameIndex];
        } else {
            dying = false;
            alive = false;
        }
        g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);

    }

    //Loading animated GIFS
    public ImageIcon setupAnimatedGif(String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(imagePath + ".gif"));

        // Optionally scale the image
        Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return new ImageIcon(scaled);
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        UltilityTool uTool = new UltilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaleImage(image, width, height);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public BufferedImage setup(String imagePath) {
        UltilityTool uTool = new UltilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = uTool.scaleImage(image, gp.getTileSize(), gp.getTileSize());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void getDyingImages() {
        dead1 = setup("/effects/dead/Dead1", gp.getTileSize() * 2, gp.getTileSize() * 2);
        dead2 = setup("/effects/dead/Dead2", gp.getTileSize() * 2, gp.getTileSize() * 2);
        dead3 = setup("/effects/dead/Dead3", gp.getTileSize() * 2, gp.getTileSize() * 2);
        dead4 = setup("/effects/dead/Dead4", gp.getTileSize() * 2, gp.getTileSize() * 2);
        dead5 = setup("/effects/dead/Dead5", gp.getTileSize() * 2, gp.getTileSize() * 2);
        dead6 = setup("/effects/dead/Dead6", gp.getTileSize() * 2, gp.getTileSize() * 2);
        dead7 = setup("/effects/dead/Dead7", gp.getTileSize() * 2, gp.getTileSize() * 2);
        dead8 = setup("/effects/dead/Dead8", gp.getTileSize() * 2, gp.getTileSize() * 2);
        dead9 = setup("/effects/dead/Dead9", gp.getTileSize() * 2, gp.getTileSize() * 2);
        dead10 = setup("/effects/dead/Dead10", gp.getTileSize() * 2, gp.getTileSize() * 2);
        dead11 = setup("/effects/dead/Dead11", gp.getTileSize() * 2, gp.getTileSize() * 2);
        dead12 = setup("/effects/dead/Dead12", gp.getTileSize() * 2, gp.getTileSize() * 2);
        dead13 = setup("/effects/dead/Dead13", gp.getTileSize() * 2, gp.getTileSize() * 2);
        dead14 = setup("/effects/dead/Dead14", gp.getTileSize() * 2, gp.getTileSize() * 2);
    }

    public void checkCollision() {
        collisionOn = false;
        gp.cChecker.checkTile(this); //passing "NPC" class (because npc has the priority )
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);

        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        if (this.type == 2 && contactPlayer && !gp.player.invincible) {
            damagePlayer(attack);

        }
    }

    public void searchPath(int goalCol, int goalRow) {
        int startCol = (worldX + solidArea.x) / gp.getTileSize();
        int startRow = (worldY + solidArea.y) / gp.getTileSize();

        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);

        if (gp.pFinder.search()) {
            // NEXT WORLD X / Y
            int nextX = gp.pFinder.pathList.get(0).col * gp.getTileSize();
            int nextY = gp.pFinder.pathList.get(0).row * gp.getTileSize();

            // ENTITY SOLID AREA POSITION
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.getTileSize()) {
                direction = "up";
            } else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.getTileSize()) {
                direction = "down";
            } else if (enTopY >= nextY && enBottomY < nextY + gp.getTileSize()) {
                // LEFT OR RIGHT
                if (enLeftX > nextX) {
                    direction = "left";
                }
                if (enLeftX < nextX) {
                    direction = "right";
                }
            } else if (enTopY > nextY && enLeftX > nextX) {
                // UP OR LEFT
                direction = "up";
                checkCollision();

                if (collisionOn) {
                    direction = "left";
                }
            } else if (enTopY > nextY && enLeftX < nextX) {
                // UP OR RIGHT
                direction = "up";
                checkCollision();

                if (collisionOn) {
                    direction = "right";
                }
            } else if (enTopY < nextY && enLeftX > nextX) {
                // DOWN OR LEFT
                direction = "down";
                checkCollision();

                if (collisionOn) {
                    direction = "left";
                }
            } else if (enTopY < nextY && enLeftX < nextX) {
                // DOWN OR RIGHT
                direction = "down";
                checkCollision();

                if (collisionOn) {
                    direction = "right";
                }
            }

            // IF REACH ThE GOAL, STOP THE SEARCH
            // int nextCol = gp.pFinder.pathList.get(0).col;
            // int nextRow = gp.pFinder.pathList.get(0).row;

            // if (nextCol == goalCol && nextRow == goalRow) {
            // onPath = false;
            // }
        }
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public int getScreenX() {
        return worldX - gp.player.worldX + gp.player.screenX;
    }

    public int getScreenY() {
        return worldY - gp.player.worldY + gp.player.screenY;
    }


    public int getTileDistance(Entity target)
    {
        int tileDistance = (getXdistance(target) + getYdistance(target))/gp.getTileSize();
        return tileDistance;
    }

    public int getXdistance(Entity target)
    {
        int xDistance = Math.abs(getCenterX() - target.getCenterX());
        return xDistance;
    }
    public int getYdistance(Entity target)
    {
        int yDistance = Math.abs(getCenterY() - target.getCenterY());
        return yDistance;
    }
    public int getCenterX()
    {
        int centerX = worldX + left1.getWidth()/2;
        return centerX;
    }
    public int getCenterY()
    {
        int centerY = worldY + up1.getWidth()/2;
        return centerY;
    }


    public void checkStopChasingOrNot(Entity target, int distance, int rate)
    {
        if(getTileDistance(target) > distance)
        {
            int i = new Random().nextInt(rate);
            if(i == 0)
            {
                onPath = false;
            }
        }
    }
    public void checkStartChasingOrNot(Entity target, int distance, int rate)
    {
        if(getTileDistance(target) < distance)
        {
            int i = new Random().nextInt(rate);
            if(i == 0)
            {
                onPath = true;
            }
        }
    }


    public void getRandomDirection(int interval)
    {
        actionLockCounter++;

        if(actionLockCounter > interval)
        {
            Random random = new Random();
            int i = random.nextInt(100) + 1;  // pick up  a number from 1 to 100
            if(i <= 25){direction = "up";}
            if(i>25 && i <= 50){direction = "down";}
            if(i>50 && i <= 75){direction = "left";}
            if(i>75 && i <= 100){direction = "right";}
            actionLockCounter = 0; // reset
        }
    }


    public void dropItem(Entity droppedItem)
    {
        for(int i = 0; i < gp.obj[1].length; i++)
        {
            if(gp.obj[gp.getCurrentMap()][i] == null)
            {
                gp.obj[gp.getCurrentMap()][i] = droppedItem;
                gp.obj[gp.getCurrentMap()][i].worldX = worldX;  //the dead monster's worldX
                gp.obj[gp.getCurrentMap()][i].worldY = worldY;  //the dead monster's worldY
                break; //end loop after finding empty slot on array
            }
        }
    }
    public void checkAttackOrNot(int rate, int straight, int horizontal)
    {
        boolean tartgetInRange = false;
        int xDis = getXdistance(gp.player);
        int yDis = getYdistance(gp.player);

        switch (direction)
        {
            case "up":
                if(gp.player.getCenterY() < getCenterY()  && yDis < straight && xDis < horizontal)
                {
                    tartgetInRange = true;
                }
                break;
            case "down":
                if(gp.player.getCenterY()  > getCenterY()  && yDis < straight && xDis < horizontal)
                {
                    tartgetInRange = true;
                }
                break;
            case "left":
                if(gp.player.getCenterX()  < getCenterX() && xDis < straight && yDis < horizontal)
                {
                    tartgetInRange = true;
                }
                break;
            case "right":
                if(gp.player.getCenterX() > getCenterX() && xDis < straight && yDis < horizontal)
                {
                    tartgetInRange = true;
                }
                break;
        }

        if(tartgetInRange == true)
        {
            //Check if it initiates an attack
            int i = new Random().nextInt(rate);
            if(i == 0)
            {
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
                shotAvailableCounter = 0;
            }
        }

    }

    public void checkDrop()
    {
        //CAST A DIE
        int i = new Random().nextInt(100)+1;

        //SET THE MONSTER DROP
        if (i < 50) {
            dropItem(new OBJ_Key(gp));
        } else if (i < 75) {
            dropItem(new OBJ_Potion_Heath_Two(gp));
        } else {
            dropItem(new OBJ_Gold(gp));
        }
    }
    public void attacking()
    {
        spriteCounter++;

        if(spriteCounter <= motion1_duration)
        {
            spriteNum = 1;
        }
        if(spriteCounter > motion1_duration && spriteCounter <= motion2_duration)
        {
            spriteNum = 2;

            //Save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //Adjust player's worldX/worldY for the attackArea
            switch (direction)
            {
                case "up": worldY -= attackArea.height; break;                 //attackArea's size
                case "down" : worldY += gp.getTileSize(); break;                    //gp.getTileSize()(player's size)
                case "left" : worldX -= attackArea.width; break;               //attackArea's size
                case "right" : worldX += gp.getTileSize(); break;                   //gp.getTileSize()(player's size)
            }

            //attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            if(type == 2)
            {
                if(gp.cChecker.checkPlayer(this) == true) //This means attack is hitting player
                {
                    damagePlayer(attack);
                }
            }
            else // Player
            {
                //Check monster collision with the updated worldX, worldY and solidArea
                int monsterIndex = gp.cChecker.checkEntity(this,gp.monster);
                gp.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);

//                int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
//                gp.player.damageInteractiveTile(iTileIndex);
//
//                int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
//                gp.player.damageProjectile(projectileIndex);
            }

            //After checking collision, restore the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if(spriteCounter > motion2_duration)
        {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void contactWater(int count) {
        if (!invincible && count > 50) {
            gp.playSoundEffect(8);
            life -= 1;
            invincible = true;
        }
    }

    public void damagePlayer(int attack)
    {
        if(!gp.player.invincible)
        {
            int damage = attack - gp.player.defense;
            //Get an opposite direction of this attacker
            String canGuardDirection = getOppositeDirection(direction);

            //Not guarding
                gp.playSoundEffect(8);   //receivedamage.wav
                if(damage < 1 )
                {
                    damage = 1;
                }
            if(damage != 0)
            {
                setKnockBack(gp.player, this, knockBackPower);
            }

            //We can give damage
            gp.player.life -= damage;
            gp.player.invincible = true;
        }
    }
    public void setKnockBack(Entity target, Entity attacker, int knockBackPower)
    {
        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed += knockBackPower;
        target.knockBack = true;
    }

    public String getOppositeDirection(String direction)
    {
        String oppositeDirection = "";

        switch (direction)
        {
            case "up": oppositeDirection = "down";break;
            case "down": oppositeDirection = "up";break;
            case "left": oppositeDirection = "right";break;
            case "right": oppositeDirection = "left";break;
        }

        return oppositeDirection;
    }

    public int getGoalCol(Entity target)
    {
        int goalCol = (target.worldX + target.solidArea.x) / gp.getTileSize();
        return goalCol;

    }
    public int getGoalRow(Entity target)
    {
        int goalRow = (target.worldY + target.solidArea.y) / gp.getTileSize();
        return goalRow;
    }
    public String getDirection() {
        return direction;
    }
    public int getMaxLife(){
        return maxLife;
    }


    public void setMaxLife(int maxLife) {
         this.maxLife = maxLife;

    }
}