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
	
	//should probably be in its own separate unit case
	@Test
	public void OceanMapTest() {
		oceanMap = OceanMap.getInstance();
		int cell = columbus.OceanMap.whatIs(0,0);
		assertTrue(cell >= 0 && cell <= 6);
	}
	
	@Test
	public void SingletonTest() {
		columbus.OceanMap oceanMap1 = OceanMap.getInstance();
		columbus.OceanMap oceanMap2 = OceanMap.getInstance();
		assertEquals(oceanMap1, oceanMap2);
	}

}
