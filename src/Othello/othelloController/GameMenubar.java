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

public class GameMenubar implements ActionListener {
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

        /*
        JMenu quit = new JMenu("Quit");
        quit.addMenuListener(this);
        menuBar.add(quit);*/

        JMenu withdraw = new JMenu("Withdraw/Quit");
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
