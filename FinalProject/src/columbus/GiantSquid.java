package columbus;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GiantSquid implements Monster{

	int x;
	int y;
	Circle circle;
	int scalingFactor;
	int radius = 5;
	OceanMap oceanMap;
	Random random = new Random();
	
	//constructor 
	public GiantSquid(int x, int y, int scalingFactor) {
		this.x = x;
		this.y = y;
		circle= new Circle();
		setPositionX(x);
		setPositionY(y);
		circle.setRadius(radius);
		this.scalingFactor = scalingFactor;
		circle.setFill(Color.RED);
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
		if (xMove >=0 && xMove <= 30)
			setX(xMove);
		// Move Y
		int yMove = getY() + random.nextInt(3)-1;
		if (yMove >=0 && yMove <=30)
			setY(yMove);
	}
}
