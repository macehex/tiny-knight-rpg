package model.monster;
import model.entity.Entity;
import controller.GamePanel;
import java.util.Random;
public class MON extends Entity{
    GamePanel gp;
    public MON(GamePanel gp) {
        super(gp);
        this.gp = gp;
        getDyingImages();

    }
    public void setAction() {
        //set NPC behavior & AI
        if (onPath) {
            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;

            searchPath(goalCol, goalRow);

        } else {

            actionLockCounter++; //increment everytime setAction is called
            if (actionLockCounter == 120) {
//                  the action direction stay the same for 120 seconds
//                randomize npc state
                Random random = new Random();
                int i = random.nextInt(100) + 1; // pick up num in range[1,100]
                if (i <= 25) {
                    direction = "up";
                }
                if (i > 25 && i <= 50) {
                    direction = "down";
                }
                if (i > 50 && i <= 75) {
                    direction = "left";
                }
                if (i > 75) {
                    direction = "right";
                }
                actionLockCounter = 0; // reset counter
            }

        }

    }
    public void update(){
        super.update();
        //check distance between player and slime
        tileDistance =  getTileDistance();
        if(!onPath && tileDistance<4){
            int i = new Random().nextInt(100)+1;
            if(i>50){
                onPath = true;
            }
        }
        checkStopChasingOrNot(10);
    }

}
