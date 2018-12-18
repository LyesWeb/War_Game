package jeuxV1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Man extends GraphicObject{
	
	public Man(Zone zone){
		Image image=null;
		try {
			Image[] imgs = new Image[3];
			imgs[0] = new Image(new FileInputStream("photosJeu/men1.gif"));
			imgs[1] = new Image(new FileInputStream("photosJeu/men2.gif"));
			image = imgs[(int) Tools.getRandom(0, 2)];
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		corps=new ImageView(image);
		((ImageView)corps).setX(0);
		((ImageView)corps).setY(0);
		

		((ImageView)corps).setFitHeight(50);
		((ImageView)corps).setFitWidth(80);
		
		corps.setTranslateX(10);
		corps.setTranslateY(Tools.getRandom(zone.getY1(),zone.getY2()));
		
	}
	
}
