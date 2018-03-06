package columbus;

import java.awt.Point;

public class Booze implements ShipInterface{
	ShipInterface ability;
	
	public Booze(ShipInterface ability) {
		this.ability = ability;
	}

	@Override
	public String getAbilities() {
		return ability.getAbilities() + ", booze";
	}

	@Override
	public void moveNorth() {
		ability.moveSouth();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveEast() {
		ability.moveWest();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveSouth() {
		ability.moveNorth();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveWest() {
		ability.moveEast();
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

	@Override
	public int getVelocity() {
		// TODO Auto-generated method stub
		return ability.getVelocity();
	}
	
	

}
