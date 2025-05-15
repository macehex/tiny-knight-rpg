package main;

import entity.NPC;
import entity.King;
import main.monster.MON_Shroom;
import object.*;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;

    }
    public void setObject(){
        // place coordinates of object
        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = 22 *gp.tileSize;
        gp.obj[0].worldY = 21 * gp.tileSize;
        gp.obj[1] = new OBJ_Key(gp);
        gp.obj[1].worldX = 19 *gp.tileSize;
        gp.obj[1].worldY =  20 * gp.tileSize;
        gp.obj[2] = new OBJ_Door(gp);
        gp.obj[2].worldX = 23 *gp.tileSize;
        gp.obj[2].worldY = 25 * gp.tileSize;

        gp.obj[3] = new OBJ_Chest(gp);
        gp.obj[3].worldX = 19 *gp.tileSize;
        gp.obj[3].worldY = 21 * gp.tileSize;

        gp.obj[4] = new OBJ_Door(gp);
        gp.obj[4].worldX = 27 *gp.tileSize;
        gp.obj[4].worldY = 21 * gp.tileSize;


        gp.obj[5] = new OBJ_Potion_Speed(gp);
        gp.obj[5].worldX = 20 *gp.tileSize;
        gp.obj[5].worldY = 26 * gp.tileSize;

        gp.obj[6] = new OBJ_Potion_Heath_Two(gp);
        gp.obj[6].worldX = 22 *gp.tileSize;
        gp.obj[6].worldY = 26 * gp.tileSize;
    }
    public void setNPC(){
        gp.npc[0]= new NPC(gp);
        gp.npc[0].worldX = gp.tileSize*27;
        gp.npc[0].worldY =gp.tileSize*17;

        gp.npc[1]= new NPC(gp);
        gp.npc[1].worldX = gp.tileSize*10;
        gp.npc[1].worldY =gp.tileSize*10;
    }
    public void setMonster(){
        gp.monster[0] = new MON_Shroom(gp);
        gp.monster[0].worldX = gp.tileSize*29;
        gp.monster[0].worldY = gp.tileSize*6;
        gp.monster[1] = new MON_Shroom(gp);
        gp.monster[1].worldX = gp.tileSize*27;
        gp.monster[1].worldY = gp.tileSize*8;
        gp.monster[2] = new MON_Shroom(gp);
        gp.monster[2].worldX = gp.tileSize*9;
        gp.monster[2].worldY = gp.tileSize*9;

    }
    public void setKing(){
        gp.king[0]= new King(gp);
        gp.king[0].worldX = gp.tileSize*9;
        gp.king[0].worldY =gp.tileSize*10;
    }

}
