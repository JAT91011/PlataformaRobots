package core.entities;

import java.util.Vector;

public class Route {

	public static int		LEFT	= 0;
	public static int		RIGHT	= 1;

	private String			name;
	private Vector<RPoint>	steps;
	private Vector<Integer>	direction;
	private boolean			loop;

	public Route() {

	}

	public Route(String name) {
		this.name = name;
		this.steps = new Vector<RPoint>();
		this.direction = new Vector<Integer>();
		this.loop = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector<RPoint> getSteps() {
		return steps;
	}

	public void setSteps(Vector<RPoint> steps) {
		this.steps = steps;
	}

	public Vector<Integer> getDirection() {
		return direction;
	}

	public void setDirection(Vector<Integer> direction) {
		this.direction = direction;
	}

	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	@Override
	public String toString() {
		return this.name;
	}
}