package columbus;

import java.awt.Point;

public class SimplePersuit implements PersuitStrategy {
	
	String simplePersuit(Point location, Point otherLocation) {
		String move = null;
		
		//Decides where to move just based on location in comparison to the player ship
		if(location.x < otherLocation.x)
			move = "RIGHT";
		else if(location.x > otherLocation.x)
			move = "LEFT";
		else if(location.y < otherLocation.y)
			move = "DOWN";
		else if(location.y > otherLocation.y)
			move = "UP";
		else
			move = "NONE";
		
		return move;
	}
		
	public String decideMove(Point location, Point otherLocation) {
		//Gets the move from the persuit strategy
		return simplePersuit(location, otherLocation);
	}
}
