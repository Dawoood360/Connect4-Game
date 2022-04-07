package com.company;

public class Main {

    public static void main(String[] args) {


        String initialState = "";
        for (int i = 0; i < 42; i++)
            initialState += "U";
        Board boardState = new Board(initialState, true);
//        System.out.println(boardState.getState());
        Board newState1 = new Board(boardState.insert(6, 'r'), true);
//        System.out.println(newState1.getState());
        Board newState2 = new Board(newState1.insert(5, 'r'), true);
        Board newState3 = new Board(newState2.insert(6, 'r'), true);
        Board newState4 = new Board(newState3.insert(3, 'b'), true);
        Board newState5 = new Board(newState4.insert(4, 'b'), true);
        Heuristics heu = new Heuristics();
        Long startTime = System.currentTimeMillis();
        int heur = heu.eval(newState5);
        Long endTime = System.currentTimeMillis();


//
//        System.out.println(newState3.getState());
//        System.out.println(newState3.getState().length());

//        for (String col :  newState3.getLeftDiagonals()){
//            System.out.println(col);
//        }
    }

}
