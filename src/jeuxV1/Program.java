package jeuxV1;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Program extends Application {

	private double widthWindow = 800;
	private double heightWindow = 600;
	private Pane container = new Pane();
	Line line = new Line(0, 200, widthWindow, 200);
	Zone zone1 = new Zone(0, 0, line.getEndX()-50, line.getEndY()-50);
	Zone zone2 = new Zone(line.getStartX(), line.getStartY(), line.getEndX()-50, heightWindow-50);
	// les Objets du jeu
	Player player = new Player(zone2);
	private List<Monster> monsters = new ArrayList<>();
	private List<Balle> balles = new ArrayList<>();
	Arme arme = new Arme(player);
	
	// AnimationTimer
	AnimationTimer animation = new AnimationTimer() {
		@Override
		public void handle(long now) {
			refreshContent();
		}

	};
	
	// event
	EventHandler<KeyEvent> event = new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent event) {
			if(event.getCode() == KeyCode.X){
				arme.rotateLeft();
			}
			if(event.getCode() == KeyCode.Y){
				arme.rotateRight();
			}
			if(event.getCode() == KeyCode.SPACE){
				Balle balle = new Balle(arme);
				container.getChildren().add(balle.getCorps());
				balles.add(balle);
			}
			if(event.getCode() == KeyCode.UP){
				player.getCorps().setTranslateY(player.getCorps().getTranslateY()-5);
				arme.attachToPlayer(player);
			}
			if(event.getCode() == KeyCode.DOWN){
				player.getCorps().setTranslateY(player.getCorps().getTranslateY()+5);
				arme.attachToPlayer(player);
			}
			if(event.getCode() == KeyCode.LEFT){
				player.getCorps().setTranslateX(player.getCorps().getTranslateX()-5);
				arme.attachToPlayer(player);
			}
			if(event.getCode() == KeyCode.RIGHT){
				player.getCorps().setTranslateX(player.getCorps().getTranslateX()+5);
				arme.attachToPlayer(player);
			}
		}
		
	};
	
	public void refreshContent() {
		
		// parcourir la collection des balles : pour mettre a jour la position
		for(Balle balle:balles){
			for(Monster monster:monsters){
				if(balle.touch(monster)){
					container.getChildren().removeAll(balle.getCorps(), monster.getCorps());
					balle.setAlive(false);
					monster.setAlive(false);
				}
			}
		}

		monsters.removeIf(GraphicObject::isDead);
		balles.removeIf(GraphicObject::isDead);
		
		for(Balle balle:balles){
			balle.update();
		}
		
		if(Math.random() < 0.01){
			Monster m = new Monster(zone1);
			container.getChildren().add(m.corps);
			monsters.add(m);
		}
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	private void createContent(){
		container.getChildren().add(line);
		container.getChildren().add(player.corps);
		container.getChildren().add(arme.getCorps());
		container.getChildren().add(arme.getSortie());		
	}

	class TopBar extends HBox {

        public TopBar() {
            Text scoreText = new Text("Score : ");
            this.getChildren().addAll(scoreText);
        }
    }
	
	@Override
	public void start(Stage window) throws Exception {
		window.setWidth(widthWindow);
		window.setHeight(heightWindow);
		window.setTitle("Jeu de Guerre");
		createContent();
		
//		window.initStyle(StageStyle.UNDECORATED);
		BorderPane p = new BorderPane();
		HBox toolBar = new HBox();
		toolBar.setAlignment(Pos.CENTER_RIGHT);
        toolBar.setPrefHeight(25);
        toolBar.setMinHeight(25);
        toolBar.setMaxHeight(25);
        toolBar.getChildren().add(new TopBar());
		p.setTop(toolBar);
		container.getChildren().add(p);
		Scene scene = new Scene(container);
		window.setScene(scene);
		animation.start();
		
		scene.setOnKeyPressed(event);
		
		window.show();
		
	}

}
