package columbus;

import java.awt.Point;
import java.util.HashMap;
import java.util.LinkedList;

import sun.misc.Queue;

public class SmartPursuit implements PursuitStrategy {
	
	OceanMap oceanMap;
	
	public SmartPursuit() {
		oceanMap = OceanMap.getInstance();
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
		
		if(point.x > 0) {
			if(map[point.x - 1][point.y] == 0) {
				neighbors.add(new Point(point.x - 1, point.y));

				if(firstMove.get(point) == null)
					firstMove.put(new Point(point.x - 1, point.y), "LEFT");
				else
					firstMove.put(new Point(point.x - 1, point.y), firstMove.get(point));
			}
		}
		if(point.x < oceanMap.dimension - 1) {
			if(map[point.x + 1][point.y] == 0) {
				neighbors.add(new Point(point.x + 1, point.y));

				if(firstMove.get(point) == null)
					firstMove.put(new Point(point.x + 1, point.y), "RIGHT");
				else
					firstMove.put(new Point(point.x + 1, point.y), firstMove.get(point));
			}
		}
		if(point.y > 0) {
			if(map[point.x][point.y - 1] == 0) {
				neighbors.add(new Point(point.x, point.y - 1));

				if(firstMove.get(point) == null)
					firstMove.put(new Point(point.x, point.y - 1), "UP");
				else
					firstMove.put(new Point(point.x, point.y - 1), firstMove.get(point));
			}
		}
		if(point.y < oceanMap.dimension - 1) {
			if(map[point.x][point.y + 1] == 0) {
				neighbors.add(new Point(point.x, point.y + 1));

				if(firstMove.get(point) == null)
					firstMove.put(new Point(point.x, point.y + 1), "DOWN");
				else
					firstMove.put(new Point(point.x, point.y + 1), firstMove.get(point));
			}
		}
		
		return neighbors;
	}

	public String decideMove(Point location, Point otherLocation) {
		String move = smartPersuit(location, otherLocation);
		
		return move;	
	}
}
