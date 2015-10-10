package isft.operative_investig.controller;

import isft.operative_investig.graph.Graph;
import isft.operative_investig.model.Line;
import isft.operative_investig.model.Solver;
import isft.operative_investig.view.GraphView;
import isft.operative_investig.view.Result;
import istf.Math.Point;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

public class Controller {

	private Graph graph;
	private Solver solver;
	private String bestPoint;
	private String profit;

	public Controller(Solver resolver, Graph graph) {
		this.graph = graph;
		this.solver = resolver;
	}

	public void addLine(Line line, String description) {
		graph.addLine(line, description);
		solver.addLine(line);
	}

	public void addFeasiblePointsToGraph(Set<Point> points) {
		Iterator<Point> iterator = points.iterator();
		while (iterator.hasNext()) {
			graph.addFeasiblePoint(iterator.next());
		}
	}

	public void getGraph(GraphView view, int width, int height) {
		Result result = new Result();
		result.graphIcon = graph.getGraph(width, height);
		result.point = "El punto óptimo es " + bestPoint;

		result.profit = "El máximo beneficio es " + profit;
		view.showGraph(result);
	}

	public void processData(Object[][] inputValues, int rowsCount,
			double xValue, double yValue) {

		reset();

		insertLines(inputValues, rowsCount);

		Set<Point> candidates = solver.findCandidatesPoints();

		addFeasiblePointsToGraph(candidates);

		Point best = solver.getBest(candidates, xValue, yValue);

		graph.setBestPoint(best);

		bestPoint = best.toString();
		
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
		DecimalFormat format = new DecimalFormat("0.00", symbols);
		profit = format.format(solver.getBestValue(candidates, xValue, yValue));
	}

	private void reset() {
		graph = graph.reset();
		solver.reset();
	}

	private void insertLines(Object[][] inputValues, int rowsCount) {
		for (int i = 0; i < rowsCount; i++) {
			String name = (String) inputValues[i][0];
			double x = Double.parseDouble((String) inputValues[i][1]);
			double y = Double.parseDouble((String) inputValues[i][2]);
			double limit = Double.parseDouble((String) inputValues[i][3]);

			Line line = new Line(x, y, limit);
			addLine(line, name);
		}
	}
}
