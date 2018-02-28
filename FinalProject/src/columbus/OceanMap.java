package columbus;

import java.util.Random;

public class OceanMap {
	//Creates an OceanMap instance for the singleton pattern
	static OceanMap oceanMap;
	
	//Creates a 2 dimensional integer array to store the map
	int[][] map;
	
	//Integer variables for map dimensions, normal island count, and pirate island count
	int dimension;
	int islandCount;
	int pirateIslandCount;
	int paddleCount;
	int boozeCount;
	int rocketCount;
	
	//Creates a random object to generate island locations
	Random rand = new Random();
	
	private OceanMap() {
		//Sets the dimensions and island counts
		this.dimension = 30;
		this.islandCount = 10;
		this.pirateIslandCount = 2;
		this.paddleCount = 2;
		this.boozeCount = 2;
		this.rocketCount = 2;
		
		//Creates the 2 dimensional array with the dimensions specified
		map = new int[dimension][dimension];
		
		//Creates local variables for current island count and island coordinates
		spawnIslands("island", islandCount);
		spawnIslands("pirateIsland", pirateIslandCount);
		spawnIslands("treasureIsland", 1);
		spawnIslands("paddle", paddleCount);
		spawnIslands("rocket", rocketCount);
		spawnIslands("booze", boozeCount);
		
		//Creates the amount of islands specified in islandCount and makes sure they aren't on the same spot as another
		/*while(currentIslands < islandCount) {
			x = rand.nextInt(dimension);
			y = rand.nextInt(dimension);
			
			if(map[x][y] == 0) {
				map[x][y] = 1;
				
				currentIslands++;
			}
		}
		
		currentIslands = 0;

		//Creates the amount of pirate islands specified in pirateIslandCount and makes sure they aren't on the same spot as another
		while(currentIslands < pirateIslandCount) {
			x = rand.nextInt(dimension);
			y = rand.nextInt(dimension);
			
			if(map[x][y] == 0) {
				map[x][y] = 2;
				
				currentIslands++;
			}
		}
		
		currentIslands = 0;
		
		//Creates a single treasure island at a random square within 3 squares of the right edge of the map
		while(currentIslands < 1) {
			x = rand.nextInt(3) + dimension - 3;
			y = rand.nextInt(dimension);
			
			if(map[x][y] == 0) {
				map[x][y] = 3;
				
				currentIslands++;
			}
		}
		
		currentIslands = 0;
		
		while(currentIslands < paddleCount) {
			x = rand.nextInt(dimension);
			y = rand.nextInt(dimension);
			
			if(map[x][y] == 0) {
				map[x][y] = 4;
				
				currentIslands++;
			}
		}
		currentIslands = 0;
		
		while(currentIslands < rocketCount) {
			x = rand.nextInt(dimension);
			y = rand.nextInt(dimension);
			
			if(map[x][y] == 0) {
				map[x][y] = 5;
				
				currentIslands++;
			}
		}
		
		currentIslands = 0;
		
		
		while(currentIslands < boozeCount) {
			x = rand.nextInt(dimension);
			y = rand.nextInt(dimension);
			
			if(map[x][y] == 0) {
				map[x][y] = 6;
				
				currentIslands++;
			}
		}*/		
	}
	
	public void spawnIslands(String type, int Count) {
		int num, x, y;
		int currentIslands = 0;
		
		if(type.equals("island")) 
			num = 1;
		else if(type.equals("pirateIsland"))
			num = 2;
		else if(type.equals("treasureIsland"))
			num = 3;
		else if(type.equals("paddle"))
			num = 4;
		else if(type.equals("rocket"))
			num = 5;
		else
			num = 6;
		
		while(currentIslands < Count) {
			if(type.equals("treasureIsland")) 
				x = rand.nextInt(3) + dimension -3;
			else 
				x = rand.nextInt(dimension);
			
			y = rand.nextInt(dimension);
				
			if(map[x][y] == 0) {
				map[x][y] = num;
				
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
	
	public int getDimension() {
		return dimension;
	}
}
