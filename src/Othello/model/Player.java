package Othello.model;

import java.io.Serializable;

public class Player implements Serializable {

    private final String userName;
    private PieceColor color;

    public Player(String uname) {
        this.userName = uname;
    }

    public void assignColor(PieceColor c) {  //called when a game starts
        this.color = c;
    }
    public String getPlayerName() {
        return this.userName;
    }
}
