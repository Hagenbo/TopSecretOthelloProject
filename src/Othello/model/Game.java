package Othello.model;

import java.io.*;
import java.util.function.Consumer;

public class Game implements Serializable {

    private int gameID;
    private boolean isBlackTurn;
    private PieceColor currentColor;
    private Consumer<PieceColor> onGameOver;
    private Board board;

    //some additions: See comments in player-class
    private final Player player1;
    private final Player player2;

    public Game(String p1, String p2, Board b) {
        this.isBlackTurn = true;
        /*Random r = new Random();        // this may not be used
        this.gameID = r.nextInt();      // this may not be used*/
        setColor();
        this.board = b;

        //assign players and Colors. See comments in player-class
        this.player1 = new Player(p1);
        this.player2 = new Player(p2);
        player1.assignColor(PieceColor.BLACK);
        player2.assignColor(PieceColor.WHITE);
    }

    //observer-delen
    public void setOnGameOver(Consumer<PieceColor> c) {
        onGameOver = c;
    }


    public void setColor() {
        if (isBlackTurn) {
            currentColor = PieceColor.BLACK;
        } else {
            currentColor = PieceColor.WHITE;
        }
    }

    public PieceColor getCurrentColor() {
        return currentColor;
    }

    public void changeTurn() {
        isBlackTurn = !isBlackTurn;
        setColor();
    }

    public void multiChangeTurn(){
        isBlackTurn = !isBlackTurn;
    }

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

    public Board getBoard() {
        return this.board;
    }

    public Player getP1(){
        return player1;
    }

    public Player getP2(){
        return player2;
    }

}

