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


                () -> new OBJ_Portal(gp),

                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),

                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),

                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),

                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),

                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp),
                () -> new OBJ_Spikes(gp)

        };

        // Corresponding coordinates for each object
        int[][] objectCoords = {
                // Key
                {74, 42},
                {60, 42},
                {11, 20},
                {93, 18},

                // Door
                {12, 18},
                {61, 40},
                {75, 40},
                {92, 14},
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

                {13, 43},  // Portal

                // Spikes
                {30, 11}, {31, 11}, {32, 11},
                {30, 12}, {31, 12}, {32, 12},
                {30, 13}, {31, 12}, {32, 12},

                {82, 20}, {83, 20}, {84, 20}, {85, 20},
                {82, 21}, {83, 21}, {84, 21}, {85, 21},
                {82, 22}, {83, 22}, {84, 22}, {85, 22},
                {82, 23}, {83, 23}, {84, 23}, {85, 23},

                {69, 49}, {70, 49}, {71, 49}, {72, 49},
                {69, 50}, {70, 50}, {71, 50}, {72, 50},
                {69, 51}, {70, 51}, {71, 51}, {72, 51},
                {69, 52}, {70, 52}, {71, 52}, {72, 52},

                {52, 49}, {53, 49}, {54, 49}, {55, 49},
                {52, 50}, {53, 50}, {54, 50}, {55, 50},
                {52, 51}, {53, 51}, {54, 51}, {55, 51},
                {52, 52}, {53, 52}, {54, 52}, {55, 52},

                {26, 49}, {27, 49}, {28, 49}, {29, 49},
                {26, 50}, {27, 50}, {28, 50}, {29, 50},
                {26, 51}, {27, 51}, {28, 51}, {29, 51},
                {26, 52}, {27, 52}, {28, 52}, {29, 52}

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
