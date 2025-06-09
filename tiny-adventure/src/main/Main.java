package main;

import javax.swing.JFrame;

public class Main {
    public static JFrame window;
    public static void main(String[] args){
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        window.setTitle("tiny knight");
        window.setUndecorated(true);
        GamePanel gamePanel = new GamePanel(); //add game panel
        window.add(gamePanel);

        GamePanel.config.loadConfig();
        window.pack();

        window.setLocationRelativeTo(null); //display window at center
        window.setVisible(true);


        gamePanel.setupGame();

        gamePanel.startGameThread();

        //include game loop
    }
}

