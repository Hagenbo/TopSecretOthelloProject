package Othello.model;

import java.io.Serializable;
import java.util.function.Consumer;

public class OthelloModel implements Serializable {

    // behövs vid serializable: private static final long serialVersionUID = 1L;
    private int gameID;
    private static final int n = 8;
    private PieceColor[][] board = new PieceColor[n][n];
    private boolean isBlackTurn;
    private PieceColor startColor;
    private Consumer<PieceColor> onGameOver;

    //some additions: See comments in player-class
    private final Player player1;
    private final Player player2;


    public OthelloModel(String p1, String p2) {
        isBlackTurn = true;
        /*Random r = new Random();        // this may not be used
        this.gameID = r.nextInt();      // this may not be used*/
        startColor = setStartColor();

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

    //observer-delen
    public void setOnGameOver(Consumer <PieceColor> c){
        onGameOver = c;

    }

    public PieceColor getPiece( int i, int j) {
        return board[i][j];
    }

    public void changeTurn() {
        isBlackTurn = !isBlackTurn;
        startColor = setStartColor();
    }

    public boolean placePieceAt( int i, int j) {
        //System.out.println(startColor);
        if(!movePossible(i,j) || board[i][j] != PieceColor.EMPTY) {
            return false;}
        board[i][j] = startColor;
        flip(i,j);
        changeTurn();
        return true;
    }

    public String gameOver() {
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
            if(onGameOver != null){
                onGameOver.accept(PieceColor.BLACK);
            }
            return "Black";

        }
        else if (nr_white > nr_black) {
            if(onGameOver != null){
                onGameOver.accept(PieceColor.WHITE);
            }
            return "White";
        }
        else {
            //TODO add for draw
            //System.out.println("It's a draw!");
            System.out.println("Draw");
            return "Draw";
        }

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

    // checks if a move is possible. Should be called each new turn.
    // If this returns false, a prompt saying that no moves are possible should pop up,
    // and it will be the next players turn.
    private boolean movePossible(int i, int j) {
        return (checkVerticalUp(i,j) || checkVerticalDown(i,j) ||
                checkHorizontalRight(i,j) || checkHorizontalLeft(i,j) ||
                checkDiagonalUR(i,j) || checkDiagonalDR(i,j) ||
                checkDiagonalDL(i,j) || checkDiagonalUL(i,j));
    }

    // helper-method used in check"direction"-methods.
    private PieceColor setStartColor() {
        if (isBlackTurn) {
            return PieceColor.BLACK;
        } else {
            return PieceColor.WHITE;
        }
    }

    // Methods for checking color of an immediate neighbour of a given piece. Returns PieceColor.
    //TODO add checks for diagonal, but first make sure that these work as intended.
    private PieceColor checkAbove(int i, int j) { return board[i - 1][j];}
    private PieceColor checkBelow( int i, int j ) { return board[i + 1][j];}
    private PieceColor checkRight( int i, int j ) { return board[i][j + 1];}
    private PieceColor checkLeft( int i, int j ) { return board[i][j - 1];}
    //Diagonal checks
    private PieceColor checkUpRight(int i, int j) { return board[i-1][j+1];}
    private PieceColor checkDownRight(int i, int j) { return board[i+1][j+1];}
    private PieceColor checkDownLeft(int i, int j) { return board[i+1][j-1];}
    private PieceColor checkUpLeft(int i, int j) { return board[i-1][j-1];}

    // Methods for checking if a "flippable move" is possible in every direction from a given piece.

    private boolean checkVerticalUp(int i, int j) {
        if( i > 0 && checkAbove(i,j) == startColor) {
            //System.out.println("VerticalUp = false");
            return false;
        }
        while (i > 0 && checkAbove(i,j) != PieceColor.EMPTY) {
            if (checkAbove(i, j) == startColor) {
                //System.out.println("VerticalUp = true");
                return true;
            }
            i--;
        }
        //System.out.println("VerticalUp = false");
        return false;
    }
    private boolean checkVerticalDown(int i, int j) {
        if( i < (n-1) && checkBelow(i,j) == startColor) {
            //System.out.println("VerticalDown = false");
            return false;
        }
        while (i < (n-1) && checkBelow(i,j) != PieceColor.EMPTY) {
            if (checkBelow(i, j) == startColor) {
                //System.out.println("VerticalDown = true");
                return true;
            }
            i++;
        }
        //System.out.println("VerticalDown = false");
        return false;
    }
    private boolean checkHorizontalRight(int i, int j) {
        if( j < (n-1)  && checkRight(i,j) == startColor) {
            //System.out.println("HorizontalRight = false");
            return false;
        }
        while (j < (n-1) && checkRight(i,j) != PieceColor.EMPTY) {
            if (checkRight(i, j) == startColor) {
                //System.out.println("HorizontalRight = true");
                return true;
            }
            j++;
        }
        //System.out.println("HorizontalRight = false");
        return false;
    }
    private boolean checkHorizontalLeft(int i, int j) {
        if( j > 0 && checkLeft(i,j) == startColor) {
            //System.out.println("HorizontalLeft = false");
            return false;
        }
        while (j > 0 && checkLeft(i,j) != PieceColor.EMPTY) {
            if (checkLeft(i, j) == startColor) {
                //System.out.println("HorizontalLeft = true");
                return true;
            }
            j--;
        }
        //System.out.println("HorizontalLeft = false");
        return false;
    }
    private boolean checkDiagonalUR(int i, int j) {
        if( (i > 0 && j < (n-1)) && checkUpRight(i,j) == startColor) {
            //System.out.println("Diagonal UpRight = false");
            return false;
        }
        while ((i > 0 && j < (n-1)) && checkUpRight(i,j) != PieceColor.EMPTY) {
            if (checkUpRight(i, j) == startColor) {
                //System.out.println("Diagonal UpRight = true");
                return true;
            }
            i--;
            j++;
        }
        //System.out.println("Diagonal UpRight = false");
        return false;
    }
    private boolean checkDiagonalDR(int i, int j) {
        if( (i < n-1 && j < n-1) && checkDownRight(i,j) == startColor) {
            //System.out.println("Diagonal DownRight = false");
            return false;
        }
        while ((i < n-1 && j < n-1) && checkDownRight(i,j) != PieceColor.EMPTY) {
            if (checkDownRight(i, j) == startColor) {
                //System.out.println("Diagonal DownRight = true");
                return true;
            }
            i++;
            j++;
        }
        //System.out.println("Diagonal DownRight = false");
        return false;
    }
    private boolean checkDiagonalDL(int i, int j) {
        if( i < n-1 && j > 0 && checkDownLeft(i,j) == startColor) {
            //System.out.println("Diagonal DownLeft = false");
            return false;
        }
        while (i < n-1 && j > 0 && checkDownLeft(i,j) != PieceColor.EMPTY) {
            if (checkDownLeft(i, j) == startColor) {
                //System.out.println("Diagonal DownLeft = true");
                return true;
            }
            i++;
            j--;
        }
        //System.out.println("Diagonal DownLeft = false");
        return false;
    }
    private boolean checkDiagonalUL(int i, int j) {
        if( (i > 0 && j > 0) && checkUpLeft(i,j) == startColor) {
            //System.out.println("Diagonal UpLeft = false");
            return false;
        }
        while ((i > 0 && j > 0) && checkUpLeft(i,j) != PieceColor.EMPTY) {
            if (checkUpLeft(i, j) == startColor) {
                //System.out.println("Diagonal UpLeft = true");
                return true;
            }
            i--;
            j--;
        }
        //System.out.println("Diagonal UpLeft = false");
        return false;
    }

    // changes the values in board[i][j] where applicable
    private void flip(int i, int j) {
        int x;
        int y;
        if (checkVerticalUp(i,j)) {
            y = i - 1;
            while (y > 0 && board[y][j] != startColor) {
                board[y][j] = startColor;
                y--;
            }
        }
        if (checkVerticalDown(i,j)) {
            y = i + 1;
            while (y < (n-1) && board[y][j] != startColor) {
                board[y][j] = startColor;
                y++;
            }
        }
        if (checkHorizontalRight(i,j)) {
            x = j + 1;
            while (x < n-1 && board[i][x] != startColor) {
                board[i][x] = startColor;
                x++;
            }
        }
        if (checkHorizontalLeft(i,j)) {
            x = j - 1;
            while (x > 0 && board[i][x] != startColor) {
                board[i][x] = startColor;
                x--;
            }
        }
        if (checkDiagonalUR(i,j)) {
            y = i - 1;
            x = j + 1;
            while ((y > 0 && x < n-1) && board[y][x] != startColor) {
                board[y][x] = startColor;
                y--;
                x++;
            }
        }
        if (checkDiagonalDR(i,j)) {
            y = i + 1;
            x = j + 1;
            while ((y < n-1 && x < n-1) && board[y][x] != startColor) {
                board[y][x] = startColor;
                y++;
                x++;
            }
        }
        if (checkDiagonalDL(i,j)) {
            y = i + 1;
            x = j - 1;
            while ((y < n - 1 && x > 0) && board[y][x] != startColor) {
                board[y][x] = startColor;
                y++;
                x--;
            }
        }
        if (checkDiagonalUL(i,j)) {
            y = i - 1;
            x = j - 1;
            while ((y > 0 && x > 0) && board[y][x] != startColor) {
                board[y][x] = startColor;
                y--;
                x--;
            }
        }
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

}

