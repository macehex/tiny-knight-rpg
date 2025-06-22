package model;

import controller.GamePanel;
import model.entity.Entity;


public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.getTileSize();
        int entityRightCol = entityRightWorldX / gp.getTileSize();
        int entityTopRow = entityTopWorldY / gp.getTileSize();
        int entityBottomRow = entityBottomWorldY / gp.getTileSize();

        int tileNum1 = 0, tileNum2 = 0;

        // checking direction collision
        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.getTileSize();
                tileNum1 = gp.tileM.mapTileNum[gp.getCurrentMap()][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.getCurrentMap()][entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;

                }
                break;
            case "down":
                entityBottomRow = ((entityBottomWorldY + entity.speed ) / gp.getTileSize());
                tileNum1 = gp.tileM.mapTileNum[gp.getCurrentMap()][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.getCurrentMap()][entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = ((entityLeftWorldX - entity.speed) / gp.getTileSize());
                tileNum1 = gp.tileM.mapTileNum[gp.getCurrentMap()][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.getCurrentMap()][entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.getTileSize();
                tileNum1 = gp.tileM.mapTileNum[gp.getCurrentMap()][entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.getCurrentMap()][entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;
        for (int i = 0; i < gp.obj[1].length; i++) {
            if (gp.obj[gp.getCurrentMap()][i] != null) {
                //get model.entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //get the object's solid area position

                gp.obj[gp.getCurrentMap()][i].solidArea.x = gp.obj[gp.getCurrentMap()][i].worldX + gp.obj[gp.getCurrentMap()][i].solidArea.x;

                gp.obj[gp.getCurrentMap()][i].solidArea.y = gp.obj[gp.getCurrentMap()][i].worldY + gp.obj[gp.getCurrentMap()][i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                if (entity.solidArea.intersects((gp.obj[gp.getCurrentMap()][i].solidArea))) {
                    if (gp.obj[gp.getCurrentMap()][i].collision) {
                        entity.collisionOn = true;
                    }
                    if (player) {
                        index = i;
                        // else do nothing
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[gp.getCurrentMap()][i].solidArea.x = gp.obj[gp.getCurrentMap()][i].solidAreaDefaultX;
                gp.obj[gp.getCurrentMap()][i].solidArea.y = gp.obj[gp.getCurrentMap()][i].solidAreaDefaultY;

            }
        }
        return index;
    }

    public int checkEntity(Entity entity, Entity[][] target) {
        int index = 999;
        for (int i = 0; i < target[1].length; i++) {
            if (target[gp.getCurrentMap()][i] != null) {
                //get model.entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //get the object's solid area position
                target[gp.getCurrentMap()][i].solidArea.x = target[gp.getCurrentMap()][i].worldX + target[gp.getCurrentMap()][i].solidArea.x;
                target[gp.getCurrentMap()][i].solidArea.y = target[gp.getCurrentMap()][i].worldY + target[gp.getCurrentMap()][i].solidArea.y;

                switch (entity.direction) {
                    case "up": entity.solidArea.y -= entity.speed;
                        break;
                    case "down": entity.solidArea.y += entity.speed;
                        break;
                    case "left": entity.solidArea.x -= entity.speed;
                        break;
                    case "right": entity.solidArea.x += entity.speed;
                        break;
                }
                if(entity.solidArea.intersects(target[gp.getCurrentMap()][i].solidArea)){
                    if(target[gp.getCurrentMap()][i]!=entity){
                        entity.collisionOn = true;
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[gp.getCurrentMap()][i].solidArea.x = target[gp.getCurrentMap()][i].solidAreaDefaultX;
                target[gp.getCurrentMap()][i].solidArea.y = target[gp.getCurrentMap()][i].solidAreaDefaultY;
            }
        }
        return index;
    }
    public boolean checkPlayer(Entity entity){
        boolean contactPlayer = false;
        //check if npc is colliding with player ?

        //get model.entity's solid area position
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        //get the object's solid area position
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                break;
        }
        if (entity.solidArea.intersects((gp.player.solidArea))) {
            entity.collisionOn = true;
            contactPlayer = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        return  contactPlayer;
    }
}