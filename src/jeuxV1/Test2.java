package jeuxV1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jeuxV1.Program.topBar;

public class Test2 extends Application {
	
	private static Pane container=new Pane();
	public static ImageView imgview = null;
	public static VBox box;
	
	@Override
	public void start(Stage window) throws Exception {
		window.setWidth(500);
		window.setHeight(300);
		window.setTitle("jeu de guerre");
		createContent();		
		Scene scene=new Scene(container);
		window.setScene(scene);
		window.show();
		
	}
	
	public static ImageView createImageView(String path) throws FileNotFoundException{
		 Image img = new Image(new FileInputStream(path));
	     ImageView imgview = new ImageView();
	     imgview.setImage(img);
	     return imgview;
	}
	
	private void createContent(){
		box = new VBox();
		
		Button localLoadButton = new Button("Load image");
		localLoadButton.setOnAction(e -> {
			A a = new A();
			B b = new B();
			a.manger();
			b.marcher();
        });

		FlowPane buttonPane = new FlowPane();
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.getChildren().addAll(localLoadButton);

        VBox box2 = new VBox();
        box2.getChildren().add(buttonPane);
        container.getChildren().add(box2);
	}
	
	public static void main(String[] args) {
		Application.launch();
	}

	class A extends Thread {
		void manger() {
			try {
				imgview = createImageView("photosJeu/comp1.gif");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			imgview.setFitWidth(100);
			imgview.setFitHeight(100);
	        box.getChildren().add(imgview);
			container.getChildren().add(box);
			System.out.println("Ham Ham ");
			try{Thread.sleep(2000);}catch(Exception exp){}
		}
		@Override
		public void run() {
			manger();
		}
	}

	class B extends Thread {
		void marcher() {
			container.getChildren().remove(box);
			System.out.println("TikTak ");	
		}
		@Override
		public void run() { 
			marcher();
		}
	}

	
	
}


