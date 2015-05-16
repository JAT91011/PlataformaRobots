package gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import core.Globals;
import core.entities.RPoint;

public class LabelIntersections extends JLabel {

	private static final long	serialVersionUID	= -2221913201429539122L;
	private Dimension			size				= new Dimension(Globals.CAMERA_WIDTH, Globals.CAMERA_HEIGHT);
	private Vector<RPoint>		ReferencePoints;

	public LabelIntersections(ImageIcon image) {
		super(image);
		this.ReferencePoints = new Vector<RPoint>();
		this.setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
	}

	public void updateReferencePoints(Vector<RPoint> ReferencePoints) {
		this.ReferencePoints = ReferencePoints;
		super.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(0, 255, 0));
		for (RPoint p : ReferencePoints) {
			g.fillRect(p.x - p.getMargin() / 2, p.y - p.getMargin() / 2, p.getMargin(), p.getMargin());
		}
	}
}