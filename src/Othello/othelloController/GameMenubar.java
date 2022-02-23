package Othello.othelloController;

import Othello.menus.States;
import Othello.menus.StatesObservable;
import Othello.model.Game;
import Othello.model.Save;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.io.Serializable;

public class GameMenubar implements MenuListener, Serializable {
    private JMenuBar menuBar;
    private final StatesObservable observable;
    private final Game game;
    private final Options options;
    private Save save;

    public GameMenubar(Game game, Options options, StatesObservable so, JFrame f){
        observable = so;
        this.options= options;
        this.game = game;
        createMenuBar(f);
    }

    private void createMenuBar(JFrame frame) {
        this.menuBar = new JMenuBar();
        JMenu quit = new JMenu("Quit");
        quit.addMenuListener(this);
        menuBar.add(quit);

        //TODO ask if player is certain they want to withdraw, update panel how?
        JMenu withdraw = new JMenu("Withdraw");
        withdraw.addMenuListener(this);
        menuBar.add(withdraw);

        JMenu toggleSound = new JMenu("Toggle sound");
        toggleSound.addMenuListener(this);
        menuBar.add(toggleSound);

        JMenu saveGame = new JMenu("Save Game");
        menuBar.add(saveGame);
        saveGame.addMenuListener(this);

        frame.setJMenuBar(menuBar);
        //add(menuBar);
    }

    @Override
    public void menuSelected(MenuEvent e) {
        Object obj = e.getSource();
        if (!(obj instanceof JMenu j)) {
            return;
        }
        String str = j.getText();

        switch (str) {
            case "Withdraw":
                //TODO add prompt asking if they rly want to withdraw
                //setJMenuBar(null);
                //setSize(500, 500);
                observable.setValue(States.START);
                break;

            case "Save Game":
                String filename = JOptionPane.showInputDialog("Enter a file name:");
                game.save(game, filename);
                //saveTest.SaveFile(game.getModel(), filename);
                break;

            case "Quit":
                observable.setValue(States.START);
                break;

            case "Toggle sound":
                options.toggleSound();
                break;
        }
    }


    @Override
    public void menuDeselected(MenuEvent e) {
    }
    @Override
    public void menuCanceled(MenuEvent e) {
    }
}
