package columbus;

import java.awt.Point;
import java.util.HashMap;
import java.util.LinkedList;

import sun.misc.Queue;

public class SmartPursuit implements PursuitStrategy {
	
	OceanMap oceanMap;
	int[][] map;
	
	public SmartPursuit() {
		oceanMap = OceanMap.getInstance();
		map = oceanMap.getMap();
	}
	
	String smartPersuit(Point start, Point end) {
		HashMap<Point, String> firstMove = new HashMap<Point, String>();
		Queue<Point> queue = new Queue<Point>();
		boolean[][] visited = new boolean[oceanMap.dimension][oceanMap.dimension];
		
		visited[start.x][start.y] = true;
		
		for(Point neighbor : getNeighbors(start, firstMove)) {
			queue.enqueue(neighbor);
			visited[neighbor.x][neighbor.y] = true;
		}
		
		while(!queue.isEmpty()) {
			Point point = null;
			
			try {
				point = queue.dequeue();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			visited[point.x][point.y] = true;
			
			if(point.equals(end)) break;
			
			for(Point neighbor : getNeighbors(point, firstMove)) {
				if(visited[neighbor.x][neighbor.y] == false) {
					queue.enqueue(neighbor);
					visited[neighbor.x][neighbor.y] = true;
				}
			}
		}
		
		return firstMove.get(end);
	}
	
	public LinkedList<Point> getNeighbors(Point point, HashMap<Point, String> firstMove) {
		int[][] map = oceanMap.getMap();
		LinkedList<Point> neighbors = new LinkedList<Point>();
		
		Point up, down, left, right;
		
		up = new Point(point.x, point.y - 1);
		down = new Point(point.x, point.y + 1);
		left = new Point(point.x - 1, point.y);
		right = new Point(point.x + 1, point.y);
		
		if(checkValidSquare(left)) {
			neighbors.add(left);

			if(firstMove.get(point) == null)
				firstMove.put(left, "LEFT");
			else
				firstMove.put(left, firstMove.get(point));
		}
		
		if(checkValidSquare(right)) {
			neighbors.add(right);

			if(firstMove.get(point) == null)
				firstMove.put(right, "RIGHT");
			else
				firstMove.put(right, firstMove.get(point));
		}

		if(checkValidSquare(up)) {
			neighbors.add(up);

			if(firstMove.get(point) == null)
				firstMove.put(up, "UP");
			else
				firstMove.put(up, firstMove.get(point));
		}
		
		if(checkValidSquare(down)) {
			neighbors.add(down);

			if(firstMove.get(point) == null)
				firstMove.put(down, "DOWN");
			else
				firstMove.put(down, firstMove.get(point));
		}
	
		return neighbors;
	}
	
	public boolean checkValidSquare(Point point) {
		int dimension = oceanMap.getDimension() - 1;
		
		if(point.x > dimension || point.x < 0) return false;
		if(point.y > dimension || point.y < 0) return false;
		
		if(map[point.x][point.y] != 0) return false;
		
		return true;
	}

	public String decideMove(Point location, Point otherLocation) {
		String move = smartPersuit(location, otherLocation);
		
		return move;	
	}
}
