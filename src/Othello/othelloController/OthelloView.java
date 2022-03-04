package Othello.othelloController;
import Othello.menus.States;
import Othello.menus.StatesObservable;
import Othello.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Objects;

public class OthelloView extends JPanel {

    private ObjectOutputStream objectOutputClient;
    private MyButton[][] buttons;
    private final Game game;
    private static final Color color = new Color(0, 78, 56);
    private final Options options;
    private final StatesObservable observable;
    private JLabel bottomLabel;
    private boolean viewTurn = false;
    private final ImageIcon transparent = new ImageIcon(getClass().getResource("/transparent.png"), "1");
    private final ImageIcon blackPiece = new ImageIcon(getClass().getResource("/blackPiece.png"), "2");
    private final ImageIcon whitePiece = new ImageIcon(getClass().getResource("/whitePiece.png"), "3");


    public OthelloView(Game g, Options options, StatesObservable so) {
        this.observable = so;
        this.options = options;
        this.game = g;
        int n = game.getBoard().getBoardSize();
        this.buttons = new MyButton[n][n];
        setLayout(new BorderLayout());

        bottomLabel = new JLabel("Turn: " + game.getCurrentColor());
        add(setUpBoardPanel(n));
        add(bottomLabel, BorderLayout.SOUTH);
        game.setOnGameOver((color)-> displayWinner(color));
    }

    public Game getGame(){
        return this.game;
    }

    private JPanel setUpBoardPanel(int n) {
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
                    playerAction(e);
                });
            }
        }
        return boardPanel;
    }

    public void setObjectOutputClient(ObjectOutputStream dataOut){
        this.objectOutputClient = dataOut;
    }

    private void playerAction(ActionEvent e) {
        MyButton pressedButton = (MyButton) e.getSource();
        int x = pressedButton.getCol();
        int y = pressedButton.getRow();

            if (!game.getBoard().placePieceAt(y, x, game.getCurrentColor()) || !this.viewTurn) {
                if (Objects.equals(options.isSoundOn(), "On")) {
                    Toolkit.getDefaultToolkit().beep();
                }
                return;
            }
            new SaveInfo(game.getBoard(), game.getP1(), game.getP2(), game.getCurrentColor(), options);
            try {
                objectOutputClient.writeObject(game.getBoard());
                objectOutputClient.flush();
                objectOutputClient.reset();//NOT SURE ABOUT THIS ONE!!!
                setViewTurn(false);
                game.changeTurnLabel();
                game.multiChangeTurn();
                bottomLabel.setText("Turn: " + game.getFakeColor());
                System.out.println("Sending packages");
            } catch (IOException a) {
                a.printStackTrace();
            }

        if (!game.getBoard().playPossible(game.getCurrentColor())) {
                game.changeTurn();           // changes turn
                if (!game.getBoard().playPossible(game.getCurrentColor())) {  //if none of the players can make a move, the game ends.
                    game.gameOver();
                }
            }
        }

    public JLabel getBottomLabel(){
        return bottomLabel;
    }

    public boolean getViewTurn(){
        return this.viewTurn;
    }

    public void setViewTurn(boolean turn){
        this.viewTurn = turn;
    }

    private void displayWinner(PieceColor winner){
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

    public void flipButtons() {
        int n = game.getBoard().getBoardSize();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                PieceColor pc = game.getBoard().getPiece(i, j);
                if (pc == PieceColor.BLACK) {
                    buttons[i][j].setIcon2(blackPiece);
                } else if (pc == PieceColor.WHITE) {
                    buttons[i][j].setIcon2(whitePiece);
                } else {
                    buttons[i][j].setIcon2(transparent);
                }
            }
        }
    }
}






