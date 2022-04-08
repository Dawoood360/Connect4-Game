package com.company;

import java.util.ArrayList;

public class Heuristics {

    public int eval(Board nodeState) {
        ArrayList<String> columns = nodeState.getColumns();
        ArrayList<String> rows = nodeState.getRows();
        ArrayList<String> rightDiagonal = nodeState.getRightDiagonals();
        ArrayList<String> leftDiagonal = nodeState.getLeftDiagonals();


        int redRowScore = 0;
        int redColScore = 0;
        int redRightDiagScore = 0;
        int redLeftDiagScore = 0;

        int blueRowScore = 0;
        int blueColScore = 0;
        int blueRightDiagScore = 0;
        int blueLeftDiagScore = 0;


        int colScore = evaluateScore(columns, redColScore, blueColScore);

        int rowScore = evaluateScore(rows, redRowScore, blueRowScore);

        int rightDiagScore = evaluateScore(rightDiagonal, redRightDiagScore, blueRightDiagScore);

        int leftDiagScore = evaluateScore(leftDiagonal, redLeftDiagScore, blueLeftDiagScore);


        return colScore + rowScore + rightDiagScore + leftDiagScore;
    }

    private int evaluateScore(ArrayList<String> dimension, int redScore, int blueScore) {
        int redCounter = 0;
        int blueCounter = 0;
        int i;
        for (String diag : dimension) {
            redCounter = 0;
            blueCounter = 0;
            for (i = 0; i < diag.length(); i++) {

                if (diag.charAt(i) == 'r') {
                    redCounter++;
                    blueCounter = 0;
                } else if (diag.charAt(i) == 'y') {
                    blueCounter++;
                    redCounter = 0;
                } else {
                    redCounter = 0;
                    blueCounter = 0;
                }
                if(blueCounter==7)
                {
                    blueScore+=blueCounter * 120000;
                }
                else if(blueCounter==6)
                {
                    blueScore+=blueCounter * 100000;
                }
                else if(blueCounter==5)
                {
                    blueScore+=blueCounter * 90000;
                }
                else if(blueCounter==4)
                {
                    blueScore+=blueCounter * 80000;
                }
                else if(blueCounter==3)
                {
                    blueScore+=blueCounter * 20000;
                }
                else if (blueCounter == 2)
                    blueScore += blueCounter * 500;

                if(redCounter==7){
                    redScore+=redCounter * 60000;
                }
                else if(redCounter==6){
                    redScore+=redCounter * 40000;
                }
                else if(redCounter==5){
                    redScore+=redCounter * 35000;
                }
                else if(redCounter==4)
                {
                    redScore+=redCounter * 30000;
                }
                else if(redCounter==3)
                {
                    redScore+=redCounter * 1000;
                }
                else if (redCounter == 2)
                    redScore += redCounter * 100;

            }
        }

        return (int)(redScore - 1.5*blueScore);
    }


}
