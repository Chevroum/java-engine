package main;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound{

    Clip clip;
    File soundURL[] = new File[30];

    public Sound(){

        soundURL[0] = new File("res/sound/BlueBoyAdventure.wav");
        soundURL[1] = new File("res/sound/coin.wav");
        soundURL[2] = new File("res/sound/powerup.wav");
        soundURL[3] = new File("res/sound/unlock.wav");
        soundURL[4] = new File("res/sound/fanfare.wav");
    }

    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch(Exception e){
            
        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY); // loops indefinitly, loop counts the number of times the sound needs to be played
    }
    public void stop(){
        clip.stop();
    }

}