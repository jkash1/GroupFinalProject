package testCases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.Test;

import columbus.OceanExplorer;
import columbus.OceanMap;
import columbus.PlayerShip;

public class OceanMapTest {
	columbus.OceanMap oceanMap;
	
	@Test
	public void test() {
	}
	
	//should probably be in its own separate unit case
		@Test
		public void OceanMapTest() {
			oceanMap = OceanMap.getInstance();
			
			PlayerShip ps = new PlayerShip();
			Point loc = ps.getShipLocation();
			String cell = columbus.OceanMap.whatIs(loc);
			assertTrue(cell == "ocean");
		}
		
		@Test
		public void SingletonTest() {
			columbus.OceanMap oceanMap1 = OceanMap.getInstance();
			columbus.OceanMap oceanMap2 = OceanMap.getInstance();
			assertEquals(oceanMap1, oceanMap2);
		}
		
		public void sizeTest() {
			OceanMap m = OceanMap.getInstance();
			assert(m != null);
			
			int[][] b = m.getMap();
			
			assert(b != null);
			
			assert(b.length == OceanExplorer.dimensions);
			assert(b[0].length == OceanExplorer.dimensions);
		}
		
		
}
