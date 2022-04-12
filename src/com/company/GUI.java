/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

/**
 *
 * @author abdul
 */

import com.Tree.JGraphAdapterDemo;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.Font; 
import javafx.scene.text.FontPosture; 
import javafx.scene.text.FontWeight;
import javafx.util.Pair;

public class GUI extends Application {

    private static final int TILE_SIZE = 80;
    private static final int COLUMNS = 7;
    private static final int ROWS = 6 ;
    private boolean redMove = false;
    private Disc[][] grid = new Disc[COLUMNS][ROWS];
    private static int filling = 0;
    private static int inDepth = 0;
    private Pane discRoot = new Pane();
    private static int redScore = 0;
    private static int yellowScore = 0;
    private Button draw;
    private Text text;
    private Text depthText;
    private TextField depthInput;
    private ComboBox algorithmCombo;
    private Button restart;
    private TreeNode rootTree=new TreeNode();
    private String algorithm;
    JGraphAdapterDemo jGraphModelAdapter=new JGraphAdapterDemo();
    private MinMax minMax= new MinMax();
        private MinMaxAlphaBeta minMaxAlphaBeta= new MinMaxAlphaBeta();
    private Board board;
    private boolean turn;
    private Board setInitialState(boolean turn)
    {   String initialState = "";
        for (int i = 0; i < 42; i++)
            initialState += "U";

        Board initialBoard=new Board(initialState,turn);
        initialBoard.setDepth(0);
        this.turn=true;
        return initialBoard;
    }
    private Parent createContent() {
        this.board=setInitialState(true);
        Pane root = new Pane();
        root.getChildren().add(discRoot);
        text = makeText();
        depthInput = makeTextInput();
        depthText = makeDepthText();
        algorithmCombo = makeComboBox();
        restart = makeButton();
        draw=makeButton2();
        Shape gridShape = makeGrid();
        
        root.getChildren().add(restart);
        root.getChildren().add(algorithmCombo);
        root.getChildren().add(depthText);
        root.getChildren().add(depthInput);
        root.getChildren().add(text);
        root.getChildren().add(gridShape);
        root.getChildren().addAll(makeColumns());
        root.getChildren().add(draw);
        //root.setMaxSize(500, 500);
        depthInput.setText("2");
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                grid = new Disc[COLUMNS][ROWS];
                filling = 0;
                inDepth = 0;
                redScore = 0;
                yellowScore = 0;
                
                root.getChildren().clear();
                
                discRoot = new Pane();
                resetScore();
                root.getChildren().add(discRoot);
                Shape gridShape = makeGrid();
                root.getChildren().add(restart);
                root.getChildren().add(algorithmCombo);
                root.getChildren().add(depthText);
                root.getChildren().add(depthInput);
                root.getChildren().add(text);
                root.getChildren().add(gridShape);
                root.getChildren().addAll(makeColumns());
                root.getChildren().add(draw);
                
                System.out.println("Game restart");
            }
        };
        EventHandler<ActionEvent> eventTree = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
           {


               jGraphModelAdapter.draw(rootTree,null,true,500,10);
               jGraphModelAdapter.showPane();
               System.out.println(rootTree.toString());
            }
        };
        
        restart.setOnAction(event);
        draw.setOnAction(eventTree);
        return  root;
    }
    
    
    //button.setOnAction( e-> do function)
    
    
    private Button makeButton(){
        Button button = new Button("Restart Game");
        
        button.setLayoutX(680);
        button.setLayoutY(250);
        
        return button;
    }
    private Button makeButton2(){
        Button button = new Button("Plot tree");

        button.setLayoutX(680);
        button.setLayoutY(300);

        return button;
    }
    
    
    private ComboBox makeComboBox(){
        ComboBox type = new ComboBox();
        
        type.getItems().addAll("No Pruning","Pruning");
        
        type.getSelectionModel().selectFirst();
        
        type.setLayoutX(680);
        type.setLayoutY(190);
        
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                algorithm = (String) type.getValue();

            }
        };
        
        type.setOnAction(event);
        
        return type;
    }
    
    private Text makeDepthText(){
        Text score = new Text();
        score.setText("Enter depth:");
        
        score.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
        
        score.setX(680); 
        score.setY(140);
        
        return score;
    }
    
    private TextField makeTextInput(){
        TextField depth = new TextField();
        
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                inDepth = Integer.parseInt(depth.getText());
                System.out.println("Depth is "+ inDepth);
            }
        };
        
        depth.setLayoutX(680);
        depth.setLayoutY(150);
        
        depth.setOnAction(event);
        
        return depth;
    }
    
    private Text makeText(){
        Text score = new Text();
        score.setText("Red:" + redScore +"    "+"Yellow:" + yellowScore);
        
        score.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
        score.setFill(Color.BROWN);
        score.setStrokeWidth(0.5);
        score.setStroke(Color.BLUE);
        
        score.setX(680); 
        score.setY(100);
        
        return score;
    }
    
    private void resetScore(){
        text.setText("Red:" + redScore +"    "+"Yellow:" + yellowScore);
    }
    
    private void updateText(){
        text.setText("Red:" + redScore +"    "+"Yellow:" + yellowScore);
    }
    
    private Shape makeGrid() {
        Shape shape = new Rectangle((COLUMNS + 1) * TILE_SIZE , (ROWS+1) * TILE_SIZE);

        for (int y=0 ; y<ROWS ; y++)
        {
            for(int x=0;x<COLUMNS;x++)
            {
                Circle circle = new Circle(TILE_SIZE / 2);
                circle.setCenterX(TILE_SIZE / 2);
                circle.setCenterY(TILE_SIZE / 2);
                circle.setTranslateX(x * (TILE_SIZE+5) + TILE_SIZE/4);
                circle.setTranslateY(y * (TILE_SIZE+5) + TILE_SIZE/4);

                shape = Shape.subtract(shape,circle); // cut grid into circles
            }
        }

        Light.Distant light = new Light.Distant();
        light.setAzimuth(45.0);
        light.setElevation(30.0);

        Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(5.0);


        shape.setFill(Color.BLUE);
        shape.setEffect(lighting);
        return shape;
    }

    private List<Rectangle> makeColumns(){
        List <Rectangle> list = new ArrayList<>();

        for (int x = 0; x < COLUMNS; x++) {
            Rectangle rect = new Rectangle(TILE_SIZE ,(ROWS+1) *TILE_SIZE);
            rect.setTranslateX(x * (TILE_SIZE+5) + TILE_SIZE/4);
            rect.setFill(Color.TRANSPARENT);

            rect.setOnMouseEntered(e -> rect.setFill(Color.rgb(200,200,50,0.3)));
            rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));

            final int column = x;


            rect.setOnMouseClicked(e ->{
                placeDisc(new Disc(redMove), column);
                redMove=!redMove;
                aiTurn();
            });
            list.add(rect);
        }

        return list;
    }
    private String getState()
    {   String state="";
        for(int i=0;i<COLUMNS;i++)
        {
            for (int j=0;j<ROWS;j++)
            {
                //System.out.println(i);
                //System.out.println(j);
                if(grid[i][j]!=null)
                {   if(grid[i][j].red)
                    state+='r';
                    else
                    state+='y';
                }

            else
                {
                    state+='U';
                }

            }
        }
       // System.out.println(state);
          return state;
    }
    private void aiTurn()
    {
                rootTree=new TreeNode();
                final int column2;
                this.board.setState(this.getState());
                Pair<Board,Integer> resultedMove;
                long startTime,endTime;
                System.out.println("Current depth: "+depthInput.getText());
                if(this.algorithmCombo.getValue().equals("No Pruning"))
                {   startTime = System.nanoTime();
                    resultedMove =minMax.Decide(this.board,rootTree,Integer.parseInt(depthInput.getText()));
                    endTime = System.nanoTime();
                    System.out.println("Nodes exapnded: "+minMax.numberOfnodes);
                    minMax.numberOfnodes=0;
                }
                else
                {
                    startTime = System.nanoTime();
                    resultedMove =minMaxAlphaBeta.Decide(this.board,rootTree,Integer.parseInt(depthInput.getText()));
                    endTime = System.nanoTime();
                    System.out.println("Nodes exapnded: "+minMaxAlphaBeta.numberOfnodes);
                    minMaxAlphaBeta.numberOfnodes=0;
                }
                System.out.println("Elapsed Time for "+algorithmCombo.getValue().toString()+": "+(float)(endTime-startTime)/1000000000.0+" s");

                column2=resultedMove.getValue();
                placeDisc(new Disc(redMove),column2);
                redMove=!redMove;
    }
    private void placeDisc(Disc disc , int column){
        int row = ROWS-1;
        do{
            if(!getDisc(column,row).isPresent())
            {
                break;
            }
            row--;
        }while(row>=0);

        if(row<0) {
            return;
        }
        grid[column][row] = disc;
        discRoot.getChildren().add(disc);
        disc.setTranslateX(column * (TILE_SIZE+5) + TILE_SIZE/4);

        final int currentRow = row;
        filling++;
        //System.out.println(filling);
        TranslateTransition animation = new TranslateTransition(Duration.seconds(0.01), disc);
        animation.setToY(row * (TILE_SIZE+5) + TILE_SIZE/4);
        animation.setOnFinished(e-> {
            scoreCheck(column,currentRow);
            if(filling == ROWS*COLUMNS){
                gameOver();
            }
            redMove = !redMove;
        });
        animation.play();

    }

    private void scoreCheck(int column, int row){
        List<Point2D> vertical = IntStream.rangeClosed(row-3,row+3).mapToObj(r -> new Point2D(column,r)).collect(Collectors.toList());
        List<Point2D> horizontal = IntStream.rangeClosed(column-3,column+3).mapToObj(c -> new Point2D(c,row)).collect(Collectors.toList());
        List<Point2D> vertical2 = IntStream.rangeClosed(row-4,row+4).mapToObj(r -> new Point2D(column,r)).collect(Collectors.toList());
        List<Point2D> horizontal2 = IntStream.rangeClosed(column-4,column+4).mapToObj(c -> new Point2D(c,row)).collect(Collectors.toList());

        Point2D topLeft = new Point2D(column - 3 , row -3);
        List<Point2D> diagonal1 = IntStream.rangeClosed(0,6).mapToObj(i -> topLeft.add(i,i)).collect(Collectors.toList());

        Point2D botLeft = new Point2D(column - 3 , row +3);
        List<Point2D> diagonal2 = IntStream.rangeClosed(0,6).mapToObj(i -> botLeft.add(i,-i)).collect(Collectors.toList());

        Point2D topLeft2 = new Point2D(column - 4 , row -4);
        List<Point2D> diagonal3 = IntStream.rangeClosed(0,6).mapToObj(i -> topLeft2.add(i,i)).collect(Collectors.toList());

        Point2D botLeft2 = new Point2D(column - 4 , row +4);
        List<Point2D> diagonal4 = IntStream.rangeClosed(0,6).mapToObj(i -> botLeft2.add(i,-i)).collect(Collectors.toList());
        if (checkRange(vertical))
        {
            increaseScore();
            //System.out.println("Vertical detected");
        }
        else if (checkRange(vertical2))
        {
            increaseScore();
            increaseScore();
            //System.out.println("Vertical detected");
        }


        if (checkRange(horizontal))
        {
            increaseScore();
            //System.out.println("Horizontal detected");
        }
        else if (checkRange(horizontal2))
        {
            increaseScore();
            increaseScore();
            //System.out.println("Horizontal detected");
        }

        else if (checkRange(diagonal1))
        {
            increaseScore();
            //System.out.println("Top left detected");
        }
        else if (checkRange(diagonal3))
        {
            increaseScore();
            increaseScore();
            //System.out.println("Bottom left detected");
        }
         if (checkRange(diagonal2))
        {
            increaseScore();
            //System.out.println("Bottom left detected");
        }
        else if (checkRange(diagonal4))
        {
            increaseScore();
            increaseScore();
            //System.out.println("Top left detected");
        }




        //return checkRange(vertical) || checkRange(horizontal) || checkRange(diagonal1) || checkRange(diagonal2);
    }
    
    private void increaseScore(){
        if(redMove == true)
        {
            redScore++;
            System.out.println("Red scored");
        }else
        {
            yellowScore++;
            System.out.println("Yellow scored");
        }
        updateText();
        
        System.out.println("Red score = " + redScore);
        System.out.println("Yellow score = " + yellowScore);
    }
    
    
    private boolean checkRange(List<Point2D> points){
        int chain = 0;

        for (Point2D p :points) {
            int column = (int) p.getX();
            int row = (int) p.getY();
            
            
            Disc disc = getDisc(column, row).orElse(new Disc(!redMove));
            if (disc.red == redMove) {
                chain++;
                if (chain == 4) {
                    return true;
                }
            }
            else{
                chain = 0;
            }
        }
        return false;
    }

    private void gameOver(){
        //System.out.println("Winner is " + (redMove ? "Red" : "Yellow"));
        if(redScore>yellowScore)
        {
            System.out.println("Red won!");
        }else
        {
            System.out.println("Yellow won!");
        }
    }

    private Optional<Disc> getDisc(int column , int row){
        if(column<0 || column>=COLUMNS || row < 0 || row >= ROWS)
        {
            return Optional.empty();
        }
        return Optional.ofNullable(grid[column][row]);
    }

    private static class Disc extends Circle {
        private final boolean red;

        public Disc (boolean red){
            super(TILE_SIZE/2,red ? Color.RED : Color.YELLOW);
            this.red = red;

            setCenterX(TILE_SIZE/2);
            setCenterY(TILE_SIZE/2);
        }
    }



    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.setHeight(600);
        stage.setWidth(900);
        stage.show();
    }

    public void init(String[] args){

        launch(args);
    }

}

