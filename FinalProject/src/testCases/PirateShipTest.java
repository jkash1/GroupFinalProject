package testCases;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import columbus.OceanMap;

public class PirateShipTest {
	columbus.OceanMap oceanMap;

	@Test
	public void test() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void PirateShipMoveTest() {
		columbus.PirateShip pirate = new columbus.PirateShip(0,0);
		pirate.moveEast();
		Point loc = pirate.getShipLocation();
		assertEquals(loc, new Point(1,0));
	}
	
<<<<<<< HEAD
	//should probably be in its own separate unit case
	@Test
	public void OceanMapTest() {
		oceanMap = OceanMap.getInstance();
		int cell = columbus.OceanMap.whatIs(0,0);
		assertTrue(cell >= 0 && cell <= 6);
	}
=======
>>>>>>> 0828c50ab88fb7fc6089901a4a29b7d18b27b983
	

}
