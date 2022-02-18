package Othello.othelloController;

import Othello.menus.States;
import Othello.menus.StatesObservable;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class GameMenubar extends JPanel implements MenuListener {
    private JMenuBar menuBar;
    private StatesObservable obsrvble;
    private boolean soundOn = true;

    public GameMenubar(StatesObservable so){
        obsrvble = so;
        createMenuBar();
    }

    private void createMenuBar() {
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
                obsrvble.setValue(States.START);
                break;

            case "Save Game":
                //String filename = JOptionPane.showInputDialog("Enter a file name:");
                //save(game.getModel(), filename);
                //saveTest.SaveFile(game.getModel(), filename);
                break;

            case "Quit":
                obsrvble.setValue(States.START);
                break;

            case "Toggle sound":
                toggleSound();
                break;
        }
    }

    public void toggleSound(){
        soundOn = !soundOn;
    }

    public boolean isSoundOn(){
        return soundOn;
    }



    @Override
    public void menuDeselected(MenuEvent e) {
    }
    @Override
    public void menuCanceled(MenuEvent e) {
    }



}
