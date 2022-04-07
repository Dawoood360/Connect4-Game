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
        System.out.println(colScore);
        int rowScore = evaluateScore(rows, redRowScore, blueRowScore);
        System.out.println(rowScore);
        int rightDiagScore = evaluateScore(rightDiagonal, redRightDiagScore, blueRightDiagScore);
        System.out.println(rightDiagScore);
        int leftDiagScore = evaluateScore(leftDiagonal, redLeftDiagScore, blueLeftDiagScore);
        System.out.println(leftDiagScore);
        System.out.println();
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
                } else if (diag.charAt(i) == 'b') {
                    blueCounter++;
                    redCounter = 0;
                } else {
                    redCounter = 0;
                    blueCounter = 0;
                }
                if (blueCounter > 1)
                    blueScore += blueCounter * 1000;
                if (redCounter > 1)
                    redScore += redCounter * 1000;

            }
        }

        return redScore - blueScore;
    }


}
