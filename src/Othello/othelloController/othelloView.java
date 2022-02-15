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

    private static final int n = 8;
    private MyButton[][] buttons = new MyButton[n][n];
    private othelloModel om;

    private static final Color color = new Color(0, 78, 56);

    public othelloView(othelloModel model) {


        om = model; //TODO use a userinput variable

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


        }
    }

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

    public void setModel(othelloModel model){
        om = model;
    }

    public othelloModel getModel(){
        return om;
    }

    public void toggleSound(){
        soundOn = !soundOn;
    }


}





