package ai;

import main.GamePanel;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;

public class PathFinder {
    GamePanel gp;
    Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0 ;
    public PathFinder(GamePanel gp){
        this.gp = gp;
        instantiateNodes();
    }
    public void instantiateNodes(){
        node = new Node[gp.maxWorldCol][gp.maxWorldRow];
        int col=0;
        int row=0;
        while(col<gp.maxWorldCol && row < gp.maxWorldRow){
            node[col][row]= new Node(col,row);
            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }
    }
    public void resetNodes(){
        int col=0;
        int row=0;
        while(col<gp.maxWorldCol && row < gp.maxWorldRow){
            node[col][row].open = false;
            node[col][row].checked = false;
            node[col][row].solid = false;
            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }

        }
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;


    }
    public void setNode(int startCol, int StartRow, int goalCol,int goalRow, Entity entity){
        resetNodes();
        //set up start and goal node
        startNode = node[startCol][StartRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);
        int col = 0 ;
        int row = 0;
        while(col<gp.maxWorldCol && row < gp.maxWorldRow){

//            int tileNum = gp.tileM.mapTileNum[gp.currentMap][col][row];
            if(gp.tileM.tile[tileNum].collision){
                node[col][row].solid = true;
            }
            //check interactive tiles
//            for(int i  = 0,)
        }

    }
}
