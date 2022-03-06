
package Othello.menus;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import Othello.model.*;
import Othello.othelloController.*;
import Othello.server.*;

/**
 * Main class for the program that sets up the JFrame of the application. Implements PropertyChangeListener
 * @Author Viktoria Hagenbo, Lovisa Rosin, Casper von Schenck, Ernst Näslund, Alexander Bratic
 * @Version 2022-03-06
 */

public class StartApp extends JFrame implements PropertyChangeListener {

    private States state;
    private final Options options;
    private final StartPanel sp;
    private final OptionsPanel op;
    private final RulesPanel rp;
    private final WaitingRoom wr;
    private final StatesObservable observable;
    private static final int n = 8;

    /**
     * Contructor that sets up the JFrame. Initializes the state to START and creates the observer that
     * observes the states. Also creates variables for the different panels.
     */
    public StartApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 280);
        setLocation(200, 200);
        setResizable(false);

        state = States.START;
        observable = new StatesObservable();
        options = new Options();
        sp = new StartPanel(observable);
        op = new OptionsPanel(observable, options);
        rp = new RulesPanel(observable);
        wr = new WaitingRoom(observable);
        setContentPane(sp);

        observable.addPropertyChangeListener(this);
        setVisible(true);
    }

    /**
     * PropertyChange for the PropertyChangeEvent evt. Checks that the event is an instance of States
     * and delegates variable state with the new value of the event. Calls setPanel.
     * @param evt - PropertyChangeEvent
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof States) {
            state = (States) evt.getNewValue();
            setPanel();
        }

    }

    /**
     * Method for setting the content panel for the JFrame. Identifies what state the application is in, and acts
     * accordingly to that state to set up the right content to be displayed or acted on.
     * For load: lets the user enter a file name the want to load and gets the right information from
     * SaveInfo which has information stored about previous saved games. Then delegates game, player 1, player 2,
     * and options from that saved game and sets up the view correctly.
     * @throws IOException - if the game can't load correctly
     * @throws ClassNotFoundException - if the file name the user entered doesn't exist.
     */
    private void setPanel() {
        if (state == States.START) {
            this.setJMenuBar(null);
            this.setSize(500, 275);
            setContentPane(sp);
            setResizable(false);
            validate();
        } else if (state == States.OPTIONS) {
            setContentPane(op);
            validate();
        } else if (state == States.RULES) {
            setContentPane(rp);
            validate();
        }
        else if (state == States.SINGLEPLAYER) {
            Game game = new Game("player1", "player2", new Board(n));
            new GameMenubar(game, options, observable, this);
            OthelloViewSinglePlayer game_GUIsingle = new OthelloViewSinglePlayer(game, options, observable);
            setContentPane(game_GUIsingle);
            setSize(700, 700);
            setResizable(false);
            game_GUIsingle.revalidate();
            game_GUIsingle.flipButtons();
            validate();
        }
        else if (state == States.MULTIPLAYERWAITINGROOM) {
            setContentPane(wr);
            validate();
        }
        else if (state == States.LOAD) {
            String filename = JOptionPane.showInputDialog("Give a file name:");
            if (filename != null) {
                try {
                    SaveInfo si = new Load().load(filename);
                    Game game = new Game(si.getP1(), si.getP2(), si.getBoard());
                    if (game.getCurrentColor() != si.getPlayerTurn()) {
                        game.changeTurn();
                    }
                    new GameMenubar(game, si.getOptions(), observable, this);
                    OthelloView game_GUI = new OthelloView(game, si.getOptions(), observable);
                    new DisDosUpdater(game_GUI);
                    setContentPane(game_GUI);
                    setSize(700, 700);
                    setResizable(false);
                    game_GUI.revalidate();
                    game_GUI.flipButtons();


                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "File " + filename + " not found :( Please check spelling.", "Error", JOptionPane.ERROR_MESSAGE);

                } catch (ClassNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "Can't load file " + filename, "Error", JOptionPane.ERROR_MESSAGE);
                    observable.setValue(States.START);
                }
            }

        } else if (state == States.REMATCH) {
            observable.setValue(States.SINGLEPLAYER);

        }
        if (state == States.MULTIPLAYER) {
            Game game = new Game("player1", "player2", new Board(n));
            new GameMenubar(game, options, observable, this);
            OthelloView game_GUI = new OthelloView(game, options, observable);
            new DisDosUpdater(game_GUI);
            setSize(700, 700);
            setResizable(false);
            setContentPane(game_GUI);

            game_GUI.revalidate();
            game_GUI.flipButtons();
            validate();

        }

    }

    /**
     * Main method that starts the application.
     * @param args - String of arguments
     */
    public static void main(String[] args) {
        StartApp start = new StartApp();
    }
}


