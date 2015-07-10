package isft.operative_investig.model;

import istf.Math.Point;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Maximizer implements Solver{

	private List<Line> lines = new ArrayList<>();

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

		System.out.println();
		System.out.println("maximo beneficio: " + maxValue);
		System.out.println("punto óptimo: " + maximum);
		return maximum;
	}
	
	@Override
	public double getBestValue(Set<Point> points, double x, double y){//TODO optimizar
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
		Set<Point> candidates = getCriticalPoints();

		return filterInfeasible(candidates);
	}

	@Override
	public Set<Point> filterInfeasible(Set<Point> source) {
		Set<Point> result = new HashSet<Point>();

		for (Point p : source) {
			boolean valid = true;
			for (Line line : lines) {
				valid = valid && line.isAbovePoint(p);
			}
			if (valid) {
				result.add(p);
			}
		}
		
		for (Point p : result){
			System.out.println(p);
		}

		return result;
	}

	@Override
	public Set<Point> getCriticalPoints() {

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

	@Override
	public void reset() {
		lines = new ArrayList<Line>();
	}

	@Override
	public Set<Line> find(Point p) {
		Set<Line> result = new HashSet<Line>();
		for (Line line : lines) {
			if (line.contains(p)) {
				result.add(line);
			}
		}
		return result;
	}
}
