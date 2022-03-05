package Othello.model;

import java.io.Serializable;

/**
 * Class containing variables representing the basic information of a game
 * and getter-methods for unpacking this information
 * @version 2022-03-04
 */
public class SaveInfo implements Serializable {
    private Board b;
    private Player p1;
    private Player p2;
    private PieceColor playerTurn;
    private Options o;

    /**
     * Constructor that creates a SaveInfo-object by saving board, players, whose turn it os and options
     * in the variables of the instance
     * @param b
     * @param p1
     * @param p2
     * @param playerTurn
     * @param o
     */
    public SaveInfo(Board b, Player p1, Player p2, PieceColor playerTurn, Options o) {
        this.b = b;
        this.p1 = p1;
        this.p2 = p2;
        this.playerTurn = playerTurn;
        this.o = o;
    }

    /**
     * Returns username of player1
     * @return String player
     */
    public String getP1(){
        return p1.getPlayerName();
    }

    /**
     * Returns username player2
     * @return String player
     */
    public String getP2(){
        return p2.getPlayerName();
    }

    /**
     * Returns a game board
     * @return Board b
     */
    public Board getBoard(){
        return b;
    }

    /**
     * Returns the color of the player whose turn it is
     * @return PieceColor playerturn
     */
    public PieceColor getPlayerTurn(){
        return playerTurn;
    }

    /**
     * Returns options of SaveInfo
     * @return Options o
     */
    public Options getOptions(){
        return o;
    }



}
