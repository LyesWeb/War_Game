package jeuxV1;

import java.io.FileInputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Arme extends GraphicObject{
	//private Rectangle corps = new Rectangle(-6,0,10,50);
	private Circle sortie = new Circle(0,0,2);
	
	public Arme(GraphicObject player){
		Image image = null;
		try {
			image = new Image(new FileInputStream("photosJeu/arme.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		corps = new ImageView(image);
		((ImageView)corps).setX(0);
		((ImageView)corps).setY(0);
		((ImageView)corps).setFitWidth(20);
		((ImageView)corps).setFitHeight(50);
		corps.setTranslateX(player.getCorps().getTranslateX()+50);
		corps.setTranslateY(player.getCorps().getTranslateY()+10);
		sortie.setFill(Color.GREEN);
		updateSortie();
	}
	
	public void attachToPlayer(Player player){
		corps.setTranslateX(player.getCorps().getTranslateX()+50);
		corps.setTranslateY(player.getCorps().getTranslateY()+10);
		updateSortie();
	
	}
	
	public void updateSortie(){
		sortie.setTranslateX(corps.getTranslateX()+9);
		sortie.setTranslateY(corps.getTranslateY()+21);
	
	}
	/*public Rectangle getCorps() {
		return corps;
	}*/
	public void setCorps(Rectangle corps) {
		this.corps = corps;
	}
	public Circle getSortie() {
		return sortie;
	}
	public void setSortie(Circle sortie) {
		this.sortie = sortie;
	}
	//sur f: tourner vers la droite 
	
	public void rotateRight(){
		corps.setRotate(corps.getRotate()-5);
	}
	
	//sur s: tourner vers la gauche
	
	public void rotateLeft(){
		corps.setRotate(corps.getRotate()+5);
	}
	
	public double getRotate(){
		return corps.getRotate()-90;
	}

}
