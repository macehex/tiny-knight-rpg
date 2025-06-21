package model;

import controller.GamePanel;
import model.entity.Entity;
import model.entity.NPC;
import model.entity.NPC_Princess;
import model.monster.MON_Shroom;
import model.monster.MON_Slime;
import model.object.*;

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
                //Ladder
                () -> new OBJ_Ladder(gp),

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
                {74, 44},
                {61, 44},
                {11, 22},
                {93, 18},
                // Ladder
                {64, 3},
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
                {15, 9},
                {12, 12},
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
        // Array of NPC coordinates for each map
        int[][][] npcCoords = {
                { // Map 0
                        {10, 30}
                },
                { // Map 1
                        {65, 4}
                }
        };

        // Array of NPC suppliers for each map
        Supplier<NPC>[] npcSuppliers = new Supplier[]{
                () -> new NPC(gp),          // Map 0 NPC type
                () -> new NPC_Princess(gp)  // Map 1 NPC type
        };

        // Loop through each map
        for (int mapNum = 0; mapNum < npcCoords.length; mapNum++) {
            for (int i = 0; i < npcCoords[mapNum].length; i++) {
                gp.npc[mapNum][i] = npcSuppliers[mapNum].get();
                gp.npc[mapNum][i].worldX = gp.tileSize * npcCoords[mapNum][i][0];
                gp.npc[mapNum][i].worldY = gp.tileSize * npcCoords[mapNum][i][1];
            }
        }
    }

    public void setMonster() {
        // Array of monster suppliers and their coordinates for each map
        Object[][][] mapMonsters = {
                { // Map 0
                        {new MON_Shroom(gp), 10, 10},
                        {new MON_Slime(gp), 11, 10},
                        {new MON_Slime(gp), 12, 10},
                        {new MON_Slime(gp), 13, 10},
                        {new MON_Shroom(gp), 14, 10},
                        {new MON_Shroom(gp), 10, 11},
                        {new MON_Shroom(gp), 30, 5},
                        {new MON_Shroom(gp), 12, 32},
                        {new MON_Shroom(gp), 65, 30},
                        {new MON_Shroom(gp), 52, 50},
                        {new MON_Shroom(gp), 74, 38},
                        {new MON_Shroom(gp), 41, 8}

                },
                { // Map 1
                }
        };

        // Loop through each map and its monsters
        for (int mapNum = 0; mapNum < mapMonsters.length; mapNum++) {
            for (int i = 0; i < mapMonsters[mapNum].length; i++) {
                gp.monster[mapNum][i] = (Entity) mapMonsters[mapNum][i][0];
                gp.monster[mapNum][i].worldX = gp.tileSize * (int) mapMonsters[mapNum][i][1];
                gp.monster[mapNum][i].worldY = gp.tileSize * (int) mapMonsters[mapNum][i][2];
            }
        }
    }

    public void setLadder() {
        // Array of ladder coordinates
        int[][] ladderCoords = {
                {14, 45}, {14, 49}, {14, 48}, {14, 47}, {14, 46},
                {57, 30},
                {54, 30}
        };

        // Ladder supplier
        Supplier<Entity> ladderSupplier = () -> new OBJ_LadderInWorld(gp);

        int mapNum = 0; // Assuming map 0

        // Loop through ladder coordinates and set them
        for (int i = 0; i < ladderCoords.length; i++) {
            gp.obj[mapNum][i] = ladderSupplier.get();
            gp.obj[mapNum][i].worldX = ladderCoords[i][0] * gp.tileSize;
            gp.obj[mapNum][i].worldY = ladderCoords[i][1] * gp.tileSize;
        }
    }

    public void setPotion() {
        int mapNum = 0;

        // Array of potion suppliers
        Supplier<Entity>[] potionSuppliers = new Supplier[]{
                () -> new OBJ_Potion_Strength(gp),
                () -> new OBJ_Potion_Speed(gp),
                () -> new OBJ_Potion_Heath_Two(gp),
                () -> new OBJ_Potion_Heath_Two(gp),
                () -> new OBJ_Potion_Heath_Two(gp),
                () -> new OBJ_Potion_Heath_Two(gp),
                () -> new OBJ_Potion_Heath_Two(gp),
                () -> new OBJ_Potion_Heath_Two(gp)
        };

        // Corresponding coordinates for each potion
        int[][] potionCoords = {
                {12, 30}, // Strength Potion
                {53, 28}, // Speed Potion

                {42, 7},  // Health Potion
                {9, 10},
                {15, 9},
                {12, 12},
                {61, 13},
                {92, 52},
                //Strength Potion
        };

        // Safety check: prevent mismatches
        if (potionSuppliers.length != potionCoords.length) {
            throw new IllegalStateException("Mismatch: " + potionSuppliers.length + " suppliers vs " + potionCoords.length + " coordinates.");
        }

        // Loop to create and place each potion
        for (int i = 0; i < potionSuppliers.length; i++) {
            gp.obj[mapNum][i] = potionSuppliers[i].get();
            gp.obj[mapNum][i].worldX = potionCoords[i][0] * gp.tileSize;
            gp.obj[mapNum][i].worldY = potionCoords[i][1] * gp.tileSize;
        }
    }

}
