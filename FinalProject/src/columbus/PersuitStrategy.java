package columbus;

import java.awt.Point;

public interface PersuitStrategy {
	public String decideMove(Point location, Point otherLocation);
}
