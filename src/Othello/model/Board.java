package Othello.model;

public class Board {
    private PieceColor[][] board;
    private final int n;

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

    public PieceColor getPiece( int i, int j) {
        return board[i][j];
    }

    public void setPiece(int i, int j, PieceColor color) {
        this.board[i][j] = color;
    }

    // checks if a move is possible. Should be called each new turn.
    // If this returns false, a prompt saying that no moves are possible should pop up,
    // and it will be the next players turn.
    public boolean movePossible(int i, int j, PieceColor startColor) {
        return (checkVerticalUp(i,j, startColor) || checkVerticalDown(i,j, startColor) ||
                checkHorizontalRight(i,j, startColor) || checkHorizontalLeft(i,j, startColor) ||
                checkDiagonalUR(i,j, startColor) || checkDiagonalDR(i,j, startColor) ||
                checkDiagonalDL(i,j, startColor) || checkDiagonalUL(i,j, startColor));
    }

    // changes the values in board[i][j] where applicable
    public void flip(int i, int j, PieceColor startColor) {
        int x;
        int y;
        if (checkVerticalUp(i,j, startColor)) {
            y = i - 1;
            while (y > 0 && board[y][j] != startColor) {
                board[y][j] = startColor;
                y--;
            }
        }
        if (checkVerticalDown(i,j, startColor)) {
            y = i + 1;
            while (y < (n-1) && board[y][j] != startColor) {
                board[y][j] = startColor;
                y++;
            }
        }
        if (checkHorizontalRight(i,j, startColor)) {
            x = j + 1;
            while (x < n-1 && board[i][x] != startColor) {
                board[i][x] = startColor;
                x++;
            }
        }
        if (checkHorizontalLeft(i,j, startColor)) {
            x = j - 1;
            while (x > 0 && board[i][x] != startColor) {
                board[i][x] = startColor;
                x--;
            }
        }
        if (checkDiagonalUR(i,j, startColor)) {
            y = i - 1;
            x = j + 1;
            while ((y > 0 && x < n-1) && board[y][x] != startColor) {
                board[y][x] = startColor;
                y--;
                x++;
            }
        }
        if (checkDiagonalDR(i,j, startColor)) {
            y = i + 1;
            x = j + 1;
            while ((y < n-1 && x < n-1) && board[y][x] != startColor) {
                board[y][x] = startColor;
                y++;
                x++;
            }
        }
        if (checkDiagonalDL(i,j, startColor)) {
            y = i + 1;
            x = j - 1;
            while ((y < n - 1 && x > 0) && board[y][x] != startColor) {
                board[y][x] = startColor;
                y++;
                x--;
            }
        }
        if (checkDiagonalUL(i,j, startColor)) {
            y = i - 1;
            x = j - 1;
            while ((y > 0 && x > 0) && board[y][x] != startColor) {
                board[y][x] = startColor;
                y--;
                x--;
            }
        }
    }

    // private methods for checking all directions
    //checks immediate neighbours
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
    private boolean checkVerticalUp(int i, int j, PieceColor startColor) {
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
    private boolean checkVerticalDown(int i, int j, PieceColor startColor) {
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
    private boolean checkHorizontalRight(int i, int j, PieceColor startColor ) {
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
    private boolean checkHorizontalLeft(int i, int j, PieceColor startColor) {
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
    private boolean checkDiagonalUR(int i, int j, PieceColor startColor) {
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
    private boolean checkDiagonalDR(int i, int j, PieceColor startColor) {
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
    private boolean checkDiagonalDL(int i, int j, PieceColor startColor) {
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
    private boolean checkDiagonalUL(int i, int j, PieceColor startColor) {
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
}
