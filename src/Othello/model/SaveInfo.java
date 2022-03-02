package Othello.model;

import java.io.Serializable;

public class SaveInfo implements Serializable {
    private Board b;
    private Player p1;
    private Player p2;
    private PieceColor playerTurn;
    private Options o;

    public SaveInfo(Board b, Player p1, Player p2, PieceColor playerTurn, Options o) {
        this.b = b;
        this.p1 = p1;
        this.p2 = p2;
        this.playerTurn = playerTurn;
        this.o = o;
    }

    public String getP1(){
        return p1.getPlayerName();
    }

    public String getP2(){
        return p2.getPlayerName();
    }

    public Board getBoard(){
        return b;
    }

    public PieceColor getPlayerTurn(){
        return playerTurn;
    }

    public Options getOptions(){
        return o;
    }



}
