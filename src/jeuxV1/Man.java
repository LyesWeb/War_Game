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
			imgs[0] = new Image(new FileInputStream("photosJeu/men.png"));
			imgs[1] = new Image(new FileInputStream("photosJeu/men1.png"));
			imgs[2] = new Image(new FileInputStream("photosJeu/men2.png"));
			image = imgs[(int) Tools.getRandom(0, 2)];
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		corps=new ImageView(image);
		((ImageView)corps).setX(0);
		((ImageView)corps).setY(0);
		

		((ImageView)corps).setFitHeight(43);
		((ImageView)corps).setFitWidth(43);
		
//		double x=0;
//		double y=zone.getY2()-zone.getY1()*Math.random();
		corps.setTranslateX(10);
		corps.setTranslateY(Tools.getRandom(zone.getY1(),zone.getY2()));
	
		
	}
	
}
