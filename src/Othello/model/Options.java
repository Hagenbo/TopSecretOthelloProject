package Othello.model;

import java.io.Serializable;

/**
 * Class containing variables and methods regarding game options
 * @Author Viktoria Hagenbo, Lovisa Rosin, Casper von Schenck, Ernst Näslund, Alexander Bratic
 * @version 2022-03-04
 */
public class Options implements Serializable {
    private Boolean soundOn;

    public Options() {
        soundOn = true;
    }

    /**
     * Methods that toggles value of soundOn
     */
    public void toggleSound() {
        soundOn = !soundOn;
    }

    /**
     * Method that checks whether sound is on or off
     * @return String "On", "Off"
     */
    public String isSoundOn() {
        if (soundOn) {
            return "On";
        }
        return "Off";
    }
}
