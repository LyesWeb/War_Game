package jeuxV1;

import java.io.FileInputStream;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Balle extends GraphicObject{
	
	private Point2D direction = new Point2D(0,0);
	
	
	public Balle(Arme arme){
		Image image = null;
		try {
			image = new Image(new FileInputStream("photosJeu/balle7.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		corps = new ImageView(image);
		((ImageView)corps).setX(0);
		((ImageView)corps).setY(0);
		((ImageView)corps).setFitWidth(6);
		((ImageView)corps).setFitHeight(6);
		corps.setTranslateX(arme.getSortie().getTranslateX()-7);
		corps.setTranslateY(arme.getSortie().getTranslateY());
		updateDirection(arme.getRotate());
	}
	
	private void updateDirection(double rotation){
		Point2D p;
		double x=Math.cos(Math.toRadians(rotation));
		double y=Math.sin(Math.toRadians(rotation));
		p=new Point2D(x,y);
		direction=p.normalize().multiply(9);
	}
	
	public void update(){
		corps.setTranslateX(corps.getTranslateX()+direction.getX());
		corps.setTranslateY(corps.getTranslateY()+direction.getY());

	}
}
