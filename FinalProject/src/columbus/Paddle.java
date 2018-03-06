package columbus;

import java.awt.Point;

public class Paddle extends PowerUps{
	ShipInterface ability;
	
	public Paddle(ShipInterface ability) {
		this.ability = ability;
	}
	
	public String getAbilities() {
		return ability.getAbilities() + ", Paddle";
	}

	@Override
	public void moveNorth() {
		ability.moveNorth();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveEast() {
		ability.moveEast();
		//ability.moveEast();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveSouth() {
		ability.moveSouth();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveWest() {
		ability.moveWest();
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point getShipLocation() {
		// TODO Auto-generated method stub
		return ability.getShipLocation();
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return ability.getType();
	}

	@Override
	public void stopGame() {
		ability.stopGame();
		// TODO Auto-generated method stub
		
	}
	

}
