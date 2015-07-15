package isft.operativeinvestigation.controller;

import isft.operativeinvestigation.graph.Graph;
import isft.operativeinvestigation.model.OperationType;
import isft.operativeinvestigation.model.Solver;
import isft.operativeinvestigation.model.SolverFactory;
import isft.operativeinvestigation.view.GraphView;
import isft.operativeinvestigation.view.Result;
import istf.math.Line;
import istf.math.Point;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

public class Controller {

    private Graph graph;
    private String bestPoint;
    private String profit;

    public Controller(Graph graph) {
        this.graph = graph;
    }

    public void addFeasiblePointsToGraph(Set<Point> points) {
        Iterator<Point> iterator = points.iterator();
        while (iterator.hasNext()) {
            graph.addFeasiblePoint(iterator.next());
        }
    }

    public void getGraph(GraphView view, int width, int height) {
        Result result = new Result();
        result.setGraphIcon(graph.getGraph(width, height));
        result.setPoint("El punto óptimo es " + bestPoint);

        result.setProfit("El máximo beneficio es " + profit);

        view.showGraph(result);
    }

    public void processData(Object[][] inputValues, int rowsCount,
            double xValue, double yValue, OperationType operation) {

        Solver solver = (operation == OperationType.MAXIMIZATION) ? SolverFactory
                .createMaximizer() : SolverFactory.createMinimizer();

        graph = graph.reset();

        insertLines(inputValues, rowsCount, solver);

        Set<Point> candidates = solver.findCandidatesPoints();

        addFeasiblePointsToGraph(candidates);

        Point best = solver.getBest(candidates, xValue, yValue);

        graph.setBestPoint(best);

        bestPoint = best.toString();

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat format = new DecimalFormat("0.00", symbols);
        profit = format.format(solver.getBestValue(candidates, xValue, yValue));
    }

    private void insertLines(Object[][] inputValues, int rowsCount,
            Solver solver) {
        for (int i = 0; i < rowsCount; i++) {
            String name = (String) inputValues[i][0];
            double x = Double.parseDouble((String) inputValues[i][1]);
            double y = Double.parseDouble((String) inputValues[i][2]);
            double limit = Double.parseDouble((String) inputValues[i][3]);

            Line line = new Line(x, y, limit);
            graph.addLine(line, name);
            solver.addLine(line);
        }
    }

}
