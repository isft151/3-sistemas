package isft.operativeinvestigation.model;

import isft.operativeinvestigation.model.filter.BelowFilter;
import isft.operativeinvestigation.model.filter.PointsFilterer;
import istf.math.Line;
import istf.math.Point;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Minimizer implements Solver {

    private List<Line> lines = new ArrayList<>();

    Minimizer() {
    }

    @Override
    public void addLine(Line line) {
        lines.add(line);
    }

    @Override
    public Point getBest(Set<Point> points, double x, double y) {
        Iterator<Point> iterator = points.iterator();
        Point minimum = iterator.next();
        double minValue = getValue(minimum, x, y);

        while (iterator.hasNext()) {
            Point p = iterator.next();
            double value = getValue(p, x, y);
            if (value < minValue) {
                minValue = value;
                minimum = p;
            }
        }

        return minimum;
    }
    
    private double getValue(Point p, double x, double y){
        return x * p.getX() + y * p.getY();
    }

    @Override
    public double getBestValue(Set<Point> points, double x, double y) {
        Iterator<Point> iterator = points.iterator();
        Point minimum = iterator.next();
        double minValue = getValue(minimum, x, y);

        while (iterator.hasNext()) {
            Point p = iterator.next();
            double value = getValue(p, x, y);
            if (value < minValue) {
                minValue = value;
                minimum = p;
            }
        }

        return minValue;
    }

    @Override
    public Set<Point> findCandidatesPoints() {
        PointsFilterer filterer = new PointsFilterer();
        BelowFilter filter = new BelowFilter();
        
        return filterer.findCandidatesPoints(lines,filter);
    }

    @Override
    public void reset() {
        lines = new ArrayList<Line>();
    }

}
