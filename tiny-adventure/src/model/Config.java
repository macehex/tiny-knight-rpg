package model;

import controller.GamePanel;

import java.io.*;

public class Config {
    GamePanel gp;
    public Config(GamePanel gp){
        this.gp = gp;
    }
    public void saveConfig(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
            //FULL SCREEN
            if(gp.fullScreenOn){
                bw.write("On");
            }else{
                bw.write("Off");
            }
            bw.newLine();
            // Save music volume
            bw.write(String .valueOf(gp.music.volumeScale));
            bw.newLine();
           //sound
            bw.write(String.valueOf(gp.sound_effect.volumeScale));
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadConfig(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));
            String s = br.readLine();
            // read the next line
//music
            s = br.readLine();
            gp.music.volumeScale = Integer.parseInt(s);

            //sound eff
            s = br.readLine();
            gp.sound_effect.volumeScale = Integer.parseInt(s);

            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
