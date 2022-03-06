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
 * Class containing the visual windows and configurations.
 * Extends JPanel.
 * @Version 2022-03-06
 */
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

    /**
     * Constructor that initializes the parameters game, options and observable.
     * Creates a game, a board and puts buttons with ImageIcons depending on what the board put in.
     * Also creates JLabel in the corner containing the which players turn it is.
     * @param g       - Creates game.
     * @param options - Creates Options.
     * @param so      - Creates Observable.
     */
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

    /**
     * returns game.
     * @return Game game.
     */
    public Game getGame(){
        return this.game;
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
     * Sets ObjectOutputStream to given ObjectOutputStream.
     * @param dataOut - Is an ObjectOutputStream from DisDosUpdater-class.
     */
    public void setObjectOutputClient(ObjectOutputStream dataOut){
        this.objectOutputClient = dataOut;
    }

    /**
     * ActionEvent-method, capturing triggering when a button with an ActionListener is pushed.
     * Gets the position of the button, checks if it's a legal move, makes a noise if otherwise.
     * Updates the saveInfo-class if client wishes to save game.
     * Sends an OutputStream to opponent with the move made.
     * Prints stacktrace on IOException.
     * Checks if no more legal moves can be made, counts each player's pieces and who won.
     * @param e - ActionEvent
     */
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
                objectOutputClient.reset();
                setViewTurn(false);
                game.changeTurnLabel();
                game.multiChangeTurn();
                bottomLabel.setText("Turn: " + game.getFakeColor());
                System.out.println("Sending packages");
            } catch (IOException a) {
                a.printStackTrace();
            }

        if (!game.getBoard().playPossible(game.getCurrentColor())) {
                game.changeTurn();
                if (!game.getBoard().playPossible(game.getCurrentColor())) {
                    game.gameOver();
                }
            }
        }

    /**
     * returns bottomLabel.
     * @return JLabel bottomLabel.
     */
    public JLabel getBottomLabel(){
        return bottomLabel;
    }

    /**
     * returns viewTurn.
     * @return Boolean viewTurn.
     */
    public boolean getViewTurn(){
        return this.viewTurn;
    }

    /**
     * Sets viewTurn to given boolean.
     * @param turn - keeps track on whose turn it is for DisDosUpdater();
     */
    public void setViewTurn(boolean turn){
        this.viewTurn = turn;
    }

    /**
     * In case it's game over, the color of the winner is sent with this method-call.
     * Shows a window of the winner, or if it's a draw and checks with client if a rematch is wished.
     * Afterwards sends player back to main menu or starts a new game depending on choice of previous question.
     * @param winner - PieceColor of whoever won, or "EMPTY" if it's a draw.
     */
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






