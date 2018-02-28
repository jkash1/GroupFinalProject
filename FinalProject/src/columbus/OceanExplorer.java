package columbus;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class OceanExplorer extends Application{

	public final int scale = 40;
	final int dimensions = 40;
	Image shipImage, pirateImage, IslandImage;
	ImageView shipImageView;
	OceanMap oceanMap;
	Scene scene;
	AnchorPane root;
	ShipInterface ship;
	
	
	
	@Override
	public void start(Stage oceanStage) throws Exception {
		
		oceanMap = OceanMap.getInstance();
		root = new AnchorPane();
		
		scene = new Scene(root, 800, 600);
		
		oceanStage.setScene(scene);
		oceanStage.setTitle("Columbus vs. the Deep Blue");
		
		oceanStage.show();
		drawMap();
		
	//	startSailing();
		
	}
	//TODO Load Images
	
	/**
	 * Draws the Grid
	 */
	public void drawMap() {
		for(int x = 0; x < dimensions; x++) {
			for(int y = 0; y < dimensions; y++) {
				Rectangle rect = new Rectangle(x*scale, y*scale,scale,scale);
				rect.setStroke(Color.TURQUOISE);
				rect.setFill(Color.PALETURQUOISE);
				root.getChildren().add(rect);
			
			}
		}	
	}
	
	
	
	
	/*
	 * Main launches the game
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
