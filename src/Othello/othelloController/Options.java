package Othello.othelloController;

import java.io.Serializable;

public class Options implements Serializable {
    private Boolean soundOn;

    public Options() {
        soundOn = true;
    }

    public void toggleSound() {
        soundOn = !soundOn;
        //print sound off or sound on
    }

    public String isSoundOn() {

        if (soundOn) {
            return "On";
        }
        return "Off";
    }
}
