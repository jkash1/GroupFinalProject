package columbus;

import java.util.Random;

public class OceanMap {
	static OceanMap oceanMap;
	
	int[][] map = new int[10][10];
	
	int dimension;
	int islandCount;
	
	Random rand = new Random();
	
	private OceanMap() {
		this.dimension = 10;
		this.islandCount = 10;
		
		int currentIslands = 0;
		int x;
		int y;
		
		while(currentIslands < islandCount) {
			x = rand.nextInt(dimension);
			y = rand.nextInt(dimension);
			
			if(map[x][y] == 0) {
				if(currentIslands < 2) {
					map[x][y] = 2;
				} else {
					map[x][y] = 1;
				}
				
				currentIslands++;
			}
		}
	}
	
	public static OceanMap getInstance() {
		if(oceanMap == null) {
			oceanMap = new OceanMap();
		}
		
		return oceanMap;
	}
	
	public static void resetMap() {
		oceanMap = new OceanMap();
	}
	
	public int[][] getMap() {
		return map;
	}
}