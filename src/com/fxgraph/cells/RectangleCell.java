package com.fxgraph.cells;

import com.fxgraph.graph.Graph;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class RectangleCell extends AbstractCell {

    private String number;

    public RectangleCell() {
        
    }
    
    public RectangleCell(String string){
        this.number = string;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
        
    @Override
    public Region getGraphic(Graph graph) {
        final Rectangle view = new Rectangle(50, 50);

	view.setStroke(Color.DODGERBLUE);
	view.setFill(Color.DODGERBLUE);
                
        Text text = new Text(number);
                
                
                
	final StackPane pane = new StackPane(view);
        pane.getChildren().add(text);
        pane.setAlignment(text,Pos.CENTER);
	pane.setPrefSize(50, 50);
	view.widthProperty().bind(pane.prefWidthProperty());
	view.heightProperty().bind(pane.prefHeightProperty());
	//CellGestures.makeResizable(graph, pane);

	return pane;
    }
}
