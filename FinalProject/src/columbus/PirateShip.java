package columbus;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

public class PirateShip implements ShipInterface, Observer {
	//Creates global variables for oceanMap, map array, and location
	OceanMap oceanMap;
	int[][] map;
	Point location;
	
	PersuitStrategy persuitStrategy;
	
	public PirateShip(int x, int y) {
		//Gets an instance of the ocean map then pulls the map array from it
		oceanMap = OceanMap.getInstance();
		map = oceanMap.getMap();

		//Sets the location of the ship
		location = new Point(x, y);
		
		//Creates a persuit strategy for the pirate ship
		persuitStrategy = new SimplePersuit();
	}
	
	public void setStrategy(PersuitStrategy strategy) {
		persuitStrategy = strategy;
	}
	
	public Point getShipLocation() {
		//Returns the point that the ship is located at
		return location;
	}
	
	public void moveNorth() {
		//Move 1 square up
		location.y--;
	}

	public void moveEast() {
		//Move 1 square right
		location.x++;
	}

	public void moveSouth() {
		//Move 1 square down
		location.y++;
	}

	public void moveWest() {
		//Move 1 square left
		location.x--;
	}
	
	public String getType() {
		return "pirate";
	}

	public void update(Observable ship, Object arg1) {
		//Gets the recommended move from the persuit strategy
		String move = persuitStrategy.decideMove(location, ((ShipInterface) ship).getShipLocation());
		
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
