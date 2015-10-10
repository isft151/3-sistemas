package isft.operativeinvestigation.model.filter;

import istf.math.Line;
import istf.math.Point;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PointsFilterer {

    public Set<Point> findCandidatesPoints(List<Line> lines, PointFilter filter) {
        Set<Point> candidates = getCriticalPoints(lines);

        return filterInfeasible(candidates, lines, filter);
    }

    private Set<Point> getCriticalPoints(List<Line> lines) {
        Set<Point> points = new HashSet<Point>();

        for (Line line : lines) {
            List<Line> otherLines = new ArrayList<Line>(lines);
            otherLines.remove(line);
            for (Line other : otherLines) {
                Point point = line.getIntersection(other);
                if (point.isInFirstQuadrant()) {
                    points.add(point);
                }
            }
            points.add(new Point(0, line.getY()));
            points.add(new Point(line.getX(), 0));
        }

        return points;
    }

    private Set<Point> filterInfeasible(Set<Point> source, List<Line> lines, PointFilter filter) {
        Set<Point> result = new HashSet<Point>();

        for (Point p : source) {
            boolean valid = true;
            for (Line line : lines) {
                valid = valid && filter.evaluate(p, line);
            }
            if (valid) {
                result.add(p);
            }
        }

        return result;
    }

}
