package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.GamePanel;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed, dashPressed,
            enterPressed, dialoguePressed, fPressed, kPressed;
    // K is for attack
    public boolean checkDrawTime = false;

    GamePanel gp;
    public KeyHandler(GamePanel gp){
            this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); //return key number that's pressed
        //        if(code == keyEvent.SHIFT){
//            dashPressed = false;
//        }
        if(gp.gameState==gp.playState) {
            switch (code) {
                case KeyEvent.VK_W:
                    upPressed = true;
                    break;
                case KeyEvent.VK_A:
                        leftPressed = true;
                        break;
                case KeyEvent.VK_S:
                        downPressed = true;
                        break;
                case KeyEvent.VK_D:
                        rightPressed = true;
                        break;
                case KeyEvent.VK_K:
                    kPressed = true;
                    break;
                case KeyEvent.VK_F:
                    fPressed = true;
                    break;
                case KeyEvent.VK_O:
                    checkDrawTime = true;
                    break;
                case KeyEvent.VK_ESCAPE:
                    gp.gameState=gp.pauseState;
                    break;
                }
            }
            else if(gp.gameState==gp.dialogueState){
                    switch(code){
                        case KeyEvent.VK_F:
                        gp.gameState = gp.playState;
                        break;
                    }
                }
            else if(gp.gameState==gp.pauseState) {
                if (code == KeyEvent.VK_ESCAPE) {
                    gp.gameState = gp.playState;
                }
            }
            else if(gp.gameState==gp.titleState) {
            switch (code) {
                case KeyEvent.VK_W:
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum<0){
                        gp.ui.commandNum = 3;
                    }
                    break;
                case KeyEvent.VK_S:
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum >3)
                    {
                        gp.ui.commandNum = 0;
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    switch (gp.ui.commandNum){
                        case 0:
                        //New game
                            gp.gameState = gp.playState;
                            gp.playMusic(6);
                            break;
                        case 1:
                            //Load game later hehe
                            break;
                        case 2:
                            //settings later
                            break;
                        case 3:
                            //exit
                            System.exit(0);
                            break;
                    }
                    break;

            }
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode(); //return key number that's pressed
        switch (code) {
            case KeyEvent.VK_W -> upPressed = false;
            case KeyEvent.VK_A -> leftPressed = false;
            case KeyEvent.VK_S -> downPressed = false;
            case KeyEvent.VK_D -> rightPressed = false;
            case KeyEvent.VK_K -> kPressed = false;
            case KeyEvent.VK_O -> checkDrawTime = false;
//  case KeyEvent.VK_SHIFT -> dashPressed = false; // Uncomment if dashPressed is used
        }


    }
}
