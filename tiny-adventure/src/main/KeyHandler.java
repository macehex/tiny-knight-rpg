package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.GamePanel;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed, dashPressed,
            enterPressed, dialoguePressed, fPressed;
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

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode(); //return key number that's pressed
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;

        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
//        if(code == keyEvent.SHIFT){
//            dashPressed = false;
//        }
        if(code == KeyEvent.VK_O){
            checkDrawTime = false;
        }

    }
}
