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

/**
 * Copy with some small changes of class "OthelloView" with purpose of use in singleplayer-mode.
 * Extends JPanel.
 * @Author Viktoria Hagenbo, Lovisa Rosin, Casper von Schenck, Ernst Näslund, Alexander Bratic
 * @Version 2022-03-06
 */
public class OthelloViewSinglePlayer extends JPanel {

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

    /**
     * Constructor that initializes the parameters game, options and observable.
     * Creates a game, a board and puts buttons with ImageIcons depending on what the board put in.
     * Also creates JLabel in the corner containing the which players turn it is.
     * @param g       - Creates game.
     * @param options - Creates Options.
     * @param so      - Creates Observable.
     */
    public OthelloViewSinglePlayer(Game g, Options options, StatesObservable so) {
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

    /**
     * Sets up grid with buttons, and gives description of white, black or transparent for the pieces starting location.
     * Adds ActionListeners and then adds the buttons to boardPanel.
     * @param n - Size of n times n grid.
     * @return JPanel boardPanel.
     */
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

    /**
     * ActionEvent-method, capturing triggering when a button with an ActionListener is pushed.
     * Gets the position of the button, checks if it's a legal move, makes a noise if otherwise.
     * Updates the saveInfo-class if client wishes to save game.
     * Calls flipButtons to change ImageIcons
     * Changes turn and updates JLabel presenting whose turn it is anyway.
     * Checks if no more legal moves can be made, counts each player's pieces and who won.
     * @param e - ActionEvent
     */
    private void playerAction(ActionEvent e) {
        MyButton pressedButton = (MyButton) e.getSource();
        int x = pressedButton.getCol();
        int y = pressedButton.getRow();

        if (!game.getBoard().placePieceAt(y, x, game.getCurrentColor())) {
            if (Objects.equals(options.isSoundOn(), "On")) {
                Toolkit.getDefaultToolkit().beep();
            }
            return;
        }
        new SaveInfo(game.getBoard(), game.getP1(), game.getP2(), game.getCurrentColor(), options);
        flipButtons();
        game.changeTurn();

        if (!game.getBoard().playPossible(game.getCurrentColor())) {
            game.changeTurn();           // changes turn
            if (!game.getBoard().playPossible(game.getCurrentColor())) {  //if none of the players can make a move, the game ends.
                game.gameOver();
            }
        }
        bottomLabel.setText("Turn: " + game.getCurrentColor());
    }

    /**
     * In case it's game over, the color of the winner is sent with this method-call.
     * Shows a window of the winner, or if it's a draw and checks with client if a rematch is wished.
     * Afterwards sends player back to main menu or starts a new game depending on choice of previous question.
     * @param winner - PieceColor of whoever won, or "EMPTY" if it's a draw.
     */
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

    /**
     * Checks if any new additions to the PieceColor board and updates ImageIcons accordingly.
     */
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






