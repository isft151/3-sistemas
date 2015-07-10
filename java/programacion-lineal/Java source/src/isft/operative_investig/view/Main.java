package isft.operative_investig.view;

import isft.operative_investig.controller.Controller;
import isft.operative_investig.graph.Graph;
import isft.operative_investig.model.Maximizer;
import isft.operative_investig.model.Solver;

public class Main {

	public static void main(String[] args) {
		Solver solver = new Maximizer();
		Graph graph = new Graph();
		Controller controller = new Controller(solver, graph);
		MainForm frame = new MainForm(controller);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
