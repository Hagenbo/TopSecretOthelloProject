package Othello.model;

import java.io.*;
import java.util.function.Consumer;

public class Game implements Serializable {

    // behövs vid serializable: private static final long serialVersionUID = 1L;
    private int gameID;
    private boolean isBlackTurn;
    private PieceColor startColor;
    private Consumer<PieceColor> onGameOver;
    private final Board board;
    private static final int n = 8;

    //some additions: See comments in player-class
    private final Player player1;
    private final Player player2;

    public Game(String p1, String p2) {
        this.isBlackTurn = true;
        /*Random r = new Random();        // this may not be used
        this.gameID = r.nextInt();      // this may not be used*/
        this.startColor = setStartColor();
        this.board = new Board(n);

        //assign players and Colors. See comments in player-class
        this.player1 = new Player(p1);
        this.player2 = new Player(p2);
        player1.assignColor(PieceColor.BLACK);
        player2.assignColor(PieceColor.WHITE);
    }

    //observer-delen
    public void setOnGameOver(Consumer<PieceColor> c) {
        onGameOver = c;
    }


    private PieceColor setStartColor() {
        if (isBlackTurn) {
            return PieceColor.BLACK;
        } else {
            return PieceColor.WHITE;
        }
    }

    public PieceColor getStartColor() {
        return startColor;
    }

    public PieceColor getPiece(int i, int j) {
        return board.getPiece(i, j);
    }

    public void changeTurn() {
        isBlackTurn = !isBlackTurn;
        startColor = setStartColor();
    }

    // method checks if the player has any possible moves
    public boolean playPossible(PieceColor startColor) {
        int possibleMoves = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board.getPiece(i, j) == PieceColor.EMPTY && board.movePossible(i, j, startColor)) {
                    possibleMoves++;
                }
            }
        }
        return (possibleMoves > 0);
    }

    public boolean placePieceAt(int i, int j) {
        //System.out.println(startColor);
        if (!board.movePossible(i, j, startColor) || board.getPiece(i, j) != PieceColor.EMPTY) {
            return false;
        }
        board.setPiece(i, j, startColor);
        board.flip(i, j, startColor);
        changeTurn();
        return true;
    }

    public String gameOver() {
        int nr_black = 0;
        int nr_white = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board.getPiece(i, j) == PieceColor.BLACK) {
                    nr_black++;
                }
                if (board.getPiece(i, j) == PieceColor.WHITE) {
                    nr_white++;
                }
            }
        }
        //TODO add prompt with an "ok"-button saying who is the winner.
        // when ok_button is pressed the game ends and returns to main menu. Should be in view somehow...
        if (nr_black > nr_white) {
            if (onGameOver != null) {
                onGameOver.accept(PieceColor.BLACK);
            }
            return "Black";

        } else if (nr_white > nr_black) {
            if (onGameOver != null) {
                onGameOver.accept(PieceColor.WHITE);
            }
            return "White";
        } else {
            //TODO add for draw
            //System.out.println("It's a draw!");
            System.out.println("Draw");
            return "Draw";
        }
    }

    public Game getGame() {
        return this;
    }

    //Methods for save and load

    public void save(Game model, String filename) {
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
    /*
    public void SaveFile(Game model, String filename) {
        try {
            //fileName should be the name of model-object we want to save
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            //model is the model object we want to save
            oos.writeObject(model);
            oos.close();
            //promt sayin file saved?


        } catch (IOException ioException) {
            //should come up as prompt
            System.out.println("Error saving");
            ioException.printStackTrace(); //for debugging
        }
    }
    */


    public Game load(String filename) {
        try {
            FileInputStream input = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(input);
            Game stored = (Game) (objectInputStream.readObject());
            objectInputStream.close();
            System.out.println("Loaded " + filename);
            return stored;
        } catch (Exception e) {
            System.out.println("load failed because " + e);
            //System.out.println("returned current game.");
            //return othelloView.getModel(); //getModel static?
            return new Game("player1","player2"); //tillfällig lösning tills getModel funkar
        }
    }
}

