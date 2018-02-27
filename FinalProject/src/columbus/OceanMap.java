package columbus;

import java.util.Random;

public class OceanMap {
	//Creates an OceanMap instance for the singleton pattern
	static OceanMap oceanMap;
	
	//Creates a 2 dimensional integer array to store the map
	int[][] map = new int[10][10];
	
	//Integer variables for map dimensions, normal island count, and pirate island count
	int dimension;
	int islandCount;
	int pirateIslandCount;
	
	//Creates a random object to generate island locations
	Random rand = new Random();
	
	private OceanMap() {
		//Sets the dimensions and island counts
		this.dimension = 10;
		this.islandCount = 10;
		this.pirateIslandCount = 2;
		
		//Creates local variables for current island count and island coordinates
		int currentIslands = 0;
		int x;
		int y;
		
		//Creates the amount of islands specified in islandCount and makes sure they aren't on the same spot as another
		while(currentIslands < islandCount) {
			x = rand.nextInt(dimension);
			y = rand.nextInt(dimension);
			
			if(map[x][y] == 0) {
				map[x][y] = 1;
				
				currentIslands++;
			}
		}
		
		currentIslands = 0;

		//Creates the amount of pirate islands specified in pirateIslandCount and makes sure they aren't on the same spot as another
		while(currentIslands > pirateIslandCount) {
			x = rand.nextInt(dimension);
			y = rand.nextInt(dimension);
			
			if(map[x][y] == 0) {
				map[x][y] = 2;
				
				currentIslands++;
			}
		}
	}
	
	public static OceanMap getInstance() {
		//If there isn't a global OceanMap already, create one
		if(oceanMap == null) {
			oceanMap = new OceanMap();
		}
		
		//Return the global OceanMap
		return oceanMap;
	}
	
	public static void resetMap() {
		//Generates a new OceanMap for the global (resets the map)
		oceanMap = new OceanMap();
	}
	
	public int[][] getMap() {
		//Returns the 2 dimensional map array
		return map;
	}
}