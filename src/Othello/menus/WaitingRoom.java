package Othello.menus;

import javax.swing.*;
import java.awt.*;


/**
 * Class that is displayed while waiting for an opponent in a multiplayer game.
 * @Author Viktoria Hagenbo, Lovisa Rosin, Casper von Schenck, Ernst Näslund, Alexander Bratic
 * @Version 2022-03-06
 */

public class WaitingRoom extends JPanel /*implements MouseListener*/ {

    private StatesObservable observable;
    private static final Color color = new Color(0, 78, 56);
    private boolean boolStart=false;
    private JButton startButton;

    /**
     * Constructor that initializes the parameter observable.
     * Adds a logo up top and a "Press when ready" button below. State is changed
     * to multiplayer and boolStart set true when the button is pressed.
     * @param so      - Creates Observable.
     */

    public WaitingRoom(StatesObservable so) {
        observable = so;
        setBackground(color);
        JLabel imgLabel = new JLabel(new ImageIcon(getClass().getResource("/othelloLogo.png")));

        add(imgLabel, BorderLayout.NORTH);

        startButton = new JButton("Press when ready");
        startButton.addActionListener(e -> {
            observable.setValue(States.MULTIPLAYER);
            boolStart = true;
        });
        add(startButton, BorderLayout.CENTER);


    }

    /**
     * returns boolStart.
     * @return Boolean boolStart.
     */

    public boolean getStart(){
        return this.boolStart;
    }

}
