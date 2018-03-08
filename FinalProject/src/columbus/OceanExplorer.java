package columbus;

//import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;


import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.Button;
//import javafx.scene.control.Dialog;
//import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class OceanExplorer extends Application{

	int scale = 20;
	public static int dimensions;
	Image shipImage, pirateImage, IslandImage;
	ImageView shipImageView, paddleImageView, rocketImageView, boozeImageView;
	OceanMap oceanMap;
	int[][] map;
	Scene scene;
	AnchorPane root;
	ShipInterface ship;	
	LinkedList<ShipInterface> pirates = new LinkedList<ShipInterface>();
	LinkedList<ImageView> pirateImages = new LinkedList<ImageView>();
	MonsterSpawner monsterSpawner;
	Thread monstersThread;
	boolean playing = true;
	boolean hard = true;
	int damage = 0;
	
	@Override
	public void start(Stage oceanStage) throws Exception {
		//Gets an instance of the ocean map, gets its dimensions, and gets the map from it
		oceanMap = OceanMap.getInstance();
		dimensions = oceanMap.getDimension();
		map = oceanMap.getMap();
		
		//Creates a ship for the player
		ship = new PlayerShip();
		shipImageView = createShipImage(ship);
		
		//Creates a pane, scene, and draws the map
		root = new AnchorPane();
		scene = new Scene(root, scale * dimensions, (scale * dimensions) + (scale * 2));
		drawMap();
		root.getChildren().add(shipImageView);
		
		Button difficulty = new Button("Switch Difficulty to Easy");
		difficulty.setMinHeight(2 * scale);
		difficulty.setMinWidth((dimensions * scale) / 2);
		difficulty.setLayoutX((dimensions * scale) / 2);
		difficulty.setLayoutY(dimensions * scale);
		root.getChildren().add(difficulty);
		
		difficulty.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				changeDifficulty(difficulty);
			}
		});
		
		//Sets the scene, adds a title to the window, and shows it
		oceanStage.setScene(scene);
		oceanStage.setTitle("Columbus vs. the Deep Blue");
		oceanStage.show();
		
		//Spawns the monsters and starts the thread
		monsterSpawner = new MonsterSpawner(20);
		monsterSpawner.addToPane(root.getChildren());
		
		monstersThread = new Thread(monsterSpawner);
	    monstersThread.start();
	    
		//Start listening for user input and moving the boat
		startSailing();
	}
	
	public void changeDifficulty(Button button) {
		hard = !hard;
		
		for(ShipInterface p : pirates) {
			((PirateShip) p).setStrategy(hard ? new SmartPursuit() : new SimplePursuit());
		}
		
		button.setText(hard ? "Set Difficulty to Easy" : "Set Difficulty to Hard");
	}
	
	public void startSailing() {
		if(playing) {
			//Creates a new key event handler to move the player
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
				public void handle(KeyEvent ke) {
					switch(ke.getCode()) {
					case D:
					case RIGHT:
						ship.moveEast(ship.getVelocity());
						break;
					case A:	
					case LEFT:
						ship.moveWest(ship.getVelocity());
						break;
					case W:
					case UP:
						ship.moveNorth(ship.getVelocity());
						break;
					case S:	
					case DOWN:
						ship.moveSouth(ship.getVelocity());
						break;
					case Q:
						System.exit(0);
					default:
						break;
					}
					
					//updates ship with powerups
					checkPowerUp();
					//check damage status
					checkDamage();
					//Updates the locations of all ships
					updateShips();
				}
			});
		}
	}
	
	public void drawMap() {
		//Iterates through every square on the map
		for(int x = 0; x < dimensions; x++) {
			for(int y = 0; y < dimensions; y++) {
				//Creates a rectangle for a square on the map
				Rectangle rect = new Rectangle(x * scale, y * scale, scale, scale);
				
				//Sets the rectangle to be turquoise with a pale turquoise stroke
				rect.setStroke(Color.TURQUOISE);
				rect.setFill(Color.PALETURQUOISE);
				
				//Adds a blue square to the pane and pushes it to the back so ships display over them
				root.getChildren().add(rect);
				rect.toBack();
				
				//Draws an island of the desired type
				if(map[x][y] == 1) {
					drawIsland(x, y, "normal");
				} else if(map[x][y] == 2) {
					drawIsland(x, y, "pirate");
					createPirate(x, y);
				} else if(map[x][y] == 3) {
					drawIsland(x, y, "treasure");
				} else if(map[x][y] == 4) {
					drawIsland(x, y, "paddle");
				} else if(map[x][y] == 5) {
					drawIsland(x,y, "Rocket");
				}else if(map[x][y] == 6) {
					drawIsland(x,y,"booze");
				}
				
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
		case("treasure"):
			fileName = "/images/treasureIsland.png";
			break;
		case("paddle"):
			fileName = "/images/paddle.png";
			break;
		case("Rocket"):
			fileName = "/images/rocket.png";
			break;
		case("booze"):
			fileName = "/images/booze.png";
			break;
		}
		
		//Creates an Image and ImageView based on the file name
		URL url = getClass().getResource(fileName);
		Image island = new Image(url.toString(), scale, scale, false, false);
		ImageView islandImageView = new ImageView(island);
		
		//Sets the image view to the location passed in
		islandImageView.setX(x * scale);
		islandImageView.setY(y * scale);

		if(fileName == "/images/paddle.png") {
			paddleImageView = islandImageView;
		} else if(fileName == "/images/rocket.png") {
			rocketImageView = islandImageView;
		} else if(fileName == "/images/booze.png") {
			boozeImageView = islandImageView;
		}

		//Adds the image view to the pane
		root.getChildren().add(islandImageView);
	}
	
	public void createPirate(int x, int y) {
		//Creates a new ship and image view for the new pirate
		ShipInterface pirateShip = new PirateShip(x, y);
		ImageView pirateShipImageView = createShipImage(pirateShip);
		
		//Adds each new pirate ship to the list of observers for the player ship
		((PlayerShip) ship).addObserver((PirateShip) pirateShip);
		
		//Adds the ships image view to the pane
		root.getChildren().add(pirateShipImageView);
		
		//Adds both the ship and the image view to their lists
		pirates.add(pirateShip);
		pirateImages.add(pirateShipImageView);
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
		URL url = getClass().getResource(fileName);
		Image shipImage = new Image(url.toString(), scale, scale, false, false);
		ImageView shipImageView = new ImageView(shipImage);
		
		//Sets the image views location to the location of the ship
		shipImageView.setX(ship.getShipLocation().x * scale);
		shipImageView.setY(ship.getShipLocation().y * scale);
		
		//Returns the ship image view
		return shipImageView;
	}
	
	public void updateShips() {
		//Updates the image view for the player ship
		shipImageView.setX(ship.getShipLocation().x * scale);
		shipImageView.setY(ship.getShipLocation().y * scale);
		
		//TODO This is the win condition but it currently only prints to the console
		if(checkWin()) {
			System.out.println("you win");
			stopPlaying();
			Alert alert = new Alert(AlertType.INFORMATION);
			URL url = getClass().getResource("/images/treasureIsland.png");
			Image treasure = new Image(url.toString(), 100, 100, false, false);
			ImageView TreasureIsland = new ImageView(treasure);
			TreasureIsland.setFitWidth(100);
			TreasureIsland.setFitHeight(100);
			alert.setTitle("End Game");
			alert.setHeaderText("You Found the Treasure!");
			alert.setContentText("You Win!\n Total Damage: " + damage);
			alert.setGraphic(TreasureIsland);
			alert.getButtonTypes().remove(0);
			alert.getButtonTypes().add(new ButtonType("Quit"));
			alert.showAndWait();
		
			if(alert.getResult().getText() == "Quit")
				System.exit(0);
		}
		
		//Updates the image views for each pirate ship
		for(int i = 0; i < pirates.size(); i++) {
			pirateImages.get(i).setX(pirates.get(i).getShipLocation().x * scale);
			pirateImages.get(i).setY(pirates.get(i).getShipLocation().y * scale);
			
			//TODO This is the lose condition but it currently only prints to the console
			if(pirates.get(i).getShipLocation().equals(ship.getShipLocation())) {
				Alert alert = new Alert(AlertType.INFORMATION);
				URL url = getClass().getResource("/images/pirateShip.png");
				Image pirate = new Image(url.toString(), 100, 100, false, false);
				ImageView pirateShip = new ImageView(pirate);
				pirateShip.setFitWidth(100);
				pirateShip.setFitHeight(100);
				alert.setTitle("End Game");
				alert.setHeaderText("The Pirates Boarded your ship!");

				alert.setGraphic(pirateShip);
				alert.setContentText("You Lose!\n Total Damage: " + damage);
				alert.showAndWait();
				
				
				stopPlaying();
				System.exit(0);

				alert.setContentText("You Loose!");
				alert.getButtonTypes().remove(0);
				alert.getButtonTypes().add(new ButtonType("Quit"));
				alert.showAndWait();
				
				if(alert.getResult().getText() == "Quit")
					System.exit(0);

			}
		}
	}
	
	public void checkPowerUp() {
		if(map[ship.getShipLocation().x][ship.getShipLocation().y] == 4) {
			ship = new Paddle(ship);
			map[ship.getShipLocation().x][ship.getShipLocation().y] = 0;
			paddleImageView.setX(-100);
		}
		if(map[ship.getShipLocation().x][ship.getShipLocation().y] == 5) {
			ship = new RocketBooster(ship);
			map[ship.getShipLocation().x][ship.getShipLocation().y] = 0;
			rocketImageView.setX(-100);
		}
		if(map[ship.getShipLocation().x][ship.getShipLocation().y] == 6) {
			ship = new Booze(ship);
			map[ship.getShipLocation().x][ship.getShipLocation().y] = 0;
			boozeImageView.setX(-100);
		}
	}
	
	public void checkDamage() {
		for(Monster monsterSprite : monsterSpawner.monsterSprites)
			if(ship.getShipLocation().x == monsterSprite.getX() && ship.getShipLocation().y == monsterSprite.getY())
				damage++;
	}
	
	
	public boolean checkWin() {
		boolean output = false;
		//checks right adj cell
		if(ship.getShipLocation().x < dimensions - 1)
			if(map[ship.getShipLocation().x+1][ship.getShipLocation().y] == 3) 
				output = true;
			
		
		//checks left adj cell
		if(ship.getShipLocation().x > 0)
			if(map[ship.getShipLocation().x-1][ship.getShipLocation().y] == 3) 
				output = true;
		
		
		//checks top adj cell
		if(ship.getShipLocation().y < dimensions - 1) 
			if(map[ship.getShipLocation().x][ship.getShipLocation().y+1] == 3) 
				output = true;
			
		
		//checks bot adj cell
		if(ship.getShipLocation().y > 0)
			if(map[ship.getShipLocation().x][ship.getShipLocation().y-1] == 3) 
				output = true;
			
		return output;
	}
	
	@SuppressWarnings("deprecation")
	public void stopPlaying() {
		playing = false;
		ship.stopGame();
		monstersThread.stop();
		for(ShipInterface p : pirates) {
			p.stopGame();
		}
	}
	
	public static void main(String[] args) {
		//Launches the game
		launch(args);
	}
}