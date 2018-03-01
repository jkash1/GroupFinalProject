package columbus;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

public class PirateShip implements ShipInterface, Observer {
	//Creates global variables for oceanMap, map array, and location
	OceanMap oceanMap;
	int[][] map;
	Point location;
	
	PursuitStrategy pursuitStrategy;
	
	public PirateShip(int x, int y) {
		//Gets an instance of the ocean map then pulls the map array from it
		oceanMap = OceanMap.getInstance();
		map = oceanMap.getMap();

		//Sets the location of the ship
		location = new Point(x, y);
		
		//Creates a pursuit strategy for the pirate ship
		pursuitStrategy = new SmartPursuit();
	}
	
	public void setStrategy(PursuitStrategy strategy) {
		//Sets the current pursuit strategy a new one passed in
		pursuitStrategy = strategy;
	}
	
	public Point getShipLocation() {
		//Returns the point that the ship is located at
		return location;
	}
	
	public void moveNorth() {
		//Checks if the ship is not already at the top edge of the map
		if(location.y > 0) {
			//Checks if the space above is a water square
			if(map[location.x][location.y - 1] != 1 || map[location.x][location.y - 1] != 2) {
				//Moves the ship 1 square north
				location.y--;
			}
		}
	}

	public void moveEast() {
		//Checks if the ship is not already at the right edge of the map
		if(location.x < oceanMap.dimension - 1) {
			//Checks if the space to the right is a water square
			if(map[location.x + 1][location.y] == 0) {
				//Moves the ship 1 square east
				location.x++;
			}
		}
	}

	public void moveSouth() {
		//Checks if the ship is not already at the bottom edge of the map
		if(location.y < oceanMap.dimension - 1) {
			//Checks if the space below is a water square
			if(map[location.x][location.y + 1] == 0) {
				//Moves the ship 1 square south
				location.y++;
			}
		}
	}

	public void moveWest() {
		//Checks if the ship is not already at the left edge of the map
		if(location.x > 0) {
			//Checks if the space to the left is a water square
			if(map[location.x - 1][location.y] == 0) {
				//Moves the ship 1 square west
				location.x--;
			}
		}
	}
	
	public String getType() {
		return "pirate";
	}

	public void update(Observable ship, Object arg1) {
		//Gets the recommended move from the pursuit strategy
		String move = pursuitStrategy.decideMove(location, ((ShipInterface) ship).getShipLocation());
		
		//Moves based on the recommended move
		switch(move) {
		case("UP"):
			moveNorth();
			break;
		case("DOWN"):
			moveSouth();
			break;
		case("LEFT"):
			moveWest();
			break;
		case("RIGHT"):
			moveEast();
			break;
		}
	}
}
