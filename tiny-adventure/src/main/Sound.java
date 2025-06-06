package main;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;


public class Sound {
    Clip clip ;
    URL[] soundURL = new URL[30];
    public Sound(){
//        soundURL[0]= getClass().getResource("/effects/chest_opening.wav");
//        soundURL[1]= getClass().getResource("/effects/item_equip.wav");
//        soundURL[2]= getClass().getResource("/effects/item_pick_up.wav");
//        soundURL[3]= getClass().getResource("/effects/main_door_opening_closing.wav");
//        soundURL[4]= getClass().getResource("/effects/pick_up_potion.wav");
//        soundURL[5]= getClass().getResource("/effects/pick_up_sfx.wav");
        soundURL[0]=getClass().getResource("/effects/pick.wav");
        soundURL[1]=soundURL[0];
        soundURL[2]=soundURL[0];
        soundURL[3]=soundURL[0];
        soundURL[4]=soundURL[0];
        soundURL[5]=soundURL[0];

        soundURL[6]= getClass().getResource("/effects/music/uther_lust.wav");
        soundURL[7]=getClass().getResource("/effects/battle/sword_swing.wav");
        soundURL[8]=getClass().getResource("/effects/battle/playerhit_soft.wav");
        soundURL[9]=getClass().getResource("/effects/battle/mushroom_hit.wav");
    }
    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            AudioFormat format = ais.getFormat();
            System.out.println(format);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
