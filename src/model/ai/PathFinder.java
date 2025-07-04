package model.ai;

import controller.GamePanel;

import java.util.ArrayList;

public class PathFinder {
    GamePanel gp;
    Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;

    public PathFinder(GamePanel gp) {
        this.gp = gp;
        instantiateNodes();
    }

    public void instantiateNodes() {
        node = new Node[gp.getMaxWorldCol()][gp.getMaxWorldRow()];
        int col = 0;
        int row = 0;
        while (col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {
            node[col][row] = new Node(col, row);
            col++;
            if (col == gp.getMaxWorldCol()) {
                col = 0;
                row++;
            }
        }
    }

    public void resetNodes() {
        int col = 0;
        int row = 0;
        while (col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {
            node[col][row].open = false;
            node[col][row].checked = false;
            node[col][row].solid = false;
            col++;
            if (col == gp.getMaxWorldCol()) {
                col = 0;
                row++;
            }

        }
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;


    }

    public void setNodes(int startCol, int StartRow, int goalCol, int goalRow) {
        resetNodes();
        //set up start and goal node
        startNode = node[startCol][StartRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);
        int col = 0;
        int row = 0;
        while (col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {

            int tileNum = gp.tileM.mapTileNum[gp.getCurrentMap()][col][row];
            if (gp.tileM.tile[tileNum].collision) {
                node[col][row].solid = true;
            }
            //check interactive view.tiles
//            for(int i  = 0,)
            getCost(node[col][row]);
            col++;
            if (col == gp.getMaxWorldCol()) {
                col = 0;
                row++;
            }
        }

    }

    public void getCost(Node node) {
        // G cost
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;
        // h Cost
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;
        // F cost
        node.fCost = node.gCost + node.hCost;

    }

    public boolean search() {
        while (!goalReached && step < 500) {
            int col = currentNode.col;
            int row = currentNode.row;

            //check the current node
            currentNode.checked = true;
            openList.remove(currentNode);
            //open the Up node
            if (row - 1 >= 0) {
                openNode(node[col][row - 1]);
            }
            // Open the left node
            if (col - 1 >= 0) {
                openNode(node[col - 1][row]);
            }
            // Open the down node
            if (row + 1 < gp.getMaxWorldRow()) {
                openNode(node[col][row + 1]);
            }
            // Open the right node
            if (col + 1 < gp.getMaxWorldCol()) {
                openNode(node[col + 1][row]);
            }
            // fnid the best node
            int bestNodeIndex = 0;
            int bestNodefCost = 999;
            for (int i = 0; i < openList.size(); i++) {
                //check if this node's F cost is better
                if (openList.get(i).fCost < bestNodeIndex) {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }
                // If F cost is equal, check the G cost
                else if (openList.get(i).fCost == bestNodefCost) {
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }
            // If there is no node in the openList, end the loop
            if (openList.isEmpty()) {
                break;
            }
            // After the loop, openList [bestNodeIndex] is the next step (= currentNode)
            currentNode = openList.get(bestNodeIndex);

            if (currentNode == goalNode) {
                goalReached = true;
                trackThePath();
            }
            step++;
        }
        return goalReached;
    }


    public void openNode(Node node) {
        if (!node.open && !node.checked && !node.solid) {
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }

    }

    public void trackThePath() {
        Node current = goalNode;
        while (current!=startNode){
            pathList.addFirst(current);
            current = current.parent;
        }
    }
}
