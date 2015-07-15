package isft.operativeinvestigation.model.filter;

import istf.math.Line;
import istf.math.Point;

public class AboveFilter implements PointFilter {

    @Override
    public boolean evaluate(Point point, Line line) {
        return line.isAbovePoint(point) || line.contains(point);
    }
}
