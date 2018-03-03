package columbus;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Shark implements Monster {

	int x;
	int y;
	Circle circle;
	int scalingFactor;
	int radius = 5;
	OceanMap oceanMap;
	Random random  = new Random();
	
	//Constructor
	public Shark(int x, int y, int scalingFactor) {
		this.x = x;
		this.y = y;
		circle= new Circle();
		setPositionX(x);
		setPositionY(y);
		circle.setRadius(radius);
		this.scalingFactor = scalingFactor;
		circle.setFill(Color.BLUE);
		oceanMap = OceanMap.getInstance();
	}
	@Override
	public Circle getCircle() {
		return circle;
	}

	@Override
	public void setX(int x) {
		this.x = x;
		setPositionX(x);
	}

	@Override
	public void setY(int y) {
		this.y = y;
		setPositionY(y);
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setLineColor(Circle circle, Color color){
		circle.setStroke(color);
		circle.setFill(color);
	}
	
	@Override
	public void setPositionX(int x){
		circle.setCenterX(x*scalingFactor + (scalingFactor/2));
	}
	
	@Override
	public void setPositionY(int y){
		circle.setCenterY(y*scalingFactor + (scalingFactor/2));
	}
	
	@Override
	public void move() {
		
		int xMove = getX() + random.nextInt(3)-1;
		int yMove = getY() + random.nextInt(3)-1;
		if(isValidMove(xMove, yMove)) {
			setX(xMove);
			setY(yMove);
		}
	}
	//checks to see if the coordinate is an ocean
	public boolean isValidMove(int x, int y) {
		if(x >= 0 && x < 30)
			if(y>= 0 && y < 30)
				if(oceanMap.getMap()[x][y] == 0)
					return true;
		
		return false;
	}

}
