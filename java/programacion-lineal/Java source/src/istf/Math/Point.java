package istf.math;

import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Point extends Point2D {

    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
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

    @Override
    public boolean equals(Object obj) {
        Point point = (Point) obj;
        DoubleComparator comparator = new DoubleComparator();
        return comparator.areEquals(this.getX(), point.getX())
                && comparator.areEquals(this.getY(), point.getY());
    }

    @Override
    public int hashCode() {
        int code = java.lang.Double.hashCode(x);
        code += java.lang.Double.hashCode(y);
        return code;
    }

    public boolean isInFirstQuadrant() {
        return x >= 0 && y >= 0;
    }

}
