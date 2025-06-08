package main;

import entity.NPC;
import main.monster.MON_Shroom;
import object.*;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;

    }
    public void setObject(){
        int mapNum = 0;
        //set stuffs at other map with mapNym = smth else
        // place coordinates of object
        gp.obj[mapNum][0] = new OBJ_Key(gp);
        gp.obj[mapNum][0].worldX = 22 *gp.tileSize;
        gp.obj[mapNum][0].worldY = 21 * gp.tileSize;
        gp.obj[mapNum][1] = new OBJ_Key(gp);
        gp.obj[mapNum][1].worldX = 19 *gp.tileSize;
        gp.obj[mapNum][1].worldY =  20 * gp.tileSize;
        gp.obj[mapNum][2] = new OBJ_Door(gp);
        gp.obj[mapNum][2].worldX = 23 *gp.tileSize;
        gp.obj[mapNum][2].worldY = 25 * gp.tileSize;

        gp.obj[mapNum][3] = new OBJ_Chest(gp);
        gp.obj[mapNum][3].worldX = 19 *gp.tileSize;
        gp.obj[mapNum][3].worldY = 21 * gp.tileSize;

        gp.obj[mapNum][4] = new OBJ_Door(gp);
        gp.obj[mapNum][4].worldX = 27 *gp.tileSize;
        gp.obj[mapNum][4].worldY = 21 * gp.tileSize;


        gp.obj[mapNum][5] = new OBJ_Potion_Speed(gp);
        gp.obj[mapNum][5].worldX = 20 *gp.tileSize;
        gp.obj[mapNum][5].worldY = 26 * gp.tileSize;

        gp.obj[mapNum][6] = new OBJ_Potion_Heath_Two(gp);
        gp.obj[mapNum][6].worldX = 22 *gp.tileSize;
        gp.obj[mapNum][6].worldY = 26 * gp.tileSize;
    }
    public void setNPC(){
        int mapNum = 0;
        gp.npc[mapNum][0]= new NPC(gp);
        gp.npc[mapNum][0].worldX = gp.tileSize*27;
        gp.npc[mapNum][0].worldY =gp.tileSize*17;

        gp.npc[mapNum][1]= new NPC(gp);
        gp.npc[mapNum][1].worldX = gp.tileSize*10;
        gp.npc[mapNum][1].worldY =gp.tileSize*10;

    }
    public void setMonster(){
        int mapNum = 0;

        gp.monster[mapNum][0] = new MON_Shroom(gp);
        gp.monster[mapNum][0].worldX = gp.tileSize*29;
        gp.monster[mapNum][0].worldY = gp.tileSize*6;
        gp.monster[mapNum][1] = new MON_Shroom(gp);
        gp.monster[mapNum][1].worldX = gp.tileSize*27;
        gp.monster[mapNum][1].worldY = gp.tileSize*8;
        gp.monster[mapNum][2] = new MON_Shroom(gp);
        gp.monster[mapNum][2].worldX = gp.tileSize*9;
        gp.monster[mapNum][2].worldY = gp.tileSize*9;

    }
}
