package model.monster;
import model.entity.Entity;
import controller.GamePanel;
import model.object.OBJ_Gold;
import model.object.OBJ_Key;
import model.object.OBJ_Potion_Heath_Two;

import java.util.Random;
public class MON extends Entity{
    GamePanel gp;
    public MON(GamePanel gp) {
        super(gp);
        this.gp = gp;
        getDyingImages();

    }

    public void setAction() {


        if (onPath) {
            //Check if it stops chasing
            checkStopChasingOrNot(gp.player, 10, 100);

            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
        } else {
            //Check if it starts chasing
            checkStartChasingOrNot(gp.player, 5, 100);

            //Get a random direction
            getRandomDirection(120);
        }

        //Check if it is attacks
        if (attacking == false) {
            checkAttackOrNot(30, gp.tileSize * 4, gp.tileSize);
        }
    }


}
