package jeuxV1;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.sun.jmx.snmp.tasks.Task;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Program extends Application {
	private GridPane  gp = new GridPane();
	public static int nbBallesTires = 0;
	public static int nbMonstresTues = 0;
	private int nbLife = 3;
	private Text txtMonstresTues = new Text(" Monstres tués ( " + nbBallesTires + " )");
	private Text txtBallesTires = new Text(" Balles tires ( " + nbMonstresTues + " )");
	private Text txtLife = new Text(" Life ( " + nbLife + " )");
	
	// timer
	public static int heurs = 00;
	public static int minutes = 00;
	public static int seconds = 00;
	
	private static Text time = new Text(" Time (" + heurs + ":" + minutes + ":" + seconds + " )");
	
	private Boolean isGameOver = false;

	//Les élements de l'interface graphique
	private double widthWindow=900;
	private double heightWindow=600;
	private Pane container=new Pane();
	Line line = new Line(0, 150, widthWindow, 150);
	Zone zone1=new Zone(0,20,line.getEndX()-50,line.getEndY()-50);
	Zone zone2=new Zone(line.getStartX(),line.getStartY(),line.getEndX()-50,heightWindow-50);
	//les objets du jeu
	private Player player = new Player(zone2);
	private List<Monster> monsters = new ArrayList<>();
	private List<Balle> balles = new ArrayList<>();
	Arme arme = new Arme(player);
	private List<Bombe> bombes = new ArrayList<>();
	
	public Rectangle porte = new Rectangle(widthWindow-23, 350, 7, 90);
	private List<Man> mans = new ArrayList<>();
	HBox toolBar = new HBox();
	
	public static Program p = null;
	
	//AnimationTimer
	AnimationTimer animation = new AnimationTimer(){
		@Override
		public void handle(long now) {
			if(isGameOver) {
				animation.stop();
				//container.getChildren().clear();
				VBox cc = new VBox();
				ImageView gameOver = null;
				try {
					gameOver = Tools.createImageView("photosJeu/gameOver1.png");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				ImageView replay = null;
				try {
					replay = Tools.createImageView("photosJeu/replay.png");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				replay.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				     @Override
				     public void handle(MouseEvent event) {
				    	 for(Monster monster:monsters){
					    	container.getChildren().remove(monster.getCorps());
					 	 }
				    	 for(Balle balle:balles){
					    	container.getChildren().remove(balle.getCorps());
					 	 }
				    	 for(Bombe bombe:bombes){
					    	container.getChildren().remove(bombe.getCorps());
					 	 }
				    	 for(Man man:mans){
					    	container.getChildren().remove(man.getCorps());
					 	 }
				    	 heurs = 0;
				    	 minutes = 0;
				    	 seconds = 0;
				    	 nbLife = 3;
				    	 monsters.clear();
				    	 balles.clear();
				    	 bombes.clear();
				    	 nbMonstresTues = 0;
				    	 txtMonstresTues.setText(" Monstres tués ( 0 )");
				    	 nbBallesTires = 0;
				    	 txtBallesTires.setText(" Balles tires ( 0 )");
				    	 txtLife.setText(" Time ( 0:0:0 )");
				    	 isGameOver = false;
				    	 container.getChildren().remove(cc);
				     	 animation.start();
				     }
				});
				replay.setFitWidth(50);
				replay.setFitHeight(50);
				replay.setLayoutY(replay.getLayoutY()+10);
				Text finalTime = new Text("Time ( " + heurs + ":" + minutes + ":" + seconds + " )");
				Text finalMonstre = new Text("Monstres tués (" + nbMonstresTues + " )");
				finalTime.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
				finalTime.setFill(Color.BLACK);
				Text finalBalles = new Text("Balles tires ( " + nbBallesTires + " )");
				finalMonstre.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
				finalMonstre.setFill(Color.BLACK);
				finalBalles.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
				finalBalles.setFill(Color.BLACK);
				cc.setPrefWidth(widthWindow);
				cc.setPrefHeight(heightWindow);
				cc.setAlignment(Pos.CENTER);
				cc.setBackground(Background.EMPTY);
				cc.setStyle("-fx-padding: 5 0 5 10;-fx-background-color: #fff003;");
				cc.getChildren().addAll(gameOver,finalTime,finalMonstre,finalBalles,replay);
				container.getChildren().addAll(cc);
			}else {				
				refreshContent();
			}
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
				nbBallesTires++;
				txtBallesTires.setText("Balles tires ( " + nbBallesTires + " )");
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
					nbMonstresTues++;
					txtMonstresTues.setText(" Monstres tués ( " + nbMonstresTues + " )");
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
		for(Bombe b:bombes){
			if(b.touch(player)){
				container.getChildren().remove(b.getCorps());
				b.setAlive(false);
				Tools.SoundPlayerDead();
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				nbLife--;
				if(nbLife<=0) {
					isGameOver = true;
				}
				txtLife.setText(" Life ( " + nbLife + " )");
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
			if(Math.random() < 0.025) {
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
		final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
            	time.setText((" Time ( " + heurs + ":" + minutes + ":" + seconds + " )"));
                seconds++;
                if(seconds == 60){
                	minutes++;
                	seconds = 0;
                }
                if(minutes == 60){
                	heurs++;
                	minutes = 0;
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
        
      Tools.SoundBgCardriving();
            
		Application.launch();
	}

	private void createContent(){
		container.getChildren().add(gp);
		//line.setStyle("-fx-color: #fff003;");
		line.setStroke(Color.RED);
		 line.setStrokeWidth(0);
		container.getChildren().add(line);
		porte.setFill(Color.ORANGE);
		container.getChildren().add(porte);
		container.getChildren().add(player.getCorps());
		container.getChildren().add(arme.getCorps());
		container.getChildren().add(arme.getSortie());
		toolBar.setAlignment(Pos.CENTER_RIGHT);
        toolBar.setPrefHeight(25);
        toolBar.setMinHeight(25);
        toolBar.setMaxHeight(25);
        toolBar.getChildren().add(new topBar());
        container.getChildren().add(toolBar);
	}
	
	class topBar extends HBox {
        public topBar() {
        	txtBallesTires.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        	txtBallesTires.setFill(Color.WHITE);
        	txtMonstresTues.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        	txtMonstresTues.setFill(Color.WHITE);
        	time.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        	time.setFill(Color.WHITE);
        	txtLife.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        	txtLife.setFill(Color.WHITE);
            this.getChildren().addAll(txtBallesTires, txtMonstresTues, time, txtLife);
            this.setStyle("-fx-padding: 5 0 5 10;-fx-background-color: #dbce01;");
            this.setMinWidth(widthWindow);
        }
    }
	
	@Override
	public void start(Stage window) throws Exception {
		window.setWidth(widthWindow);
		window.setHeight(heightWindow);
		window.setTitle("jeu de guerre");
		
		Image image =new Image("file:photosJeu/backGif.gif");
		ImageView imv = new ImageView(image);
		imv.setFitHeight(window.getHeight());
		imv.setFitWidth(window.getWidth());
		Group root = new Group();
		root.getChildren().addAll(imv);
		container.getChildren().add(root);
		
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
