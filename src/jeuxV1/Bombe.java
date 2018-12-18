package jeuxV1;

import java.io.FileInputStream;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bombe extends GraphicObject {

	private Point2D direction = new Point2D(0, 0);
	
	public Bombe(Monster m){
		Image image = null;
		try {
			image = new Image(new FileInputStream("photosJeu/bombe4.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		corps = new ImageView(image);
		((ImageView)corps).setX(0);
		((ImageView)corps).setY(0);
		((ImageView)corps).setFitWidth(19);
		((ImageView)corps).setFitHeight(19);
		corps.setTranslateX(m.getCorps().getTranslateX()+10);
		corps.setTranslateY(m.getCorps().getTranslateY()+10);
		updateDirection();
	}

	private void updateDirection(){
		Point2D p;
		double x = Math.cos(Math.toRadians(-270));
		double y = Math.sin(Math.toRadians(-270));
		p = new Point2D(x, y);
		direction = p.normalize().multiply(7);
	}
	
	public void update(){
		corps.setTranslateX(corps.getTranslateX() + direction.getX());
		corps.setTranslateY(corps.getTranslateY() + direction.getY());
	}
	
	
}
