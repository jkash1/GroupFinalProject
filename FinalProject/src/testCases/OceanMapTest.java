package testCases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import columbus.OceanExplorer;
import columbus.OceanMap;

public class OceanMapTest {
	columbus.OceanMap oceanMap;
	
	@Test
	public void test() {
	}
	
	//should probably be in its own separate unit case
		@Test
		public void OceanMapTest() {
			oceanMap = OceanMap.getInstance();
			int cell = columbus.OceanMap.whatIs(0,0);
			assertTrue(cell >= 0);
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
