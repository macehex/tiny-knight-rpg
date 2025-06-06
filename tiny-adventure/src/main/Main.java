package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("tiny knight");

        GamePanel gamePanel = new GamePanel(); //add game panel
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null); //display window at center
        window.setVisible(true);


        gamePanel.setupGame();

        gamePanel.startGameThread();

        //include game loop
    }
}
