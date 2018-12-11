package jeuxV1;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Tools {
	public static double getRandom(double min, double max){
	    double x = (Math.random()*((max-min)+1))+min;
	    return x;
	}
	public static void SoundBalleShot(){
		try {
	          File soundFile = new File("sound/shot1.wav"); 
	          AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);              
	          Clip clip = AudioSystem.getClip();
	          clip.open(audioIn);
	          clip.start();
		} catch (UnsupportedAudioFileException e) {
	    	  e.printStackTrace();
	    } catch (IOException e) {
	    	  e.printStackTrace();
	    } catch (LineUnavailableException e) {
	    	  e.printStackTrace();
	    }
	}
	public static void SoundBoom(){
		try {
	          File soundFile = new File("sound/boom.wav"); 
	          AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);              
	          Clip clip = AudioSystem.getClip();
	          clip.open(audioIn);
	          clip.start();
		} catch (UnsupportedAudioFileException e) {
	    	  e.printStackTrace();
	    } catch (IOException e) {
	    	  e.printStackTrace();
	    } catch (LineUnavailableException e) {
	    	  e.printStackTrace();
	    }
	}
}
