package Othello.model;

import java.util.Random;

public class othelloModel {
    private int gameID;
    private static final int n = 10;
    private PieceColor[][] board = new PieceColor[n][n];
    private boolean isBlackTurn;
    private boolean playerWithdrawn;
    private boolean soundOn;

    //some additions: See comments in player-class
    private final Player player1;
    private final Player player2;

    public othelloModel(String p1, String p2) {
        isBlackTurn = true;
        playerWithdrawn = false;
        Random r = new Random();        // this may not be used
        this.gameID = r.nextInt();      // this may not be used
        this.soundOn = true;

        //assign players and Colors. See comments in player-class
        this.player1 = new Player(p1);
        this.player2 = new Player(p2);
        player1.assignColor(PieceColor.BLACK);
        player2.assignColor(PieceColor.WHITE);


        // Fills the grid according to main setup (hopefully)...
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i == n/2 && j == n/2) || (i == n/2 + 1 && j == n/2 + 1)) {
                    board[i][j] = PieceColor.BLACK;
                }
                else if ((i == n/2 && j == n/2 + 1) || (i == n/2 + 1 && j == n/2) ) {
                    board[i][j] = PieceColor.WHITE;
                }
                else {
                    board[i][j] = PieceColor.EMPTY;
                }
            }
        }

    }

    public int getColumns() { return n;}

    // maybe remove if not used?
    public PieceColor getPiece( int i, int j) {
        return board[i][j];
    }

    public void placePieceAt( int i, int j) {           //anropas av controller
        if(gameOver()) { return;}
        //lägg till ljud om det inte är EMPTY
        if( board[i][j] != PieceColor.EMPTY) { return;}
        PieceColor c;
        if (isBlackTurn) {
            c = PieceColor.BLACK;
        }
        else {
            c = PieceColor.WHITE;
        }
        board[i][j] = c;
        isBlackTurn = !isBlackTurn;
    }

    public void withdraw() {
        playerWithdrawn = true;
        gameOver();
    }

    // checks if there are any empty spots on the board and if a player has withdrawn.
    // counts the pieces of each color.
    //TODO add some kind of check that makes sure that at least one of the players can make a move.
    // If not the game should end.
    public boolean gameOver() {
        int nr_black = 0;
        int nr_white = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == PieceColor.EMPTY && !playerWithdrawn) {
                    return false;
                }
                if (board[i][j] == PieceColor.BLACK) {
                    nr_black++;
                }
                if (board[i][j] == PieceColor.WHITE) {
                    nr_white++;
                }
            }
        }
        if (nr_black > nr_white) {
            System.out.println("Winner is " + player1.getPlayerName());   // get player1 username, method in Player-class
        }
        if (nr_white > nr_black) {
            System.out.println("Winner is " + player2.getPlayerName());  // get player2 username, method in Player-class
        } else {
            System.out.println("It's a draw!");
        }
        return true;
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
        while (checkAbove(i,j) != PieceColor.EMPTY && i > 0) {
            if (checkAbove(i, j) == startColor) {
                return true;
            }
            i--;
        }
        return false;
    }

    public boolean checkVerticalDown(int i, int j) {
        PieceColor startColor = setStartColor(i,j);
        while (checkBelow(i,j) != PieceColor.EMPTY && i < 9) {
            if (checkBelow(i, j) == startColor) {
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean checkHorizontalRight(int i, int j) {
        PieceColor startColor = setStartColor(i,j);
        while (checkRight(i,j) != PieceColor.EMPTY && j > 0) {
            if (checkRight(i, j) == startColor) {
                return true;
            }
            j--;
        }
        return false;
    }

    public boolean checkHorizontalLeft(int i, int j) {
        PieceColor startColor = setStartColor(i,j);
        while (checkLeft(i,j) != PieceColor.EMPTY && j < 9) {
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
    public boolean playPossible() {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if(board[i][j] == PieceColor.EMPTY) {
                        return (checkVerticalUp(i,j) ||
                                checkVerticalDown(i,j) ||
                                checkHorizontalRight(i,j) ||
                                checkHorizontalLeft(i,j));
                    }
                }
            }
            return false;
    }
}

