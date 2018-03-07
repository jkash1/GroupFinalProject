package testCases;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import columbus.OceanMap;

public class PirateShipTest {
	columbus.OceanMap oceanMap;
	int velocity = 1;

	@Test
	public void test() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void PirateShipMoveTest() {
		columbus.PirateShip pirate = new columbus.PirateShip(0,0);
		pirate.moveEast(velocity);
		Point loc = pirate.getShipLocation();
		assertEquals(loc, new Point(1,0));
	}
	
	

}
