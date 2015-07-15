package isft.operativeinvestigation.model.filter;

import istf.math.Line;
import istf.math.Point;

public interface PointFilter {
    boolean evaluate(Point point, Line line);
}
