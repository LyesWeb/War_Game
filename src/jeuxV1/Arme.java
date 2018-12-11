package jeuxV1;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Arme {
private Rectangle corps = new Rectangle(-6,0,10,50);
private Circle sortie = new Circle(0,0,5);

public Arme(GraphicObject player){
	corps.setTranslateX(player.getCorps().getTranslateX());
	corps.setTranslateY(player.getCorps().getTranslateY());
	corps.setFill(Color.GOLD);
	sortie.setFill(Color.GOLD);
	updateSortie();
}


public void attachToPlayer(Player player){
	corps.setTranslateX(player.getCorps().getTranslateX());
	corps.setTranslateY(player.getCorps().getTranslateY());
	updateSortie();

}

public void updateSortie(){
	sortie.setTranslateX(corps.getTranslateX());
	sortie.setTranslateY(corps.getTranslateY()+25);

}
public Rectangle getCorps() {
	return corps;
}
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
