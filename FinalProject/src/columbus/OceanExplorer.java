package columbus;

import java.util.LinkedList;

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

	int scale = 30;
	int dimensions;
	Image shipImage, pirateImage, IslandImage;
	ImageView shipImageView;
	OceanMap oceanMap;
	int[][] map;
	Scene scene;
	AnchorPane root;
	ShipInterface ship;	
	LinkedList<ShipInterface> pirates = new LinkedList<ShipInterface>();
	
	@Override
	public void start(Stage oceanStage) throws Exception {
		//Gets an instance of the ocean map, gets its dimensions, and gets the map from it
		oceanMap = OceanMap.getInstance();
		dimensions = oceanMap.getDimension();
		map = oceanMap.getMap();
		
		//Creates a pane, scene, and draws the map
		root = new AnchorPane();
		scene = new Scene(root, 900, 900);
		drawMap();
		
		//Sets the scene, adds a title to the window, and shows it
		oceanStage.setScene(scene);
		oceanStage.setTitle("Columbus vs. the Deep Blue");
		oceanStage.show();
		
		//startSailing();
	}
	//TODO Load Images
	
	/**
	 * Draws the Grid
	 */
	public void drawMap() {
		//Iterates through every square on the map
		for(int x = 0; x < dimensions; x++) {
			for(int y = 0; y < dimensions; y++) {
				//Creates a rectangle for a square on the map
				Rectangle rect = new Rectangle(x * scale, y * scale, scale, scale);
				
				//Sets the rectangle to be turquoise with a pale turquoise stroke
				rect.setStroke(Color.TURQUOISE);
				rect.setFill(Color.PALETURQUOISE);
				
				//Adds a blue square to the pane
				root.getChildren().add(rect);
				
				//Draws an island of the desired type
				if(map[x][y] == 1)
					drawIsland(x, y, "normal");
				else if(map[x][y] == 2)
					drawIsland(x, y, "pirate");
			}
		}	
	}
	
	public void drawIsland(int x, int y, String type) {
		//Creates a string called fileName to store the name of the island image
		String fileName = null;
		
		//Decides which island image to use based on the type variable
		switch(type) {
		case("normal"):
			fileName = "/images/island.jpg";
			break;
		case("pirate"):
			fileName = "/images/pirateIsland.png";
			break;
		}
		
		//Creates an Image and ImageView based on the file name
		Image island = new Image(fileName, scale, scale, false, false);
		ImageView islandImageView = new ImageView(island);
		
		//Sets the image view to the location passed in
		islandImageView.setX(x * scale);
		islandImageView.setY(y * scale);
		
		//Adds the image view to the pane
		root.getChildren().add(islandImageView);
	}
	
	
	/*
	 * Main launches the game
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
