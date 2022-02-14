package Othello.model;

import java.io.Serializable;

// So far: a new player is created with each new game and their information is stored when a game is saved
// do we want an account to be set up that can be reused for several games?
public class Player implements Serializable {

    private final String userName;
    //private String password;
    private PieceColor color;
    //ip?

    public Player(String uname) {
        this.userName = uname;
        //this.password = password;
    }

    public void assignColor(PieceColor c) {  //called when a game starts
        this.color = c;
    }

    public PieceColor getColor() {
        return this.color;
    }

    public String getPlayerName() {
        return this.userName;
    }
}
