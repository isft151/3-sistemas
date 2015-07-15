package isft.operativeinvestigation.view;

import isft.operativeinvestigation.controller.Controller;
import isft.operativeinvestigation.graph.Graph;

public class Main {

    private Main() {

    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        Controller controller = new Controller(graph);
        MainForm frame = new MainForm(controller,false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
