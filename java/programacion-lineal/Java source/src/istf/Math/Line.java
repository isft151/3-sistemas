package istf.math;


public class Line {

    private double xValue;
    private double yValue;
    private double limit;

    public Line(double x, double y, double limit) {
        this.yValue = limit / y;
        this.xValue = limit / x;
        this.limit = limit;
    }

    public double getX() {
        return xValue;
    }

    public double getY() {
        return yValue;
    }

    public double getLimit() {
        return limit;
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
        return point.getY() < evaluate(point.getX());
    }
    
    public boolean isBelowPoint(Point point) {
        return point.getY() > evaluate(point.getX());
    }

    public double evaluate(double value) {
        return yValue - yValue * value / xValue;
    }

    public boolean contains(Point point) {
        return point.getY() == evaluate(point.getX());
    }

}
