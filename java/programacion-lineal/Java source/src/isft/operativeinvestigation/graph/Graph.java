package isft.operativeinvestigation.graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYAreaRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.ShapeUtilities;

import istf.math.Line;
import istf.math.Point;

public class Graph {
    private XYSeriesCollection linesData = new XYSeriesCollection();
    private int linesNumber = 0;
    private Point bestPoint = null;
    private XYSeries areaData = new XYSeries("Area Factible");
    private String xLabel = null;
    private String yLabel = null;

    public void addLine(Line line, String description) {
        XYSeries points = new XYSeries(description);
        points.add(line.getX(), 0);
        points.add(0, line.getY());

        linesNumber++;
        linesData.addSeries(points);
    }

    public void setBestPoint(Point point) {
        this.bestPoint = point;
    }

    public void addFeasiblePoint(Point point) {
        areaData.add(point.getX(), point.getY());
    }

    public void setAxisNames(String xName, String yName) {
        this.xLabel = xName;
        this.yLabel = yName;
    }

    public Icon getGraph(int width, int height) {
        XYPlot plot = new XYPlot();
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinesVisible(false);
        plot.setRangeGridlinesVisible(false);

        XYItemRenderer linesRenderer = new XYLineAndShapeRenderer(true, false);
        Stroke stroke = new BasicStroke(2);

        for (int i = 0; i < linesNumber; i++) {
            linesRenderer.setSeriesStroke(i, stroke);
        }

        ValueAxis domain2 = new NumberAxis(xLabel);
        domain2.setVisible(false);
        ValueAxis range2 = new NumberAxis(yLabel);
        range2.setVisible(false);
        plot.setDataset(1, linesData);
        plot.setRenderer(1, linesRenderer);
        plot.setDomainAxis(1, domain2);
        plot.setRangeAxis(1, range2);
        plot.mapDatasetToDomainAxis(1, 1);
        plot.mapDatasetToRangeAxis(1, 1);

        XYSeries points = new XYSeries("Punto Óptimo");
        points.add(bestPoint.getX(), bestPoint.getY());
        XYDataset collection1 = new XYSeriesCollection(points);

        XYItemRenderer pointRenderer = new XYLineAndShapeRenderer(false, true);
        pointRenderer.setSeriesShape(0,
                ShapeUtilities.createDiagonalCross(3, 1));
        pointRenderer.setSeriesPaint(0, Color.red);
        ValueAxis domain1 = new NumberAxis(xLabel);
        domain1.setRange(domain2.getRange());

        ValueAxis range1 = new NumberAxis(yLabel);
        range1.setRange(range2.getRange());

        plot.setDataset(0, collection1);
        plot.setRenderer(0, pointRenderer);
        plot.setDomainAxis(0, domain1);
        plot.setRangeAxis(0, range1);

        plot.mapDatasetToDomainAxis(1, 1);
        plot.mapDatasetToRangeAxis(1, 1);

        XYDataset collection3 = new XYSeriesCollection(areaData);

        XYItemRenderer areaRenderer = new XYAreaRenderer();

        areaRenderer.setSeriesPaint(0, new Color(0,160,0));

        ValueAxis domain3 = new NumberAxis();
        domain3.setRange(domain2.getRange());
        domain3.setVisible(false);
        ValueAxis range3 = new NumberAxis();
        range3.setRange(range2.getRange());
        range3.setVisible(false);

        plot.setDataset(2, collection3);
        plot.setRenderer(2, areaRenderer);
        plot.setDomainAxis(2, domain3);
        plot.setRangeAxis(2, range3);

        plot.mapDatasetToDomainAxis(1, 1);
        plot.mapDatasetToRangeAxis(1, 1);

        JFreeChart chart = new JFreeChart(plot);
        BufferedImage graficoLinea = chart.createBufferedImage(width, height);
        return new ImageIcon(graficoLinea);
    }

    public Graph reset() {
        return new Graph();
    }

}
