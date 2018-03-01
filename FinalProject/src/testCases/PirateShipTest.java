package testCases;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

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
		int[][] oMap = oceanMap.getMap();
		int dimension = oceanMap.getDimension();
		boolean error = false;
		for (int x = 0; x < dimension; x++) {
			for (int y = 0; y < dimension; y++) {
				if (oMap[x][y] < 0) {
					error = true;
				}
			}
		}
		assertFalse(error);
	}

}
