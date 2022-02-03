package Othello;

import java.util.Random;

public class othelloModel {
    private int gameID;
    private static final int n = 10;
    private PieceColor[][] boardSquare = new PieceColor[n][n];
    private boolean isBlackTurn;

    public othelloModel() {
        Random r = new Random();
        this.gameID = r.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boardSquare[i][j] = PieceColor.EMPTY;
            }
        }

    }
    public int getColumns() { return n;}

    public PieceColor getPiece( int i, int j) {
        return boardSquare[i][j];
    }

    public void placePieceAt( int i, int j) {           //anropas av controller
        if(gameOver()) { return;}
        if( boardSquare[i][j] != PieceColor.EMPTY) { return;}
        PieceColor c;
        if (isBlackTurn) {
            c = PieceColor.BLACK;
        }
        else {
            c = PieceColor.WHITE;
        }
        boardSquare[i][j] = c;
        isBlackTurn = !isBlackTurn;
    }

    public boolean gameOver() {
        int no_black = 0;
        int no_white = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (boardSquare[i][j] == PieceColor.EMPTY) {
                    return false;
                }
                if (boardSquare[i][j] == PieceColor.BLACK) {
                    no_black++;
                }
                if (boardSquare[i][j] == PieceColor.WHITE) {
                    no_white++;
                }
            }
        }
        if (no_black > no_white) {
            System.out.println("Winner is Black");   //replace with playerId
        }
        if (no_white > no_black) {
            System.out.println("Winner is White");   //replace with playerId
        } else {
            System.out.println("It's a draw!");
        }
        return true;
    }
}
