package Othello.othelloController;
import Othello.MyButton;
import Othello.menus.States;
import Othello.menus.StatesObservable;
import Othello.model.*;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.io.Serializable;

public class OthelloView extends JPanel {

    private StatesObservable obsrvble;
    //private boolean soundOn = true;
    //private JPanel gameBoard;
    private static final int n = 8;
    private MyButton[][] buttons = new MyButton[n][n];
    private OthelloModel om;
    private GameMenubar gm;
    private static final Color color = new Color(0, 78, 56);

    //ska dessa nedan va private? eller ska dom ligga här såhär?
    ImageIcon transparent = new ImageIcon(getClass().getResource("/transparent.png"), "1");
    ImageIcon blackPiece = new ImageIcon(getClass().getResource("/blackPiece.png"), "2");
    ImageIcon whitePiece = new ImageIcon(getClass().getResource("/whitePiece.png"), "3");

    public OthelloView(OthelloModel model, StatesObservable so) {
        obsrvble = so;
        om = model; //TODO use a userinput variable
       // gameBoard = new JPanel();
        //gameBoard.setBackground(color);
        //gameBoard.setLayout(new GridLayout(8, 8, 3, 3));
        setBackground(color);
        gm = new GameMenubar(so);
        //this.add(gm, BorderLayout.NORTH);
        //this.add(gameBoard, BorderLayout.SOUTH);

        //TODO put an ICON on a button instead of text
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                PieceColor pc = om.getPiece(i, j);
                MyButton mb;
                if (pc == PieceColor.BLACK) {
                    mb = new MyButton(blackPiece, i, j);
                } else if (pc == PieceColor.WHITE) {
                    mb = new MyButton(whitePiece, i, j);
                } else {
                    mb = new MyButton(transparent, i, j);
                }
                buttons[i][j] = mb;
                mb.setBackground(color);
                add(mb);

                mb.addActionListener(e -> {
                    MyButton pressedButton = (MyButton) e.getSource();
                    int x = pressedButton.getCol();
                    int y = pressedButton.getRow();

                    if (!om.placePieceAt(y, x)) {
                        if(gm.isSoundOn()){
                            Toolkit.getDefaultToolkit().beep();
                        }
                        return;
                    }
                    flipButtons();

                    if (!om.playPossible()) {

                        //TODO add prompt saying move for next player is not possible, include an "ok"-button
                        om.changeTurn();           // changes turn
                        if (!om.playPossible()) {  //if none of the players can make a move, the game ends.
                            om.gameOver();
                        }
                    }
                });
            }
        }
        model.setOnGameOver((color)->{
            displayWinner(color);
        });
    }


    /*private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
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

        add(menuBar);
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

    @Override
    public void menuDeselected(MenuEvent e) {
    }
    @Override
    public void menuCanceled(MenuEvent e) {
    }*/


    public void flipButtons() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                PieceColor pc = om.getPiece(i,j);
                if (pc == PieceColor.BLACK) {
                    buttons[i][j].setIcon2(blackPiece);
                }
                else if (pc == PieceColor.WHITE) {
                    buttons[i][j].setIcon2(whitePiece);
                }
                else {
                    buttons[i][j].setIcon2(transparent);
                }
            }
        }
    }

    public void setModel(OthelloModel model){
        om = model;
    }

    public OthelloModel getModel(){
        return om;
    }


    public void withdraw() {
        om.gameOver();
    }


    public void displayWinner(PieceColor winner){
        JOptionPane.showMessageDialog(null, "The winner is " + winner, "Winner", JOptionPane.PLAIN_MESSAGE);
        //TODO exit game on "OK", maybe make the JOptionPane more pretty
    }

}





