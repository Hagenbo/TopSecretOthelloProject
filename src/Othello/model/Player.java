package Othello.model;

import java.io.Serializable;

/**
 * Class containing variables and methods regarding the players of the game
 * @Author Viktoria Hagenbo, Lovisa Rosin, Casper von Schenck, Ernst Näslund, Alexander Bratic
 * @version 2022-03-04
 */
public class Player implements Serializable {

    private final String userName;
    private PieceColor color;

    /**
     * Constructor that creates a player by being given a username
     * @param uname - name for user
     */
    public Player(String uname) {
        this.userName = uname;
    }

    /**
     * Assigns a color a player
     * @param c - one of the PieceColor enums
     */
    public void assignColor(PieceColor c) {  //called when a game starts
        this.color = c;
    }

    /**
     * Returns the username of a player
     * @return username
     */
    public String getPlayerName() {
        return this.userName;
    }
}
