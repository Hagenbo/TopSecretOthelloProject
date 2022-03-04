package Othello.model;

import java.io.*;
import java.util.function.Consumer;

/**
 * Class containing information regaring a specific game of Othello
 * @Version 2022-03-04
 */
public class Game implements Serializable {

    private boolean isBlackTurn;
    private PieceColor currentColor;
    private PieceColor fakeColor = PieceColor.BLACK;
    private Consumer<PieceColor> onGameOver;
    private Board board;

    //some additions: See comments in player-class
    private final Player player1;
    private final Player player2;

    /**
     * Contructor that creates a game by initalizing the variables player1, player 2 and board
     * @param p1 - name of player1
     * @param p2 - name of player 2
     * @param b - the board for this game
     */
    public Game(String p1, String p2, Board b) {
        this.isBlackTurn = true;
        setColor();
        this.board = b;

        //assign players and Colors. See comments in player-class
        this.player1 = new Player(p1);
        this.player2 = new Player(p2);
        player1.assignColor(PieceColor.BLACK);
        player2.assignColor(PieceColor.WHITE);
    }

    /**
     * Sets variable onGameOver as c when a game is over.
     * @param c - color of the winning player
     */
    public void setOnGameOver(Consumer<PieceColor> c) {
        onGameOver = c;
    }

    /**
     * Updates currentColor after changing turns
     */
    public void setColor() {
        if (isBlackTurn) {
            currentColor = PieceColor.BLACK;
        } else {
            currentColor = PieceColor.WHITE;
        }
    }

    /**
     * Returns the color of the player whose turn it is
     * @return PieceColor currentColor
     */
    public PieceColor getCurrentColor() {
        return currentColor;
    }

    /**
     * Returns a dummy version of currentColor.
     * Used only for updating Jlabel on OthelloView,
     * specifically for multiplayergames
     * @return PieceColor fakeColor
     */
    public PieceColor getFakeColor() {
        return fakeColor;
    }

    /**
     * Changes whose turn it is and calls setColor()
     */
    public void changeTurn() {
        isBlackTurn = !isBlackTurn;
        setColor();
    }

    /**
     * Changes whose turn it is for multiplayergames
     */
    public void multiChangeTurn(){
        isBlackTurn = !isBlackTurn;
    }

    /**
     * Updates the dummy-version of currentColor
     */
    public void changeTurnLabel(){
        if(fakeColor == PieceColor.BLACK) {
            fakeColor = PieceColor.WHITE;
        }
        else fakeColor = PieceColor.BLACK;
    }

    /**
     * Calls the countPieces-method from board-object and returns string with the winning color.
     * @return String winner
     */
    public String gameOver() {
        String winner = board.countPieces();

        if (winner.equals("Black")) {
            if (onGameOver != null) {
                onGameOver.accept(PieceColor.BLACK);
            }

        } else if (winner.equals("White")) {
            if (onGameOver != null) {
                onGameOver.accept(PieceColor.WHITE);
            }
        } else {
            if (onGameOver != null) {
                onGameOver.accept(PieceColor.EMPTY);
            }
            
        }
        return winner;
    }

    /**
     * Returns the board of the game instance
     * @return Board
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Returns player1 of the game instance
     * @return Player player1
     */
    public Player getP1(){
        return player1;
    }

    /**
     * Returns player2 of the game instance
     * @return Player player2
     */
    public Player getP2(){
        return player2;
    }

}

