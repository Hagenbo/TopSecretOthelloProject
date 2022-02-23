package Othello.othelloController;

public class Options {
    private Boolean soundOn;

    public Options() {
        soundOn = true;
    }

    public void toggleSound() {
        soundOn = !soundOn;
        //print sound off or sound on
    }

    public Boolean isSoundOn() {
        return soundOn;
    }
}
