package Othello.model;

import java.io.Serializable;

/**
 * Class containing a grid representing an Othello board.
 * @Author Viktoria Hagenbo, Lovisa Rosin, Casper von Schenck, Ernst Näslund, Alexander Bratic
 * @version 2022-03-04
 */
public class Board implements Serializable {
    private PieceColor[][] board;
    private final int n;

    /**
     * Contructor creating an Othello board
     * @param n - decides size of board
     */
    public Board(int n) {
        this.board = new PieceColor[n][n];
        this.n = n;
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

    /**
     * Setter-method that gives an instance another board
     * @param board - board
     */
    public void setBoard(Board board){
        this.board = board.board;
    }

    /**
     * Gets the piece on position i, j from the board
     * @param i - represents row
     * @param j - represents column
     * @return PieceColor
     */
    public PieceColor getPiece( int i, int j) {
        return board[i][j];
    }

    /**
     * Updates the board by adding a PieceColor at position i, j on the board.
     * @param i - represents row
     * @param j - represents column
     * @param c - color of piece
     */
    public void setPiece(int i, int j, PieceColor c) {
        this.board[i][j] = c;
    }

    /**
     * Returns number of rows/columns
     * @return int n
     */
    public int getBoardSize() { return n; }

    /**
     * Checks if there are any possible moves for player with PieceColor c
     * @param c - PieceColor
     * @return true, false
     */
    public boolean playPossible(PieceColor c) {
        int possibleMoves = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (getPiece(i, j) == PieceColor.EMPTY && movePossible(i, j, c)) {
                    possibleMoves++;
                }
            }
        }
        return (possibleMoves > 0);
    }

    /**
     * Places a PieceColor at position i, j on the board if the move is legal.
     * Calls flip()-method.
     * @param i - represents row
     * @param j - represents column
     * @param c - color of piece
     * @return true, false
     */
    public boolean placePieceAt(int i, int j, PieceColor c) {
        if (!movePossible(i, j, c) || getPiece(i, j) != PieceColor.EMPTY) {
            return false;
        }
        setPiece(i, j, c);
        flip(i, j, c);
        return true;
    }

    private boolean movePossible(int i, int j, PieceColor c) {
        return (checkVerticalUp(i,j, c) || checkVerticalDown(i,j, c) ||
                checkHorizontalRight(i,j, c) || checkHorizontalLeft(i,j, c) ||
                checkDiagonalUR(i,j, c) || checkDiagonalDR(i,j, c) ||
                checkDiagonalDL(i,j, c) || checkDiagonalUL(i,j, c));
    }

    /**
     * Counts the pieces of each color. Returns a string with the color that has the most pieces.
     * @return Black, White, Draw
     */
    public  String countPieces() {
        int nr_black = 0;
        int nr_white = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (getPiece(i, j) == PieceColor.BLACK) {
                    nr_black++;
                }
                if (getPiece(i, j) == PieceColor.WHITE) {
                    nr_white++;
                }
            }
        }
        if (nr_black > nr_white) {
            return "Black";
        }
        else if (nr_white > nr_black) {
            return "White";
        }
        else {
            return "Draw";
        }
    }
    /**
     * Flips opposite color where applicable according to Othello rules starting from position i, j
     * @param i - represents row
     * @param j - represents column
     * @param c - color of the placed piece
     */
    public void flip(int i, int j, PieceColor c) {
        int x;
        int y;
        if (checkVerticalUp(i,j, c)) {
            y = i - 1;
            while (y > 0 && board[y][j] != c) {
                board[y][j] = c;
                y--;
            }
        }
        if (checkVerticalDown(i,j, c)) {
            y = i + 1;
            while (y < (n-1) && board[y][j] != c) {
                board[y][j] = c;
                y++;
            }
        }
        if (checkHorizontalRight(i,j, c)) {
            x = j + 1;
            while (x < n-1 && board[i][x] != c) {
                board[i][x] = c;
                x++;
            }
        }
        if (checkHorizontalLeft(i,j, c)) {
            x = j - 1;
            while (x > 0 && board[i][x] != c) {
                board[i][x] = c;
                x--;
            }
        }
        if (checkDiagonalUR(i,j, c)) {
            y = i - 1;
            x = j + 1;
            while ((y > 0 && x < n-1) && board[y][x] != c) {
                board[y][x] = c;
                y--;
                x++;
            }
        }
        if (checkDiagonalDR(i,j, c)) {
            y = i + 1;
            x = j + 1;
            while ((y < n-1 && x < n-1) && board[y][x] != c) {
                board[y][x] = c;
                y++;
                x++;
            }
        }
        if (checkDiagonalDL(i,j, c)) {
            y = i + 1;
            x = j - 1;
            while ((y < n - 1 && x > 0) && board[y][x] != c) {
                board[y][x] = c;
                y++;
                x--;
            }
        }
        if (checkDiagonalUL(i,j, c)) {
            y = i - 1;
            x = j - 1;
            while ((y > 0 && x > 0) && board[y][x] != c) {
                board[y][x] = c;
                y--;
                x--;
            }
        }
    }

    /**
     * The following 8 methods are helper methods for checking immediate neighbours on the board,
     * both left, right, above, below, and diagonally
     * @param i - row
     * @param j - column
     * @return PieceColor of the immediate neighbour
     */
    private PieceColor checkAbove(int i, int j) { return board[i - 1][j];}
    private PieceColor checkBelow( int i, int j ) { return board[i + 1][j];}
    private PieceColor checkRight( int i, int j ) { return board[i][j + 1];}
    private PieceColor checkLeft( int i, int j ) { return board[i][j - 1];}
    //Diagonal checks
    private PieceColor checkUpRight(int i, int j) { return board[i-1][j+1];}
    private PieceColor checkDownRight(int i, int j) { return board[i+1][j+1];}
    private PieceColor checkDownLeft(int i, int j) { return board[i+1][j-1];}
    private PieceColor checkUpLeft(int i, int j) { return board[i-1][j-1];}



    /**
     * The following 8 methods are methods for checking if a flippable move is possible in every
     * direction from a given place. Checks vertical, horizontal, diagonally for both left, right, up, and down.
     * @param i - row
     * @param j - column
     * @param c- a PieceColor
     * @return boolean
     */

    private boolean checkVerticalUp(int i, int j, PieceColor c) {
        if( i > 0 && checkAbove(i,j) == c) {
            return false;
        }
        while (i > 0 && checkAbove(i,j) != PieceColor.EMPTY) {
            if (checkAbove(i, j) == c) {
                return true;
            }
            i--;
        }
        return false;
    }
    private boolean checkVerticalDown(int i, int j, PieceColor c) {
        if( i < (n-1) && checkBelow(i,j) == c) {
            return false;
        }
        while (i < (n-1) && checkBelow(i,j) != PieceColor.EMPTY) {
            if (checkBelow(i, j) == c) {
                return true;
            }
            i++;
        }
        return false;
    }
    private boolean checkHorizontalRight(int i, int j, PieceColor c ) {
        if( j < (n-1)  && checkRight(i,j) == c) {
            return false;
        }
        while (j < (n-1) && checkRight(i,j) != PieceColor.EMPTY) {
            if (checkRight(i, j) == c) {
                return true;
            }
            j++;
        }
        return false;
    }
    private boolean checkHorizontalLeft(int i, int j, PieceColor c) {
        if( j > 0 && checkLeft(i,j) == c) {
            return false;
        }
        while (j > 0 && checkLeft(i,j) != PieceColor.EMPTY) {
            if (checkLeft(i, j) == c) {
                return true;
            }
            j--;
        }
        return false;
    }
    private boolean checkDiagonalUR(int i, int j, PieceColor c) {
        if( (i > 0 && j < (n-1)) && checkUpRight(i,j) == c) {
            return false;
        }
        while ((i > 0 && j < (n-1)) && checkUpRight(i,j) != PieceColor.EMPTY) {
            if (checkUpRight(i, j) == c) {
                return true;
            }
            i--;
            j++;
        }
        return false;
    }
    private boolean checkDiagonalDR(int i, int j, PieceColor c) {
        if( (i < n-1 && j < n-1) && checkDownRight(i,j) == c) {
            return false;
        }
        while ((i < n-1 && j < n-1) && checkDownRight(i,j) != PieceColor.EMPTY) {
            if (checkDownRight(i, j) == c) {
                return true;
            }
            i++;
            j++;
        }
        return false;
    }
    private boolean checkDiagonalDL(int i, int j, PieceColor c) {
        if( i < n-1 && j > 0 && checkDownLeft(i,j) == c) {
            return false;
        }
        while (i < n-1 && j > 0 && checkDownLeft(i,j) != PieceColor.EMPTY) {
            if (checkDownLeft(i, j) == c) {
                return true;
            }
            i++;
            j--;
        }
        return false;
    }
    private boolean checkDiagonalUL(int i, int j, PieceColor c) {
        if( (i > 0 && j > 0) && checkUpLeft(i,j) == c) {
            return false;
        }
        while ((i > 0 && j > 0) && checkUpLeft(i,j) != PieceColor.EMPTY) {
            if (checkUpLeft(i, j) == c) {
                return true;
            }
            i--;
            j--;
        }
        return false;
    }
}
