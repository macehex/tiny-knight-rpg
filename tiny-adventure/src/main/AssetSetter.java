package main;

import entity.Entity;
import entity.NPC;
import main.monster.MON_Shroom;
import object.*;

import java.util.function.Supplier;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;

    }

    public void setObject() {
        int mapNum = 0;
        // Array of object constructors (in order)
        Supplier<Entity>[] objectSuppliers = new Supplier[]{
                //key
                () -> new OBJ_Key(gp),
                () -> new OBJ_Key(gp),
                () -> new OBJ_Key(gp),
                () -> new OBJ_Key(gp),
                // Door
                    () -> new OBJ_Door(gp),
                    () -> new OBJ_Door(gp),
                    () -> new OBJ_Door(gp),
                    () -> new OBJ_Door(gp),

                // Chests
                () -> new OBJ_Chest(gp),
                () -> new OBJ_Chest(gp),
                () -> new OBJ_Chest(gp),
                () -> new OBJ_Chest(gp),
                () -> new OBJ_Chest(gp),
                () -> new OBJ_Chest(gp),
                () -> new OBJ_Chest(gp),
                () -> new OBJ_Chest(gp),
                () -> new OBJ_Chest(gp),

                // Other objects
                () -> new OBJ_Potion_Speed(gp),
                () -> new OBJ_Potion_Heath_Two(gp),
                () -> new OBJ_Potion_Heath_Two(gp),
                () -> new OBJ_Potion_Heath_Two(gp),
                () -> new OBJ_Potion_Heath_Two(gp),


                () -> new OBJ_Portal(gp)
        };

        // Corresponding coordinates for each object
        int[][] objectCoords = {
                // Key
                {74, 42},
                {60, 42},
                {63, 23},
                {11, 20},

                // Door
                    {12, 18},
                {61,40},
                {75,40},
                {64,21},
                // Chests
                {52, 28},
                {61, 35},
                {73, 35},
                {12, 42},
                {63, 35},
                {87, 20},
                {40, 6},
                {38, 41},
                {15, 6},

                // Other objects
                {53, 28}, // Speed Potion
                // Health Potion
                {42, 7},
                {9, 10},
                {61, 13},
                {92, 52},

                {13, 43}  // Portal
        };

        // Safety check: prevent mismatches
        if (objectSuppliers.length != objectCoords.length) {
            throw new IllegalStateException("Mismatch: " + objectSuppliers.length + " suppliers vs " + objectCoords.length + " coordinates.");
        }

        // Loop to create and place each object
        for (int i = 0; i < objectSuppliers.length; i++) {
            gp.obj[mapNum][i] = objectSuppliers[i].get();
            gp.obj[mapNum][i].worldX = objectCoords[i][0] * gp.tileSize;
            gp.obj[mapNum][i].worldY = objectCoords[i][1] * gp.tileSize;
        }


    }

    public void setNPC() {
        int mapNum = 0;
        int[][] npcCoords = {
                {35, 16}, // NPC coordinates
        };
        // Loop through and spawn NPCs
        for (int i = 0; i < npcCoords.length; i++) {
            gp.npc[mapNum][i] = new NPC(gp);
            gp.npc[mapNum][i].worldX = gp.tileSize * npcCoords[i][0];
            gp.npc[mapNum][i].worldY = gp.tileSize * npcCoords[i][1];
        }
    }

    public void setMonster() {
        int mapNum = 0;
        // 2D array of monster coordinates (x, y)
        int[][] monsterCoords = {
                {10, 10},
                {11, 10},
                {12, 10},
                {13, 10},
                {14, 10},
                {10, 11},
                {30, 5},
                {12, 32},
                {65, 30},
                {52, 50},
                {74, 38},
                {41, 8},
        };
        // Loop through and spawn monsters
        for (int i = 0; i < monsterCoords.length; i++) {
            gp.monster[mapNum][i] = new MON_Shroom(gp);
            gp.monster[mapNum][i].worldX = gp.tileSize * monsterCoords[i][0];
            gp.monster[mapNum][i].worldY = gp.tileSize * monsterCoords[i][1];
        }

    }
}
