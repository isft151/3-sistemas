package isft.operative_investig.model;

import istf.Math.Point;

public class Line {

	private double xValue;
	private double yValue;
	private double limit;

	public double getX() {
		return xValue;
	}

	public double getY() {
		return yValue;
	}

	public double getLimit() {
		return limit;
	}

	public Line(double x, double y, double limit) {
		this.yValue = limit / y;
		this.xValue = limit / x;
		this.limit = limit;
	}

	public Point getIntersection(Line secondLine) {
		double aCoef = yValue / xValue;
		double bCoef = secondLine.getY() / secondLine.getX();

		double i = yValue - secondLine.getY();
		double cCoef = aCoef - bCoef;

		double xcoef = i / cCoef;

		double dcoef = aCoef * xcoef;
		double yCoef = yValue - dcoef;

		return new Point(xcoef, yCoef);
	}

	public boolean isAbovePoint(Point point) {
		boolean above = point.getY() < evaluate(point.getX());
		return contains(point) || above; 
	}

	public double evaluate(double value) {
		return yValue - ((double) yValue * value) / xValue;
	}

	public boolean contains(Point point) {
		return point.getY() == evaluate(point.getX());
	}

}
