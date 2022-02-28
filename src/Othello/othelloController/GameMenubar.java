package Othello.othelloController;

import Othello.menus.States;
import Othello.menus.StatesObservable;
import Othello.model.Game;
import Othello.model.Save;
import Othello.model.SaveInfo;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class GameMenubar implements ActionListener, MenuListener, Serializable {
    private JMenuBar menuBar;
    private final StatesObservable observable;
    private final Game game;
    private final Options options;

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

        // added JMenuItems with actionlistener instead of menuListener.
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

            /*case "Save Game":
                String filename = JOptionPane.showInputDialog("Enter a file name:");
                if (filename != null){
                    SaveInfo si = new SaveInfo(game.getBoard(), game.getP1(), game.getP2(), game.getCurrentColor(), options);
                    new Save().save(si, filename);
                }
                break;*/

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
        }
    }
}
