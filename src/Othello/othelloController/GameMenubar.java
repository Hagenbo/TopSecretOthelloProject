package Othello.othelloController;

import Othello.menus.States;
import Othello.menus.StatesObservable;
import Othello.model.Game;
import Othello.model.Options;
import Othello.model.Save;
import Othello.model.SaveInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class that creates a menu bar for the board. Implements ActionListener
 * for the buttons.
 * @Version 2022-03-06
 */

public class GameMenubar implements ActionListener {
    private JMenuBar menuBar;
    private final StatesObservable observable;
    private final Game game;
    private final Options options;

    /**
     * Contructor that creates a menu bar
     * @param game - the current game being played
     * @param options - for changing the options during the game
     * @param so - observer for changing the state State
     * @param f - the frame of the game
     */

    public GameMenubar(Game game, Options options, StatesObservable so, JFrame f){
        observable = so;
        this.options= options;
        this.game = game;
        createMenuBar(f);
    }

    /**
     * Method for setting up the menu bar with its buttons and delegating ActionListeners.
     * @param frame - the frame which we add the menu bar to
     */
    private void createMenuBar(JFrame frame) {
        this.menuBar = new JMenuBar();

        JMenu withdraw = new JMenu("Quit");
        JMenuItem yesChoice = new JMenuItem("Yes");
        yesChoice.addActionListener(this);

        JMenuItem noChoice = new JMenuItem("No");
        noChoice.addActionListener(this);
        withdraw.add(yesChoice);
        withdraw.add(noChoice);
        menuBar.add(withdraw);

        JMenu options = new JMenu("Options");
        JMenuItem toggleSound = new JMenuItem("Toggle sound");
        toggleSound.addActionListener(this);
        options.add(toggleSound);
        menuBar.add(options);

        JMenu save_load = new JMenu("Save/Load");
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(this);

        JMenuItem load = new JMenuItem("Load");
        load.addActionListener(this);

        save_load.add(save);
        save_load.add(load);
        menuBar.add(save_load);
        frame.setJMenuBar(menuBar);
    }

    /**
     * ActionPerformed for the ActionListeners. Identifies what button was pressed down through strings.
     * Returns if e is not an instance of JMenuItem.
     * For save: lets user enter a name for the game they want to save. Get the important
     * information through getters such as game, player 1, play 2, who's turn it is, and options and
     * delegates it to variable si of type SaveInfo.
     * For others: changes the state and notifies the observer.
     * @param e - ActionEvent
     *
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(!(obj instanceof JMenuItem m)) {
            return;
        }
        String str = m.getText();
        switch (str) {
            case "Save":
                String filename = JOptionPane.showInputDialog("Enter a file name:");
                if (filename != null) {
                    SaveInfo si = new SaveInfo(game.getBoard(), game.getP1(), game.getP2(), game.getCurrentColor(), options);
                    new Save().save(si, filename);
                }
                    break;
            case "Load":
                observable.setValue(States.LOAD);
                break;
            case "Yes":
                observable.setValue(States.START);
                break;
            case "No":
                break;
            case "Toggle sound":
                options.toggleSound();
                break;
        }
    }
}
