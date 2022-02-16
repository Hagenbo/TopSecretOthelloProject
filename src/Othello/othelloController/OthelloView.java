package Othello.othelloController;
import Othello.MyButton;
import Othello.model.*;

import javax.swing.*;
import java.awt.*;

public class OthelloView extends JPanel {

    private boolean soundOn = true;
    private static final int n = 8;
    private MyButton[][] buttons = new MyButton[n][n];
    private OthelloModel om;
    private static final Color color = new Color(0, 78, 56);

    //ska dessa nedan va private? eller ska dom ligga här såhär?
    ImageIcon transparent = new ImageIcon(getClass().getResource("/transparent.png"), "1");
    ImageIcon blackPiece = new ImageIcon(getClass().getResource("/blackPiece.png"), "2");
    ImageIcon whitePiece = new ImageIcon(getClass().getResource("/whitePiece.png"), "3");

    public OthelloView(OthelloModel model) {

        om = model; //TODO use a userinput variable
        setBackground(color);
        setLayout(new GridLayout(8, 8, 3, 3));

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
                });
            }
        }
        model.setOnGameOver((color)->{
            displayWinner(color);
        });
    }


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

    public void toggleSound(){
        soundOn = !soundOn;
    }

    public void withdraw() {
        om.gameOver();
    }


    public void displayWinner(PieceColor winner){
        JOptionPane.showMessageDialog(null, "The winner is " + winner, "Winner", JOptionPane.PLAIN_MESSAGE);
        //TODO exit game on "OK", maybe make the JOptionPane more pretty
    }

}





