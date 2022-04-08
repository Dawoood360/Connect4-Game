package com.fxgraph.cells;

import com.fxgraph.graph.Graph;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;

public class TriangleCell extends AbstractCell {
        private String number;

    
        public TriangleCell(String string){
            this.number = string;
        }
    
        public void setNumber(String number) {
            this.number = number;
        }
        
	public TriangleCell() {
	}

	@Override
	public Region getGraphic(Graph graph) {
		final double width = 50;
		final double height = 50;

		//final Polygon view = new Polygon(width / 2, 0, width, height, 0, height);
                final Polygon view = new Polygon(width/2,height,width,0,0,0);
                
		view.setStroke(Color.RED);
		view.setFill(Color.RED);

                Text text = new Text(number);
                
		final StackPane pane = new StackPane(view);
                pane.getChildren().add(text);
                pane.setAlignment(text,Pos.CENTER);
		pane.setPrefSize(50, 50);
		final Scale scale = new Scale(1, 1);
		view.getTransforms().add(scale);
		scale.xProperty().bind(pane.widthProperty().divide(50));
		scale.yProperty().bind(pane.heightProperty().divide(50));
		//CellGestures.makeResizable(graph, pane);

		return pane;
	}

}