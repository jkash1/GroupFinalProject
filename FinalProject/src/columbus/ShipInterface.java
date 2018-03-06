package columbus;

import java.awt.Point;

public interface ShipInterface {
	String description = "blah blah ";
	//Provides methods for moving each direction
	public void moveNorth();
	public void moveEast();
	public void moveSouth();
	public void moveWest();
	public Point getShipLocation();
	public String getType();
	public void stopGame();
	public String getAbilities();
}
