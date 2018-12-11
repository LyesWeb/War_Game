package jeuxV1;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.*;
import javax.sound.sampled.*;

public class Program extends Application {
	private GridPane  gp = new GridPane();
	private int nb = 0;
	private int x = 0;
	private Text nbal = new Text("   |   Monstres tués : "+nb);
	private Text nmonst= new Text("Balles tires : " + x);
	
	// timer
	private int s = 0;
	private int m = 0;
	
	private Text time = new Text("   |   Time : "+m+" : "+s);
	
	//Les élements de l'interface graphique
	private double widthWindow=800;
	private double heightWindow=600;
	private Pane container=new Pane();
	Line line = new Line(0, 200, widthWindow, 200);
	Zone zone1=new Zone(0,20,line.getEndX()-50,line.getEndY()-50);
	Zone zone2=new Zone(line.getStartX(),line.getStartY(),line.getEndX()-50,heightWindow-50);
	//les objets du jeu
	private Player player = new Player(zone2);
	private List<Monster> monsters = new ArrayList<>();
	private List<Balle> balles = new ArrayList<>();
	Arme arme = new Arme(player);

	private List<Bombe> bombes = new ArrayList<>();
	
	public Rectangle porte = new Rectangle(widthWindow-26, 350, 10, 90);
	private List<Man> mans = new ArrayList<>();
	
	//AnimationTimer
	AnimationTimer animation=new AnimationTimer(){

		@Override
		public void handle(long now) {
			refreshContent();
		}
		
	};
	
	//event Handler
	
	EventHandler<KeyEvent> event = new EventHandler<KeyEvent>(){

		@Override
		public void handle(KeyEvent event) {
			
			if(event.getCode()==KeyCode.F){
				arme.rotateLeft();
				
			}
			if(event.getCode()==KeyCode.S){
				arme.rotateRight();
			}
			if(event.getCode()==KeyCode.SPACE){
				Balle balle = new Balle(arme);
				container.getChildren().add(balle.getCorps());
				balles.add(balle);
				nb++;
				nmonst.setText("Balles tires : "+nb);
				Tools.SoundBalleShot();
			}
			if(event.getCode()==KeyCode.UP){
				player.getCorps().setTranslateY(player.getCorps().getTranslateY()-5);
				arme.attachToPlayer(player);
			}
			if(event.getCode()==KeyCode.DOWN){
				player.getCorps().setTranslateY(player.getCorps().getTranslateY()+5);
				arme.attachToPlayer(player);
			}
			
			if(event.getCode()==KeyCode.LEFT){
				player.getCorps().setTranslateX(player.getCorps().getTranslateX()-5);
				arme.attachToPlayer(player);

			}
			if(event.getCode()==KeyCode.RIGHT){
				player.getCorps().setTranslateX(player.getCorps().getTranslateX()+5);
				arme.attachToPlayer(player);


			}
		}
		
	};
	
	private void refreshContent(){
		
		//parcourir la collection des balles : pour mettre a jour leur position
		
		for(Balle balle:balles){
			for(Monster monstre:monsters){
				if(balle.touch(monstre)){
					container.getChildren().removeAll(balle.getCorps(),monstre.getCorps());
					balle.setAlive(false);
					monstre.setAlive(false);
					x++;
					nbal.setText("   |   Monstres tués : " + x);
				}
			}
		}
		
		for(Bombe b:bombes){
			for(Man m:mans){
				if(b.touch(m)){
					container.getChildren().removeAll(b.getCorps(),m.getCorps());
					b.setAlive(false);
					m.setAlive(false);
					Tools.SoundBoom();
				}
			}
		}
		
		monsters.removeIf(GraphicObject::isDead);
		balles.removeIf(GraphicObject::isDead);
		mans.removeIf(GraphicObject::isDead);
		bombes.removeIf(GraphicObject::isDead);
		mans.removeIf(GraphicObject::isDead);

		
		for(Balle balle:balles){
			balle.update();
		}
		if(Math.random()<0.01){
			Monster m=new Monster(zone1);
			container.getChildren().add(m.getCorps());
			monsters.add(m);
		}
		
		if(Math.random()<0.0099) {
			Man man = new Man(zone2);
			container.getChildren().add(man.getCorps());
			mans.add(man);
			man.getCorps().setTranslateX(man.getCorps().getTranslateX()+Math.random()*0.9);
			if(Math.random()<0.1) {
				man.getCorps().setTranslateY(man.getCorps().getTranslateY()-Math.random()*7);
			}
		}
		for(Man r:mans){
			r.getCorps().setTranslateX(r.getCorps().getTranslateX()+Math.random()*0.9+Math.random());
			if(r.getCorps().getTranslateY()>350) {
				r.getCorps().setTranslateY(r.getCorps().getTranslateY()-0.19);
			}else {
				r.getCorps().setTranslateY(r.getCorps().getTranslateY()+0.19);
			}
			// remove Man if X > Window width
			if(r.getCorps().getTranslateX()>widthWindow-40) {
				container.getChildren().remove(r.getCorps());
				r.setAlive(false);
			}
		}
		if(Math.random() < 0.08)
		for(Monster monster:monsters){
			if(Math.random() < 0.009) {
				Bombe b = new Bombe(monster);
				container.getChildren().add(b.corps);
				bombes.add(b);
			}
		}
		
		for(Bombe bo:bombes){
			bo.update();
		}
		
	}
	public static void main(String[] args) {
		Application.launch();

	}

	private void createContent(){
		container.getChildren().add(gp);
		container.getChildren().add(line);
		porte.setFill(Color.BROWN);
		container.getChildren().add(porte);
		container.getChildren().add(player.getCorps());
		container.getChildren().add(arme.getCorps());
		container.getChildren().add(arme.getSortie());
	}
	
	class topBar extends HBox {
        public topBar() {
        	nbal.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
        	nbal.setFill(Color.WHITE);
        	nmonst.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
        	nmonst.setFill(Color.WHITE);
        	time.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
        	time.setFill(Color.WHITE);
            this.getChildren().addAll(nmonst, nbal, time);
            this.setStyle("-fx-padding: 5 0 5 10;-fx-background-color: #7fb0ff;");
            this.setMinWidth(widthWindow);
        }
    }
	
	@Override
	public void start(Stage window) throws Exception {
		window.setWidth(widthWindow);
		window.setHeight(heightWindow);
		window.setTitle("jeu de guerre");
		createContent();		
		
		HBox toolBar = new HBox();
		toolBar.setAlignment(Pos.CENTER_RIGHT);
        toolBar.setPrefHeight(25);
        toolBar.setMinHeight(25);
        toolBar.setMaxHeight(25);
        toolBar.getChildren().add(new topBar());
        container.getChildren().add(toolBar);
		
		Scene scene=new Scene(container);
		window.setScene(scene);
		animation.start();
		scene.setOnKeyPressed(event);
		window.show();
	}

}
