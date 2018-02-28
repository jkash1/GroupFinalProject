package columbus;

import java.awt.Point;
import java.util.Observable;

public class PlayerShip extends Observable implements ShipInterface{
	//Creates global variables for oceanMap, map array, and location
	OceanMap oceanMap;
	int[][] map;
	Point location;
	Status status;
	
	public PlayerShip() {
		//Gets an instance of the ocean map then pulls the map array from it
		oceanMap = OceanMap.getInstance();
		map = oceanMap.getMap();
		status = new Status();
		
		//Sets the location of the ship to (1, 1) every time
		//TODO set a random location for the ship
		location = new Point(1, 1);
	}
	
	public Point getShipLocation() {
		//Returns the location of the ship
		return location;
	}
	
	

	public void moveNorth() {
		//Checks if the ship is not already at the top edge of the map
		if(location.y > 0) {
			//Checks if the space above is a water square
			if(map[location.x][location.y - 1] == 0) {
				//Moves the ship 1 square north
				location.y--;
				updateObservers();
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
				updateObservers();
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
				updateObservers();
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
				updateObservers();
			}
		}
	}
	
	public void updateObservers() {
		//Notifies the observers that the ship has moved
		setChanged();
		notifyObservers();
	}
	
	public String getType() {
		return "player";
	}
}