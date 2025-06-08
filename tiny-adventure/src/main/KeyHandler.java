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

    public KeyHandler(GamePanel gp) {
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
        switch (gp.gameState) {
            //playState
            case 1 -> {
                switch (code) {
                    case KeyEvent.VK_W -> upPressed = true;
                    case KeyEvent.VK_A -> leftPressed = true;
                    case KeyEvent.VK_S -> downPressed = true;
                    case KeyEvent.VK_D -> rightPressed = true;
                    case KeyEvent.VK_K -> kPressed = true;
                    case KeyEvent.VK_F -> fPressed = true;
                    case KeyEvent.VK_O -> checkDrawTime = true;
                    case KeyEvent.VK_R ->
                            {
                                if (gp.currentMap==0) {
                                     gp.tileM.loadMap("/maps/map2/map2.txt", 0);
                                }else if(gp.currentMap==1) {
                                    gp.tileM.loadMap("/map/map2/map2_second.txt",1);}

                            }
                    case KeyEvent.VK_F1 -> gp.gameState = gp.pauseState;
                    case KeyEvent.VK_ESCAPE -> gp.gameState = gp.optionsState;
                }
            }
            //dialoge state
            case 3 -> {
                if (code == KeyEvent.VK_F) {
                    gp.gameState = gp.playState;
                }
            }
            //option state
            case 5 -> optionsState(code);
            //pause state
            case 2 -> {
                if (code == KeyEvent.VK_F1) {
                    gp.gameState = gp.playState;
                }
            }
            //GameOverSCreen
            case 6 -> {
                gameOverState(code);
            }
            // TITLE SCREEN
            case 0 -> {
                switch (code) {
                    case KeyEvent.VK_W -> {

                        gp.ui.commandNum--;
                        if (gp.ui.commandNum < 0) gp.ui.commandNum = 3;
                    }
                    case KeyEvent.VK_S -> {
                        gp.ui.commandNum++;
                        if (gp.ui.commandNum > 3) gp.ui.commandNum = 0;
                    }
                    case KeyEvent.VK_ENTER -> {
                        switch (gp.ui.commandNum) {
                            case 0 -> {
                                gp.gameState = gp.playState;
                                gp.playMusic(6);
                            }
                            case 1 -> {
                                // Load game later
                            }
                            case 2 -> {
                                // Settings later
                            }
                            case 3 -> System.exit(0);
                        }
                    }
                }
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

    public void optionsState(int code) {
        int maxCommandNum = 0;
        switch (gp.ui.subState) {
            case 0 -> maxCommandNum = 5;
//                case 1 -> maxCommandNum=5;
//                case 2 -> maxCommandNum=5;
            case 2 -> maxCommandNum = 1;

        }
        switch (code) {
            case KeyEvent.VK_ESCAPE -> gp.gameState = gp.playState;
            case KeyEvent.VK_ENTER -> enterPressed = true;
            case KeyEvent.VK_W -> {
                gp.ui.commandNum--;
                gp.playSoundEffect(9);
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = maxCommandNum;
                }
            }
            case KeyEvent.VK_S -> {
                gp.ui.commandNum++;
                gp.playSoundEffect(9);
                if (gp.ui.commandNum > maxCommandNum) {
                    gp.ui.commandNum = 0;
                }
            }
            case KeyEvent.VK_A -> {
                if (gp.ui.subState == 0) {
                    if (gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
                        gp.music.volumeScale--;
                        gp.music.checkVolume();
                        gp.playSoundEffect(10);
                    } else if (gp.ui.commandNum == 2 && gp.sound_effect.volumeScale > 0) {
                        gp.sound_effect.volumeScale--;
                        gp.playSoundEffect(10);
                    }
                }
            }
            case KeyEvent.VK_D -> {
                if (gp.ui.subState == 0) {
                    if (gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
                        gp.music.volumeScale++;
                        gp.music.checkVolume();
                        gp.playSoundEffect(10);
                    } else if (gp.ui.commandNum == 2 && gp.sound_effect.volumeScale < 5) {
                        gp.sound_effect.volumeScale++;
                        gp.playSoundEffect(10);
                    }
                }
            }


        }

    }

    public void gameOverState(int code) {
        switch (code) {
            case KeyEvent.VK_W -> {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 1;
                }
                gp.playSoundEffect(10);
            }
            case KeyEvent.VK_S -> {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 1) {
                    gp.ui.commandNum = 0;
                }
                gp.playSoundEffect(10);
            }
            case KeyEvent.VK_ENTER -> {
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                } else if (gp.ui.commandNum == 1) {
                    gp.gameState = gp.titleState;
                }

            }
        }
    }
}