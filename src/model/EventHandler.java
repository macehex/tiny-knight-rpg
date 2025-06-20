package model;


import controller.GamePanel;

public class EventHandler {
    GamePanel gp;
    EventRect[][][] eventRect;
    int previousEventX, previousEventY;
    boolean canTouchEvent = true; //prevent from happening repeatedly
    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        int col = 0 ;
        int row = 0 ;
        int map = 0 ;
        while(map< gp.maxMap&&col<gp.maxWorldCol&&row<gp.maxWorldRow){
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x= 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
            col++;
            if(col ==gp.maxWorldCol){
                col=0;
                row++;
                if(row == gp.maxWorldRow){
                    row = 0;
                    map++;
                }
            }
        }
    }

    public void checkEvent() {
        //if player is more than one tile away from the last event
        int xDistance= Math.abs(gp.player.worldX - previousEventX);
        int yDistance= Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance,yDistance);
        if(distance > gp.tileSize){
            canTouchEvent = true;
        }
        if(canTouchEvent){
            if (hit(0,19, 1,"any")) {
                //event hit happens
                damagePit(gp.dialogueState);
            }
            else if(hit(0,13,43,"any")){teleport(1,64,22);}
            else if(hit(1,64,22,"any")){teleport(0,13,43);}

        }
    }

    public boolean hit(int map, int col, int row, String reqDirection) {
        boolean hit = false;
        //Check to see if the passed map is current map
        if(map == gp.currentMap){

            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row])&& !eventRect[map][col][row].eventDone) {
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }


            }
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;

            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }
        return hit;
    }
    public void teleport(int map, int col, int row ){
        gp.currentMap = map;
        gp.player.worldX = col*gp.tileSize;
        gp.player.worldY = row*gp.tileSize;
        previousEventX =gp.player.worldX;
        previousEventY =gp.player.worldY;
        canTouchEvent = false;
        gp.playSoundEffect(12);
    }
    public void damagePit(int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Damaged";
        gp.playSoundEffect(8);
        System.out.println("player in damage pit");
        gp.player.life -= 1;
//        eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }

}
