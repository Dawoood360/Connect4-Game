package com.Tree;

import com.company.TreeNode;
import com.fxgraph.cells.RectangleCell;
import com.fxgraph.cells.TriangleCell;
import com.fxgraph.edges.CorneredEdge;
import com.fxgraph.edges.CorneredLoopEdge;
import com.fxgraph.edges.CorneredLoopEdge.Position;
import com.fxgraph.edges.DoubleCorneredEdge;
import com.fxgraph.edges.Edge;
import com.fxgraph.graph.Graph;
import com.fxgraph.graph.ICell;
import com.fxgraph.graph.Model;
import com.fxgraph.layout.AbegoTreeLayout;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.abego.treelayout.Configuration.Location;


public class BasicGraphDemo extends Application {
	private TreeNode treeNode;

	public void setTreeNode(TreeNode treeNode) {
		this.treeNode = treeNode;
	}



	@Override
	public void start(Stage stage) throws Exception {
		Graph graph = new Graph();

		// Add content to graph
		populateGraph(graph);

		// Layout nodes
		AbegoTreeLayout layout = new AbegoTreeLayout(250, 250, Location.Bottom);
		graph.layout(layout);

		// Configure interaction buttons and behavior
		graph.getViewportGestures().setPanButton(MouseButton.SECONDARY);
		graph.getNodeGestures().setDragButton(MouseButton.PRIMARY);

		// Display the graph
		stage.setScene(new Scene(new BorderPane(graph.getCanvas())));
		stage.show();
	}

	private void populateGraph(Graph graph) {
		final Model model = graph.getModel();                   //1    2 3 4 
                                                                        //2 -> 17 18 19
                                                                        //3 -> 20 21 22
                                                                        //4 -> 5
		graph.beginUpdate();
		final ICell cellA = new RectangleCell("1");
		final ICell cellB = new RectangleCell("2");
		final ICell cellC = new RectangleCell("3");
		final ICell cellD = new RectangleCell("4");
		final ICell cellE = new RectangleCell("5");
		final ICell cellF = new RectangleCell("6");
		final ICell cellG = new RectangleCell("7");
		final ICell cellH = new TriangleCell("9");
		final ICell cellY = new TriangleCell("19");

		model.addCell(cellA);
		model.addCell(cellB);
		model.addCell(cellC);
		model.addCell(cellY);
		model.addCell(cellD);
		model.addCell(cellE);
		model.addCell(cellF);
		model.addCell(cellG);
                model.addCell(cellH);


		final Edge edgeAB = new Edge(cellA, cellB, true);
		edgeAB.textProperty().set("Directed Edge");
		model.addEdge(edgeAB);
		final CorneredEdge edgeAY = new CorneredEdge(cellA, cellY, true,Orientation.HORIZONTAL);
		edgeAB.textProperty().set("Directed Edge");
		model.addEdge(edgeAY);

		final CorneredEdge edgeAC = new CorneredEdge(cellA, cellC, true, Orientation.HORIZONTAL);
		edgeAC.textProperty().set("Directed CorneredEdge");
		model.addEdge(edgeAC);

		final DoubleCorneredEdge edgeBE = new DoubleCorneredEdge(cellB, cellE, true, Orientation.HORIZONTAL);
		edgeBE.textProperty().set("Directed DoubleCorneredEdge");
		model.addEdge(edgeBE);

		final Edge edgeCF = new Edge(cellC, cellF, true);
		edgeCF.textProperty().set("Directed Edge");
		model.addEdge(edgeCF);

		final CorneredLoopEdge loopFTop = new CorneredLoopEdge(cellF, Position.TOP);
		loopFTop.textProperty().set("Loop top");
		model.addEdge(loopFTop);

		model.addEdge(cellC, cellG);

		model.addEdge(cellB, cellD);

		graph.endUpdate();
	}
}
