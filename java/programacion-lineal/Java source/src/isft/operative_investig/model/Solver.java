package isft.operative_investig.model;

import istf.Math.Point;

import java.util.Set;

public interface Solver {


	void addLine(Line line);
	
	Point getBest(Set<Point> points, double x, double y);
	
	double getBestValue(Set<Point> points, double x, double y);

	Set<Point> findCandidatesPoints();

	Set<Point> filterInfeasible(Set<Point> source);

	Set<Point> getCriticalPoints();

	void reset();

	Set<Line> find(Point p);
}
