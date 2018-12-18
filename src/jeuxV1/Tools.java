package jeuxV1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tools {
	public static double getRandom(double min, double max){
	    double x = (Math.random()*((max-min)+1))+min;
	    return x;
	}
	public static ImageView createImageView(String path) throws FileNotFoundException{
		 Image img = new Image(new FileInputStream(path));
	     // simple displays ImageView the image as is
	     ImageView imgview = new ImageView();
	     imgview.setImage(img);
	  return imgview;
	}
	public static void SoundBalleShot(){
		try {
	          File soundFile = new File("sound/shot2.wav"); 
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
	public static void SoundPlayerDead(){
		try {
	          File soundFile = new File("sound/dead.wav"); 
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
	public static void SoundBgCrow(){
		try {
	          File soundFile = new File("sound/crow.wav"); 
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
	public static void SoundBgCardriving(){
		try {
	          File soundFile = new File("sound/cardriving.wav"); 
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
