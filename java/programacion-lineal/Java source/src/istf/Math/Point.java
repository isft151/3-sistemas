package istf.Math;

import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Point extends Point2D {

	private double x;
	private double y;

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
		DecimalFormat format = new DecimalFormat("0.00", symbols);
		return "( " + format.format(x) + " , " + format.format(y) + " )";
	}

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		Point point = (Point) obj;
		return this.getX() == point.getX() && this.getY() == point.getY();
	}

	public boolean isInFirstQuadrant() {
		return x >= 0 && y >= 0;
	}

}
