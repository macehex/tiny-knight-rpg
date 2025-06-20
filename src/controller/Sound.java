package controller;
import javax.sound.sampled.*;
import java.net.URL;


public class Sound {
    Clip clip ;
    URL[] soundURL = new URL[30];
    FloatControl fc;
    public int volumeScale = 3;
    float volume;
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

        soundURL[6]= getClass().getResource("/effects/music/chiptune-theme.wav");
        soundURL[7]=getClass().getResource("/effects/battle/sword_swing.wav");
        soundURL[8]=getClass().getResource("/effects/battle/playerhit_soft.wav");
        soundURL[9]=getClass().getResource("/effects/battle/mushroom_hit.wav");

        soundURL[10] = getClass().getResource("/effects/ui/button_pressed.wav");
        soundURL[11]=getClass().getResource("/effects/game_over.wav");
        soundURL[12] = getClass().getResource("/effects/teleport.wav");
        soundURL[13] = getClass().getResource("/effects/battle/scream.wav");
    }
    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
            AudioFormat format = ais.getFormat();
            System.out.println(format);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void checkVolume(){
        switch (volumeScale){
            case 0 -> volume = -80f;

            case 1 -> volume = -20f;
            case 2 -> volume = -12f;
            case 3 -> volume = -5f;
            case 4 -> volume = 1f;
            case 5 -> volume = 6f;
        }
        fc.setValue(volume);
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
