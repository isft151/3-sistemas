package isft.operativeinvestigation.model.filter;

import istf.math.Line;
import istf.math.Point;

public class BelowFilter implements PointFilter {

    @Override
    public boolean evaluate(Point point, Line line) {
        return line.isBelowPoint(point) || line.contains(point);
    }

}
