package Othello.model;

import java.util.Random;

public class othelloModel {
    private int gameID;
    private static final int n = 10;
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

    public PieceColor getPiece( int i, int j) {
        return board[i][j];
    }

    public void placePieceAt( int i, int j) {           //anropas av controller
        if(gameOver()) { return;}
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
}
