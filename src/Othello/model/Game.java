package Othello.model;

import java.io.*;
import java.util.function.Consumer;

public class Game implements Serializable {

    // behövs vid serializable: private static final long serialVersionUID = 1L;
    private int gameID;
    private boolean isBlackTurn;
    private PieceColor currentColor;
    private Consumer<PieceColor> onGameOver;
    private final Board board;

    //some additions: See comments in player-class
    private final Player player1;
    private final Player player2;

    public Game(String p1, String p2, Board b) {
        this.isBlackTurn = true;
        /*Random r = new Random();        // this may not be used
        this.gameID = r.nextInt();      // this may not be used*/
        setColor();
        this.board = b;

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

    private void setColor() {
        if (isBlackTurn) {
            currentColor = PieceColor.BLACK;
        } else {
            currentColor = PieceColor.WHITE;
        }
    }

    public PieceColor getCurrentColor() {
        return currentColor;
    }

    public void changeTurn() {
        isBlackTurn = !isBlackTurn;
        setColor();
    }

    public String gameOver() {
        String winner = board.countPieces();
        //TODO add prompt with an "ok"-button saying who is the winner.
        // when ok_button is pressed the game ends and returns to main menu. Should be in view somehow...
        if (winner.equals("Black")) {
            if (onGameOver != null) {
                onGameOver.accept(PieceColor.BLACK);
            }

        } else if (winner.equals("White")) {
            if (onGameOver != null) {
                onGameOver.accept(PieceColor.WHITE);
            }
        } else {
            //TODO add for draw
            //System.out.println("It's a draw!");
            System.out.println("Draw");
        }
        return winner;
    }

    public Game getGame() {
        return this;
    }

    public Board getBoard() {
        return board;
    }

    //Methods for save and load - maybe remove

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

/*
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
            return new Game("player1","player2", board); //tillfällig lösning tills getGame funkar
        }
    }*/
}

