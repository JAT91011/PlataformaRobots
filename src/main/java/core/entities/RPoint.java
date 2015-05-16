package core.entities;

import java.awt.Point;
import java.awt.Rectangle;

public class RPoint extends Point {

	private static final long	serialVersionUID	= 8853305016580156841L;
	private Rectangle			rectangle;
	private int					margin;

	public RPoint(int x, int y, int margin) {
		super(x, y);
		this.margin = margin;
		if (margin > 0) {
			this.rectangle = new Rectangle(x - margin / 2, y - margin / 2, margin, margin);
		}
	}

	public Rectangle getRectangle() {
		return this.rectangle;
	}

	public boolean collision(RPoint point) {
		return this.rectangle.intersects(point.rectangle);
	}

	public int getMargin() {
		return this.margin;
	}

	public String toString() {
		return "[" + super.x + " , " + super.y + "]";
	}

}