package core.shapes;

public class Circle {

	private int	x;
	private int	y;
	private int	radius;

	public Circle() {

	}

	public Circle(final int x, final int y) {
		this.x = x;
		this.y = y;
		this.radius = 0;
	}

	public Circle(final int x, final int y, final int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public double getArea() {
		return Math.PI * Math.pow(radius, 2);
	}

	@Override
	public String toString() {
		return "Circle [x=" + x + ", y=" + y + ", radius=" + radius + "]";
	}
}