package com.company;

import java.util.ArrayList;


public class Board {
    private String state;
    private boolean playerTurn;
    private int AI_SCORE = 0;
    private int PLAYER_SCORE = 0;

    public int getAiScore() {
        return this.AI_SCORE;
    }

    public void setAiScore(int aiScore) {
        this.AI_SCORE = aiScore;
    }

    public int getPlayerScore() {
        return this.PLAYER_SCORE;
    }

    public void setPlayerScore(int playerScore) {
        this.PLAYER_SCORE = playerScore;
    }

    public Board(String state, boolean playerTurn) {
        this.state = state;
        this.playerTurn = playerTurn;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    public boolean isBoardFull() {
        return this.state.contains("U");
    }

    public boolean isColumnFull(int columnIndex) {
        int beginningIndex = 6 * columnIndex;
        int endingIndex = beginningIndex + 6;
        String columnState = this.state.substring(beginningIndex, endingIndex);
        return columnState.contains("U");
    }

    public String insert(int columnIndex, char turn) {
        int beginningIndex = 6 * columnIndex;
        int endingIndex = beginningIndex + 6;
        int i;
        StringBuilder columnState = new StringBuilder(this.state);

        for (i = beginningIndex; i < endingIndex; i++) {
            if (this.state.charAt(i) != 'U') {
                break;
            }
        }
        columnState.setCharAt(i - 1, turn);
        return columnState.toString();
    }

    public ArrayList<String> getColumns() {
        ArrayList<String> columns = new ArrayList<String>();
        int beginningIndex = 0;
        int endingIndex = 0;
        for (int i = 0; i < 7; i++) {
            beginningIndex = i * 6;
            endingIndex = beginningIndex + 6;
            columns.add(this.getState().substring(beginningIndex, endingIndex));
        }
        return columns;
    }

    public ArrayList<String> getRows() {
        ArrayList<String> rows = new ArrayList<String>();
        for (int i = 0; i < 6; i++) {
            String row = "";
            for (int j = 0; j < 7; j++)
                row += this.getState().charAt(i + j * 6);
            rows.add(row);
        }
        return rows;
    }

    public ArrayList<String> getRightDiagonals() {
        ArrayList<String> rightDiagonals = new ArrayList<String>();
        int i, col = 0;

        for (i = 0; i < 7; i++) {
            String diag = "";
            col = 0;
            for (int j = i; j > 0; j--, col++) {
                diag += this.getState().charAt(6 * col + j);
            }
            rightDiagonals.add(diag);
        }
        for (i = 0; i < 7; i++) {
            String diag = "";
            col = 6;
            for (int j = i; j < 6; j++, col--) {
                diag += this.getState().charAt(6 * col + j);
            }
            rightDiagonals.add(diag);
        }
        return rightDiagonals;
    }

    public ArrayList<String> getLeftDiagonals() {
        ArrayList<String> leftDiagonals = new ArrayList<String>();
        int i, col = 6;
        for (i = 0; i < 6; i++) {
            String diag = "";
            col = 6;
            for (int j = i; j >= 0; j--, col--) {
                diag += this.getState().charAt(6 * col + j);
            }
            leftDiagonals.add(diag);
        }
        for (i = 0; i < 7; i++) {
            String diag = "";
            col = 0;
            for (int j = i; j < 6; col++, j++) {
                diag += this.getState().charAt(6 * col + j);
            }
            leftDiagonals.add(diag);
        }
        return leftDiagonals;
    }


}
