package isft.operativeinvestigation.model;

import isft.operativeinvestigation.model.filter.AboveFilter;
import isft.operativeinvestigation.model.filter.PointsFilterer;
import istf.math.Line;
import istf.math.Point;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Maximizer implements Solver {

    private List<Line> lines = new ArrayList<>();

    Maximizer() {

    }

    @Override
    public void addLine(Line line) {
        lines.add(line);
    }

    @Override
    public Point getBest(Set<Point> points, double x, double y) {
        Iterator<Point> iterator = points.iterator();
        Point maximum = null;
        double maxValue = 0;

        while (iterator.hasNext()) {
            Point p = iterator.next();
            double value = x * p.getX() + y * p.getY();
            if (value > maxValue) {
                maxValue = value;
                maximum = p;
            }
        }

        return maximum;
    }

    @Override
    public double getBestValue(Set<Point> points, double x, double y) {
        Iterator<Point> iterator = points.iterator();
        double maxValue = 0;

        while (iterator.hasNext()) {
            Point p = iterator.next();
            double value = x * p.getX() + y * p.getY();
            if (value > maxValue) {
                maxValue = value;
            }
        }

        return maxValue;
    }

    @Override
    public Set<Point> findCandidatesPoints() {
        PointsFilterer filterer = new PointsFilterer();
        AboveFilter filter = new AboveFilter();
        
        return filterer.findCandidatesPoints(lines,filter);
    }

    @Override
    public void reset() {
        lines = new ArrayList<Line>();
    }

}
