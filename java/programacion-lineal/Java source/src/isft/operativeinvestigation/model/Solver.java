package isft.operativeinvestigation.model;

import istf.math.Line;
import istf.math.Point;

import java.util.Set;

public interface Solver {

    void addLine(Line line);

    Point getBest(Set<Point> points, double x, double y);

    double getBestValue(Set<Point> points, double x, double y);

    Set<Point> findCandidatesPoints();

    void reset();
}
