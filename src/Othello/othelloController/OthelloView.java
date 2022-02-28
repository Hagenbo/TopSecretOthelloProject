package Othello.othelloController;
import Othello.MyButton;
import Othello.menus.States;
import Othello.menus.StatesObservable;
import Othello.model.*;
import Othello.server.*;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class OthelloView extends JPanel {

    private MyButton[][] buttons;
    private final Game game;
    private static final Color color = new Color(0, 78, 56);
    private final Options options;
    private DisDosUpdater DDU;
    private final StatesObservable observable;

    //ska dessa nedan va private? eller ska dom ligga här såhär?
    private final ImageIcon transparent = new ImageIcon(getClass().getResource("/transparent.png"), "1");
    private final ImageIcon blackPiece = new ImageIcon(getClass().getResource("/blackPiece.png"), "2");
    private final ImageIcon whitePiece = new ImageIcon(getClass().getResource("/whitePiece.png"), "3");

    public OthelloView(Game g, Options options, StatesObservable so) {
        this.observable = so;
        this.options = options;
        this.game = g; //TODO use a userinput variable
        int n = game.getBoard().getBoardSize();
        this.buttons = new MyButton[n][n];
        setLayout(new BorderLayout());

        JLabel bottomLabel = new JLabel("Turn: " + game.getCurrentColor());

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(8, 8, 3, 3));
        boardPanel.setBackground(color);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                PieceColor pc = game.getBoard().getPiece(i, j);
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
                boardPanel.add(mb);


                mb.addActionListener(e -> {
                    MyButton pressedButton = (MyButton) e.getSource();
                    int x = pressedButton.getCol();
                    int y = pressedButton.getRow();

                    if (!game.getBoard().placePieceAt(y, x, game.getCurrentColor())) {
                        if(Objects.equals(options.isSoundOn(), "On")){
                            Toolkit.getDefaultToolkit().beep();
                        }
                        return;
                    }
                    game.changeTurn();
                    flipButtons();

                    if (!game.getBoard().playPossible(game.getCurrentColor())) {

                        //TODO add prompt saying move for next player is not possible, include an "ok"-button
                        game.changeTurn();           // changes turn
                        if (!game.getBoard().playPossible(game.getCurrentColor())) {  //if none of the players can make a move, the game ends.
                            game.gameOver();
                        }
                    }
                    bottomLabel.setText("Turn: " + game.getCurrentColor());
                });
            }
        }
        add(boardPanel);
        add(bottomLabel, BorderLayout.SOUTH);
        game.setOnGameOver((color)-> displayWinner(color));
    }

    public void flipButtons() {
        int n = board.getBoardSize();
        DDU = new DisDosUpdater();
        if(DDU.DisDosWaiter()){
            JOptionPane.showMessageDialog(null,"Waiting For Opponent");
        } else{

        int n = game.getBoard().getBoardSize();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                PieceColor pc = game.getBoard().getPiece(i,j);
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
    }

    public void withdraw() {
        game.gameOver();
    }

    public void displayWinner(PieceColor winner){
        int result;
        if(winner == PieceColor.EMPTY){
            result = JOptionPane.showConfirmDialog(null, "It's a draw! Do you want a rematch?" , "Game over", JOptionPane.YES_NO_OPTION);
        }
        else{
            result = JOptionPane.showConfirmDialog(null, "The winner is " + winner + "! " + "Do you want a rematch?" , "Game over", JOptionPane.YES_NO_OPTION);
        }
        if(result == JOptionPane.NO_OPTION){
            observable.setValue(States.START);
        }
        else{
            observable.setValue(States.REMATCH);
        }
    }

}





