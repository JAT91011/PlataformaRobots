package core.utils;

import java.awt.Color;
import java.awt.Point;
import java.util.Arrays;
import java.util.Vector;

import core.Globals;

public class Utilities {

	public static final int	TYPE_STRING		= 0;
	public static final int	TYPE_INTEGER	= 1;
	public static final int	TYPE_DOUBLE		= 2;

	public static Point[] ReorderRectangleCorners(Point[] points) {
		Vector<Point> OriginalPoints = new Vector<Point>(Arrays.asList(points));
		Point[] reorderedPoints = new Point[4];
		Point[] aux = new Point[2];
		aux[0] = new Point(Globals.CAMERA_WIDTH, Globals.CAMERA_HEIGHT);
		aux[1] = new Point(Globals.CAMERA_WIDTH, Globals.CAMERA_HEIGHT);

		// Esquina superior izquierda --> Menor X y Menor Y
		for (Point p : OriginalPoints) {
			if (p.x < aux[1].x) {
				if (p.x < aux[0].x) {
					aux[1] = aux[0];
					aux[0] = p;
				} else {
					aux[1] = p;
				}
			}
		}
		if (aux[0].y < aux[1].y) {
			reorderedPoints[0] = aux[0];
			reorderedPoints[3] = aux[1];
		} else {
			reorderedPoints[0] = aux[1];
			reorderedPoints[3] = aux[0];
		}
		OriginalPoints.remove(aux[0]);
		OriginalPoints.remove(aux[1]);

		if (OriginalPoints.get(0).y < OriginalPoints.get(1).y) {
			reorderedPoints[1] = OriginalPoints.get(0);
			reorderedPoints[2] = OriginalPoints.get(1);
		} else {
			reorderedPoints[2] = OriginalPoints.get(0);
			reorderedPoints[1] = OriginalPoints.get(1);
		}
		return reorderedPoints;
	}

	public static Color[] GetMinimumMaximumMediumColor(Vector<Color> pColors) {
		Color[] colors = new Color[3];
		int[] rgbMin = new int[3];
		int[] rgbMax = new int[3];
		try {
			// MINIMUM AND MAXIMUM
			for (Color color : pColors) {
				// RED
				if (color.getRed() < rgbMin[0]) {
					rgbMin[0] = color.getRed();
				}
				if (color.getRed() > rgbMax[0]) {
					rgbMax[0] = color.getRed();
				}
				// GREEN
				if (color.getGreen() < rgbMin[1]) {
					rgbMin[1] = color.getGreen();
				}
				if (color.getGreen() > rgbMax[1]) {
					rgbMax[1] = color.getGreen();
				}
				// BLUE
				if (color.getBlue() < rgbMin[2]) {
					rgbMin[2] = color.getBlue();
				}
				if (color.getBlue() > rgbMax[2]) {
					rgbMax[2] = color.getBlue();
				}
			}
			colors[0] = new Color(rgbMin[0], rgbMin[1], rgbMin[2]);
			colors[1] = new Color(rgbMax[0], rgbMax[1], rgbMax[2]);
			colors[2] = new Color((rgbMax[0] + rgbMin[0]) / 2,
					(rgbMax[1] + rgbMin[1]) / 2, (rgbMax[2] + rgbMin[2]) / 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return colors;
	}

	public static boolean ColorBetweenMaxMin(Color pColor, Color pColorMin,
			Color pColorMax, int pMargin) {
		try {
			return (pColor.getRed() > pColorMin.getRed() - pMargin && pColor
					.getRed() < pColorMax.getRed() + pMargin)
					&& (pColor.getGreen() > pColorMin.getGreen() - pMargin && pColor
							.getGreen() < pColorMax.getGreen() + pMargin)
					&& (pColor.getBlue() > pColorMin.getBlue() - pMargin && pColor
							.getBlue() < pColorMax.getBlue() + pMargin);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String FillString(String text, char fillCharacter,
			int length, int typeData) {
		try {
			String result = "";
			if (text.length() > length) {
				switch (typeData) {
					case TYPE_STRING:
						result = text.substring(0, length);
						break;
					case TYPE_INTEGER:
					case TYPE_DOUBLE:
						result = text.substring(text.length() - length, length);
						break;
				}
			} else {
				String fillText = "";
				for (int i = text.length(); i < length; i++) {
					fillText += fillCharacter;
				}
				switch (typeData) {
					case TYPE_STRING:
						result = text + fillText;
						break;
					case TYPE_INTEGER:
					case TYPE_DOUBLE:
						result = fillText + text;
						break;
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String firstToUpper(final String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
}
