
package Othello.menus;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import Othello.model.Board;
import Othello.model.Game;
import Othello.model.Load;
import Othello.othelloController.*;


public class StartApp extends JFrame implements PropertyChangeListener {

        private OthelloView game_GUI;
        private States state;
        private final Options options;
        private final StartPanel sp;
        private final OptionsPanel op;
        private final RulesPanel rp;
        private final StatesObservable observable;
        private static final int n = 8;
        private Load load;


        public StartApp() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(500, 500);
            setLocation(200, 200);

            state = States.START;
            observable = new StatesObservable();
            //skickar med "samma obsrvble" till startpaneln så dom jobbar med samma lxm
            options = new Options();
            sp = new StartPanel(observable);
            op = new OptionsPanel(observable, options);
            rp = new RulesPanel(observable);
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
        public void setPanel(){
            if(state == States.START){
                this.setJMenuBar(null);
                setContentPane(sp);
                validate();
            }
            else if(state == States.OPTIONS){
                setContentPane(op);
                validate();
            }

            else if(state == States.RULES){
                setContentPane(rp);
                validate();
            }

            else if(state == States.PLAY){
                Board board = new Board(n);
                Game game = new Game("player1","player2", board);
                new GameMenubar(game, options, observable,this);
                game_GUI = new OthelloView(game, board, options /*, observable*/);
                //game_GUI.setGame(new Game("player1","player2"));
                setContentPane(game_GUI);
                setSize(600, 600);
                game_GUI.revalidate();
                game_GUI.flipButtons();
            }

           else if(state == States.LOAD){
               String filename = JOptionPane.showInputDialog("Give a file name:");
                /*game.setModel(load(filename));
                setPanel(game);
                createMenuBar(this);
                setSize(600, 600);
                game.revalidate();
                game.flipButtons();*/

                try {
                    Game game = new Load().load(filename);
                    new GameMenubar(game, options, observable,this);
                    game_GUI = new OthelloView(game, game.getBoard(), options);
                    setContentPane(game_GUI);
                    setSize(600, 600);
                    game_GUI.revalidate();
                    game_GUI.flipButtons();

                } catch (IOException e) {
                    //stoppa in beteende
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    //stoppa in beteende
                    e.printStackTrace();
                }




               /* Load loadFile = new Load();
                String filename = JOptionPane.showInputDialog("Give a file name:");
                Game loadGame = loadFile.load(filename);
                game_GUI = new OthelloView(loadGame, loadGame.getBoard(), options);
                new GameMenubar(loadGame, options, observable,this);
                //setModel(load(filename));
                setContentPane(game_GUI);

                setSize(600, 600);
                game_GUI.revalidate();
                game_GUI.flipButtons();

                //load("filename");*/
            }

        }


        public static void main(String[] args) {
            StartApp start = new StartApp();
        }
}



