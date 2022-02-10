package Othello.othelloController;
import Othello.MyButton;
import Othello.model.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class othelloView extends JPanel {

    private static final int n = 8;
    private MyButton[][] buttons = new MyButton[n][n];
    private othelloModel om;

    private static final Color color = new Color(0, 78, 56);

    public othelloView() {

        //TODO add menubar with items "save game" and  "withdraw"
        // implement save game

        om = new othelloModel("player1", "player2");  //TODO use a userinput variable
        JFrame gameFrame = new JFrame("Othello ");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(750, 750);
        gameFrame.setLocation(800, 300);

        JPanel gamePanel = new JPanel();
        gamePanel.setBackground(this.color);
        gamePanel.setLayout(new GridLayout(8, 8, 3, 3));

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
                mb.setBackground(this.color);
                gamePanel.add(mb);

                mb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MyButton pressedButton = (MyButton)e.getSource();

                        om.placePieceAt(pressedButton.getRow(), pressedButton.getCol());
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
            gameFrame.add(gamePanel);
            gameFrame.setVisible(true);
        }
    }

    private void flipButtons() {
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

        public static void main (String[]args){
            othelloView ov = new othelloView();
        }

}




