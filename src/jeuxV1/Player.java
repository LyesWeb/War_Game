package jeuxV1;

import java.io.FileInputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends GraphicObject {

	public Player(Zone zone){
		Image image = null;
		try {
			image = new Image(new FileInputStream("photosJeu/player.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		corps = new ImageView(image);
		((ImageView)corps).setX(0);
		((ImageView)corps).setY(0);
		
		// doit appartenir au zone
		double x = zone.getX1() + (zone.getX2()-zone.getX1())*Math.random();
		double y = zone.getY1() + (zone.getY2()-zone.getY1())*Math.random();
		corps.setTranslateX(x);
		corps.setTranslateY(y);
		
	}
	
	
	
}
