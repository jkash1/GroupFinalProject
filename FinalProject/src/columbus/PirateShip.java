package columbus;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

public class PirateShip implements ShipInterface, Observer {
	//Creates global variables for oceanMap, map array, and location
	OceanMap oceanMap;
	int[][] map;
	Point location;
	
	public PirateShip(int x, int y) {
		//Gets an instance of the ocean map then pulls the map array from it
		oceanMap = OceanMap.getInstance();
		map = oceanMap.getMap();

		//Sets the location of the ship
		location.setLocation(x, y);
	}
	
	public Point getShipLocation() {
		return location;
	}
	
	public void moveNorth() {
		location.y--;
	}

	public void moveEast() {
		location.x++;
	}

	public void moveSouth() {
		location.y++;
	}

	public void moveWest() {
		location.x--;
	}
	
	public String getType() {
		return "pirate";
	}

	public void update(Observable ship, Object arg1) {
		//TODO decide where to move
	}
}
