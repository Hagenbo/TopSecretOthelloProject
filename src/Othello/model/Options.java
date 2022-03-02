package Othello.model;

import java.io.Serializable;

public class Options implements Serializable {
    private Boolean soundOn;

    public Options() {
        soundOn = true;
    }

    public void toggleSound() {
        soundOn = !soundOn;
    }

    public String isSoundOn() {
        if (soundOn) {
            return "On";
        }
        return "Off";
    }
}
