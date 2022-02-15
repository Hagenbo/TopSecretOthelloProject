package Othello.model;

import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public class othelloModel implements Serializable {
    private int gameID;
    private static final int n = 8;
    private PieceColor[][] board = new PieceColor[n][n];
    private boolean isBlackTurn;
    private boolean playerWithdrawn;

    //some additions: See comments in player-class
    private final Player player1;
    private final Player player2;


    public othelloModel(String p1, String p2) {
        isBlackTurn = true;
        playerWithdrawn = false;
        Random r = new Random();        // this may not be used
        this.gameID = r.nextInt();      // this may not be used

        //assign players and Colors. See comments in player-class
        this.player1 = new Player(p1);
        this.player2 = new Player(p2);
        player1.assignColor(PieceColor.BLACK);
        player2.assignColor(PieceColor.WHITE);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i == n/2 - 1 && j == n/2 - 1) || (i == n/2 && j == n/2)) {
                    board[i][j] = PieceColor.BLACK;
                }
                else if ((i == n/2 - 1 && j == n/2) || (i == n/2 && j == n/2 - 1) ) {
                    board[i][j] = PieceColor.WHITE;
                }
                else {
                    board[i][j] = PieceColor.EMPTY;
                }
            }
        }
    }

    public PieceColor getPiece( int i, int j) {
        return board[i][j];
    }

    public void changeTurn() {
        isBlackTurn = !isBlackTurn;
    }



    public boolean placePieceAt( int i, int j) {
        //lägg till ljud om det inte är EMPTY
        if(!movePossible(i,j) || board[i][j] != PieceColor.EMPTY) {
            return false;}
        PieceColor c;
        if (isBlackTurn) {
            c = PieceColor.BLACK;
        }
        else {
            c = PieceColor.WHITE;
        }
        board[i][j] = c;
        changeTurn();
        return true;
    }

    //Put in view instead? The player who withdraws is always the loser?
    public void withdraw() {
        playerWithdrawn = true;
        gameOver();
    }

    public void gameOver() {
        int nr_black = 0;
        int nr_white = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == PieceColor.BLACK) {
                    nr_black++;
                }
                if (board[i][j] == PieceColor.WHITE) {
                    nr_white++;
                }
            }
        }
        //TODO add prompt with an "ok"-button saying who is the winner.
        // when ok_button is pressed the game ends and returns to main menu. Should be in view somehow...
        if (nr_black > nr_white) {
            System.out.println("Winner is " + player1.getPlayerName());   // get player1 username, method in Player-class
        }
        else if (nr_white > nr_black) {
            System.out.println("Winner is " + player2.getPlayerName());  // get player2 username, method in Player-class
        }
        else {
            System.out.println("It's a draw!");
        }
    }

    // Methods for checking color of an immediate neighbour of a given piece. Returns PieceColor.
    //TODO add checks for diagonal, but first make sure that these work as intended.
    private PieceColor checkAbove(int i, int j) {
        return board[i - 1][j];
    }
    private PieceColor checkBelow( int i, int j ) {
        return board[i + 1][j];
    }
    private PieceColor checkRight( int i, int j ) {
        return board[i][j - 1];
    }
    private PieceColor checkLeft( int i, int j ) {
        return board[i][j + 1];
    }

    // Methods for checking if a "flippable move" is possible in every direction from a given piece.
    // Ugh, except diagonal.
    //TODO add checks for diagonal, but first make sure that these work as intended.
    public boolean checkVerticalUp(int i, int j) {
        PieceColor startColor = setStartColor(i, j);
        if( i > 0 && checkAbove(i,j) == startColor) {
            return false;
        }
        while (i > 0 && checkAbove(i,j) != PieceColor.EMPTY) {
            if (checkAbove(i, j) == startColor) {
                return true;
            }
            i--;
        }
        return false;
    }

    public boolean checkVerticalDown(int i, int j) {
        PieceColor startColor = setStartColor(i,j);
        if( i < (n-1) && checkBelow(i,j) == startColor) {
            return false;
        }
        while (i < (n-1) && checkBelow(i,j) != PieceColor.EMPTY) {
            if (checkBelow(i, j) == startColor) {
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean checkHorizontalRight(int i, int j) {
        PieceColor startColor = setStartColor(i,j);
        if( j > 0 && checkRight(i,j) == startColor) {
            return false;
        }
        while (j > 0 && checkRight(i,j) != PieceColor.EMPTY) {
            if (checkRight(i, j) == startColor) {
                return true;
            }
            j--;
        }
        return false;
    }

    public boolean checkHorizontalLeft(int i, int j) {
        PieceColor startColor = setStartColor(i,j);
        if( j < (n-1) && checkLeft(i,j) == startColor) {
            return false;
        }
        while (j < (n-1) && checkLeft(i,j) != PieceColor.EMPTY) {
            if (checkLeft(i, j) == startColor) {
                return true;
            }
            j++;
        }
        return false;
    }

    // helper-method used in check"direction"-methods.
    private PieceColor setStartColor(int i, int j) {
        PieceColor startColor;
        if (board[i][j] == PieceColor.EMPTY && isBlackTurn) {
            startColor = PieceColor.BLACK;
        } else if (board[i][j] == PieceColor.EMPTY && !isBlackTurn) {
            startColor = PieceColor.WHITE;
        } else {
            startColor = board[i][j];
        }
        return startColor;
    }

    // checks if a move is possible. Should be called each new turn.
    // If this returns false, a prompt saying that no moves are possible should pop up,
    // and it will be the next players turn.
    public boolean movePossible(int i, int j) {
        return (checkVerticalUp(i,j) ||
                checkVerticalDown(i,j) ||
                checkHorizontalRight(i,j) ||
                checkHorizontalLeft(i,j));
    }

    public boolean playPossible() {
        int possibleMoves = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == PieceColor.EMPTY && movePossible(i, j)) {
                    possibleMoves++;
                }
            }
        }
        return (possibleMoves > 0);
    }
/*
    public boolean flip(int i, int j){
     boolean up=false;
     boolean down=false;
     boolean right=false;
     boolean left=false;

        if(playPossible()){
            int countFlips = 0;
            while(checkVerticalDown(i, j)){
                countFlips++;
                i++;
                down = true;
            }
            while(checkVerticalUp(i, j)){
                countFlips++;
                i--;
                up = true;
            }
            while(checkHorizontalLeft(i, j)){
                countFlips++;
                j++;
                left = true;
            }
            while(checkHorizontalRight(i, j)){
                countFlips++;
                j--;
                right = true;
            }

            System.out.println(countFlips);

            if(countFlips == 0){
                return false;
            } else if(isBlackTurn){
                while(countFlips > 0){
                    if(down){i++;}
                    if(up){i--;}
                    if(left){j--;}
                    if(right){j++;}
                    this.board[i][j] = player1.getColor();
                    System.out.println("Player1");
                    countFlips--;
                }
            } else { //VEMS TUR ÄR DET??
                while (countFlips > 0) {
                    if(down){i++;}
                    if(up){i--;}
                    if(left){j--;}
                    if(right){j++;}
                    this.board[i][j] = player2.getColor();
                    System.out.println("Player2");
                    countFlips--;
                }
            }
            //System.out.println(board[i][j]);
            return true;
        }
        return false;
    }*/
    //TODO implement a flip-method that changes the values in the PieceColor-array
}

