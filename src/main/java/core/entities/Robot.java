package core.entities;

import java.awt.Color;

public class Robot {

	public static int	MODE_RANDOM	= 0;
	public static int	MODE_ROUTE	= 1;
	public static int	MODE_BROKEN	= 2;

	private String		name;
	private Color		color;
	private int			mode;
	private RPoint		lastReferencePoint;
	private Color		colorMin;
	private Color		colorMax;
	private Route		route;

	public Robot() {

	}

	public Robot(String name, Color color) {
		this.name = name;
		this.color = color;
		this.mode = MODE_RANDOM;
		this.colorMin = new Color(0, 0, 0);
		this.colorMax = new Color(0, 0, 0);
		this.setRoute(null);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColorMin() {
		return colorMin;
	}

	public void setColorMin(Color colorMin) {
		this.colorMin = colorMin;
	}

	public Color getColorMax() {
		return colorMax;
	}

	public void setColorMax(Color colorMax) {
		this.colorMax = colorMax;
	}

	public RPoint getLastReferencePoint() {
		return lastReferencePoint;
	}

	public void setLastReferencePoint(RPoint lastReferencePoint) {
		this.lastReferencePoint = lastReferencePoint;
	}

	public boolean isInReferencePoint(RPoint point) {
		return true;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}
}