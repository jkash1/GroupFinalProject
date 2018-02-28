package columbus;

import java.awt.Point;

public class PlayerShip implements ShipInterface {
	//Creates global variables for oceanMap, map array, and location
	OceanMap oceanMap;
	int[][] map;
	Point location;
	
	public PlayerShip() {
		//Gets an instance of the ocean map then pulls the map array from it
		oceanMap = OceanMap.getInstance();
		map = oceanMap.getMap();
		
		//Sets the location of the ship to (1, 1) every time
		//TODO set a random location for the ship
		location.setLocation(1, 1);
	}
	
	public Point getShipLocation() {
		//Returns the location of the ship
		return location;
	}

	public void moveNorth() {
		//Checks if the ship is not already at the top edge of the map
		if(location.y > 0) {
			//Checks if the space above is a water square
			if(map[location.x + 1][location.y] < 1) {
				//Moves the ship 1 square north
				location.y--;
			}
		}
	}

	public void moveEast() {
		//Checks if the ship is not already at the right edge of the map
		if(location.x < oceanMap.dimension + 1) {
			//Checks if the space to the right is a water square
			if(map[location.x + 1][location.y] < 1) {
				//Moves the ship 1 square east
				location.x++;
			}
		}
	}

	public void moveSouth() {
		//Checks if the ship is not already at the bottom edge of the map
		if(location.y < oceanMap.dimension - 1) {
			//Checks if the space below is a water square
			if(map[location.x + 1][location.y] < 1) {
				//Moves the ship 1 square south
				location.y++;
			}
		}
	}

	public void moveWest() {
		//Checks if the ship is not already at the left edge of the map
		if(location.x > 0) {
			//Checks if the space to the left is a water square
			if(map[location.x + 1][location.y] < 1) {
				//Moves the ship 1 square west
				location.x--;
			}
		}
	}
}
