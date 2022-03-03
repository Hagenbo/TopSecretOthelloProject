
package Othello.menus;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import Othello.othelloController.OthelloView.*;
import Othello.model.Board;
import Othello.model.Game;
import Othello.model.Load;
import Othello.model.SaveInfo;
import Othello.othelloController.*;
import Othello.server.*;


public class StartApp extends JFrame implements PropertyChangeListener {

    private OthelloView game_GUI;
    private OthelloViewSinglePlayer game_GUIsingle;
    private States state;
    private final Options options;
    private final StartPanel sp;
    private final OptionsPanel op;
    private final RulesPanel rp;
    private final WaitingRoom wr;
    private final StatesObservable observable;
    private static final int n = 8;
    private Load load;
    private Main server;


    public StartApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 280);
        setLocation(200, 200);
        setResizable(false);

        state = States.START;
        observable = new StatesObservable();
        //skickar med "samma obsrvble" till startpaneln så dom jobbar med samma lxm
        options = new Options();
        sp = new StartPanel(observable);
        op = new OptionsPanel(observable, options);
        rp = new RulesPanel(observable);
        wr = new WaitingRoom(observable);
        setContentPane(sp);

        //lägger till startApp som observer till observable
        observable.addPropertyChangeListener(this);
        setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //kollar om eventet är en instans av States, dvs att state ändrats
        if (evt.getNewValue() instanceof States) {
            //i så fall, tar det nya värdet (av state) typecast till State, sätter till state som nytt värde
            state = (States) evt.getNewValue();
            setPanel();
        }

    }

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
        // Do we want to add Options to Game instead of OthelloView? In that case we can keep the options
        // when we load a game... In that case we also need a getOptions()-method in Game.
        else if (state == States.SINGLEPLAYER) {
            Game game = new Game("player1", "player2", new Board(n));
            new GameMenubar(game, options, observable, this);
            game_GUIsingle = new OthelloViewSinglePlayer(game, options, observable);
            setContentPane(game_GUIsingle);
            setSize(700, 700);
            setResizable(false);
            setSize(600, 600);
            game_GUIsingle.revalidate();
            game_GUIsingle.flipButtons();
            validate();
        } else if (state == States.MULTIPLAYERWAITINGROOM) {
            setContentPane(wr);         //VARFÖR UPpDATERAS INTE DENNA??!?!
            validate();
            this.state = States.MULTIPLAYER;

        } else if (state == States.LOAD) {
            String filename = JOptionPane.showInputDialog("Give a file name:");
            if (filename != null) {
                try {
                    SaveInfo si = new Load().load(filename);
                    Game game = new Game(si.getP1(), si.getP2(), si.getBoard());
                    if (game.getCurrentColor() != si.getPlayerTurn()) {
                        game.changeTurn();
                    }
                    new GameMenubar(game, si.getOptions(), observable, this);
                    game_GUI = new OthelloView(game, si.getOptions(), observable);
                    setContentPane(game_GUI);
                    setSize(700, 700);
                    setResizable(false);
                    game_GUI.revalidate();
                    game_GUI.flipButtons();


                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "File " + filename + " not found :( Please check spelling.", "Error", JOptionPane.ERROR_MESSAGE);
                    //observable.setValue(States.START);

                } catch (ClassNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "Can't load file " + filename, "Error", JOptionPane.ERROR_MESSAGE);
                    observable.setValue(States.START);
                }
            }
            //else { observable.setValue(States.START);}
        } else if (state == States.REMATCH) {
            observable.setValue(States.SINGLEPLAYER);
        }
        if (state == States.MULTIPLAYER) {
            Game game = new Game("player1", "player2", new Board(n));
            new GameMenubar(game, options, observable, this);
            game_GUI = new OthelloView(game, options, observable);
            new DisDosUpdater(game_GUI);
            setSize(700, 700);
            setResizable(false);
            setContentPane(game_GUI);

            game_GUI.revalidate();
            game_GUI.flipButtons();
            validate();
        }

    }

    public static void main(String[] args) {
        StartApp start = new StartApp();
    }
}


//om man börjar ett spel och sen vill ladda men man skriver in fel namn, kommer man till startsidan igen. Kanske inte ska va så?
//om man i början vill ladda ett spel men skriver in fel namn kommer man till startsidan igen, kanske ska ha så prompten med "enter file name" kanske
//kommer upp igen?


