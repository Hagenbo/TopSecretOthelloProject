package Othello.othelloController;
import Othello.MyButton;
import Othello.model.*;

import javax.swing.*;
import java.awt.*;

public class OthelloView extends JPanel {

    private MyButton[][] buttons;
    private Game game;
    private static final Color color = new Color(0, 78, 56);
    private final Options options;

    //ska dessa nedan va private? eller ska dom ligga här såhär?
    private final ImageIcon transparent = new ImageIcon(getClass().getResource("/transparent.png"), "1");
    private final ImageIcon blackPiece = new ImageIcon(getClass().getResource("/blackPiece.png"), "2");
    private final ImageIcon whitePiece = new ImageIcon(getClass().getResource("/whitePiece.png"), "3");

    public OthelloView(Game g, Options options) {
        this.options = options;
        this.game = g; //TODO use a userinput variable
        int n = game.getBoard().getBoardSize();
        this.buttons = new MyButton[n][n];
        setBackground(color);
        setLayout(new GridLayout(8, 8, 3, 3));

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
                add(mb);

                mb.addActionListener(e -> {
                    MyButton pressedButton = (MyButton) e.getSource();
                    int x = pressedButton.getCol();
                    int y = pressedButton.getRow();

                    if (!game.getBoard().placePieceAt(y, x, game.getCurrentColor())) {
                        if(options.isSoundOn()){
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
                });
            }
        }
        game.setOnGameOver((color)->{
            displayWinner(color);
        });
    }

    public void flipButtons() {
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

    public void setGame(Game g){
        game = g;
    }

    public Game getGame(){
        return game;
    }

    public void withdraw() {
        game.gameOver();
    }

    public void displayWinner(PieceColor winner){
        JOptionPane.showMessageDialog(null, "The winner is " + winner, "Winner", JOptionPane.PLAIN_MESSAGE);
        //TODO exit game on "OK", maybe make the JOptionPane more pretty
    }

}





