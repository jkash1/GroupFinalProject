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
	LinkedList<ImageView> pirateImages = new LinkedList<ImageView>();
	
	@Override
	public void start(Stage oceanStage) throws Exception {
		//Gets an instance of the ocean map, gets its dimensions, and gets the map from it
		oceanMap = OceanMap.getInstance();
		dimensions = oceanMap.getDimension();
		map = oceanMap.getMap();
		
		ship = new PlayerShip();
		shipImageView = createShipImage(ship);
		
		//Creates a pane, scene, and draws the map
		root = new AnchorPane();
		scene = new Scene(root, 900, 900);
		drawMap();
		root.getChildren().add(shipImageView);
		
		//Sets the scene, adds a title to the window, and shows it
		oceanStage.setScene(scene);
		oceanStage.setTitle("Columbus vs. the Deep Blue");
		oceanStage.show();
		
		//Start listening for user input and moving the boat
		startSailing();
	}
	//TODO Load Images
	
	public void startSailing() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				switch(ke.getCode()) {
				case RIGHT:
					ship.moveEast();
					break;
				case LEFT:
					ship.moveWest();
					break;
				case UP:
					ship.moveNorth();
					break;
				case DOWN:
					ship.moveSouth();
					break;
				case Q:
					System.exit(0);
				default:
					break;
				}
				
				System.out.println(String.format("Ship is at (%d, %d)", ship.getShipLocation().x, ship.getShipLocation().y));
				
				updateShips();
			}
		});
	}
	
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
	
	public void drawShip(String type) {
		
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
	
	public void createPirate(int x, int y) {
		
	}
	
	public ImageView createShipImage(ShipInterface ship) {
		//Gets the ship type and creates a string for the file name
		String type = ship.getType();
		String fileName = null;
		
		//Decides which island image to use based on the type variable
		switch(type) {
		case("player"):
			fileName = "/images/ship.png";
			break;
		case("pirate"):
			fileName = "/images/pirateShip.png";
			break;
		}
		
		//Creates an image and image view with the given file name
		Image shipImage = new Image(fileName, scale, scale, false, false);
		ImageView shipImageView = new ImageView(shipImage);
		
		//Sets the image views location to the location of the ship
		shipImageView.setX(ship.getShipLocation().x * scale);
		shipImageView.setY(ship.getShipLocation().y * scale);
		
		//Returns the ship image view
		return shipImageView;
	}
	
	public void updateShips() {
		shipImageView.setX(ship.getShipLocation().x * scale);
		shipImageView.setY(ship.getShipLocation().y * scale);
	}
	
	/*
	 * Main launches the game
	 */
	public static void main(String[] args) {
		launch(args);
	}

} //testing Git commit -Antonio
