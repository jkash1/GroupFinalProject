package columbus;

import java.awt.Point;
import java.util.Observable;
import java.util.Random;

public class PlayerShip extends Observable implements ShipInterface{
	//Creates global variables for oceanMap, map array, and location
	OceanMap oceanMap;
	int[][] map;
	Point location;
	boolean moveable = true;
	int velocity = 1;
	//String description;
	
	public PlayerShip() {
		//Gets an instance of the ocean map then pulls the map array from it
		oceanMap = OceanMap.getInstance();
		map = oceanMap.getMap();
		//description = "Ship ";
		
		Random rand = new Random();
		
		//Sets the location of the ship to a random position to the left side of the map
		while(location == null) {
			int x = rand.nextInt(oceanMap.getDimension() / 10);
			int y = rand.nextInt(oceanMap.getDimension());
			
			if(map[x][y] == 0) {
				location = new Point(x, y);
			}
		}
	}
	
	public Point getShipLocation() {
		//Returns the location of the ship
		return location;
	}
	
	

	public void moveNorth() {
		if(moveable) {
			//Checks if the ship is not already at the top edge of the map
			if(location.y > 0) {
				//Checks if the space above is a water square
				if(map[location.x][location.y - 1] == 0) {
					//Moves the ship 1 square north
					location.y-= velocity;
					updateObservers();
				}
			}
		}
	}

	public void moveEast() {
		if(moveable) {
			//Checks if the ship is not already at the right edge of the map
			if(location.x < oceanMap.getDimension() - 1) {
				//Checks if the space to the right is a water square
				if(map[location.x + 1][location.y] == 0) {
					//Moves the ship 1 square east
					location.x+= velocity;
					updateObservers();
				}
			}
		}
	}

	public void moveSouth() {
		if(moveable) {
			//Checks if the ship is not already at the bottom edge of the map
			if(location.y < oceanMap.getDimension() - 1) {
				//Checks if the space below is a water square
				if(map[location.x][location.y + 1] == 0) {
					//Moves the ship 1 square south
					location.y+= velocity;
					updateObservers();
				}
			}
		}
	}

	public void moveWest() {
		if(moveable) {
			//Checks if the ship is not already at the left edge of the map
			if(location.x > 0) {
				//Checks if the space to the left is a water square
				if(map[location.x - 1][location.y] == 0) {
					//Moves the ship 1 square west
					location.x-=velocity;
					updateObservers();
				}
			}
		}
	}
	public int getVelocity() {
		return velocity;
	}
	public void updateObservers() {
		//Notifies the observers that the ship has moved
		setChanged();
		notifyObservers();
	}
	
	public String getType() {
		return "player";
	}
	public void stopGame() {
		moveable = false;
	}

	@Override
	public String getAbilities() {
		// TODO Auto-generated method stub
		return "Ship";
	}
}