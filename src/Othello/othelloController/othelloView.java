package Othello.othelloController;
import Othello.MyButton;
import Othello.model.*;
import java.io.*;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class othelloView extends JPanel {

    private boolean soundOn = true;
    //private JFrame frame = new JFrame();
    private static final int boardWidth = 750;
    private static final int boardHeight = 750;

    private static final int n = 8;
    private MyButton[][] buttons = new MyButton[n][n];
    private othelloModel om;

    private static final Color color = new Color(0, 78, 56);

    public othelloView(othelloModel model) {

        //TODO add menubar with items "save game" and  "withdraw"
        // implement save game

        om = new othelloModel("player1", "player2");  //TODO use a userinput variable
        frame.setTitle("Othello");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocation(800, 300);
        createMenuBar(frame);
        om = model;//new othelloModel("player1", "player2");  //TODO use a userinput variable
        //frame = frame;
        //frame.setTitle("Othello");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(boardWidth, boardHeight);
        //frame.setLocation(800, 300);
        //createMenuBar(frame);

        setBackground(color);
        setLayout(new GridLayout(8, 8, 3, 3));

        //TODO put an ICON on a button instead of text
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                PieceColor pc = om.getPiece(i, j);
                MyButton mb;
                if (pc == PieceColor.BLACK) {
                    mb = new MyButton("Black", i, j);
                } else if (pc == PieceColor.WHITE) {
                    mb = new MyButton("White", i, j);
                } else {
                    mb = new MyButton(" ", i, j);
                }
                buttons[i][j] = mb;
                mb.setBackground(color);
                add(mb);

                mb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MyButton pressedButton = (MyButton) e.getSource();

                        if (!om.placePieceAt(pressedButton.getRow(), pressedButton.getCol())) {
                            if(soundOn){
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
                    }
                });
            }
            //frame.add(this);
            //frame.setVisible(true);

        }
    }
/*
    private void createMenuBar(JFrame f) {
        JMenuBar menuBar = new JMenuBar();

        JMenu quit = new JMenu("Quit");
        menuBar.add(quit);

        JMenu withdraw = new JMenu("Withdraw");
        menuBar.add(withdraw);

        JMenu toggleSound = new JMenu("Toggle sound");
        menuBar.add(toggleSound);

        JMenu saveGame = new JMenu("Save Game");
        menuBar.add(saveGame);
        saveGame.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                String filename = JOptionPane.showInputDialog("Give a file name:");
                save(om,filename);
            }
            @Override
            public void menuDeselected(MenuEvent e) {}
            @Override
            public void menuCanceled(MenuEvent e) {}
        });

        //TODO add actionListeners, instance of?
        // dont use dynamic class

        f.setJMenuBar(menuBar);

    }
*/

    public void flipButtons() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                PieceColor pc = om.getPiece(i,j);
                if (pc == PieceColor.BLACK) {
                    buttons[i][j].setText("Black");
                }
                else if (pc == PieceColor.WHITE) {
                    buttons[i][j].setText("White");
                }
            }
        }
    }
/*
    private void save(othelloModel model, String filename) {
        try {
            FileOutputStream output = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(output);
            objectOutputStream.writeObject(model);
            objectOutputStream.flush();
            objectOutputStream.close();
            System.out.println(filename + " stored.");
        } catch (IOException e) {
            System.out.println("save failed because " + e);
        }
    }
*/
    public void setModel(othelloModel model){
        om = model;
    }

    public void toggleSound(){
        soundOn = !soundOn;
    }


        public static void main (String[]args) {
            othelloView ov = new othelloView(new othelloModel("player1", "player2"));
        }

}





