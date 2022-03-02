package Othello.model;

import Othello.menus.States;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ModelTests {
    @Test
    public void initialBoardDetectsCorrectPlacement() {
        Board board = new Board(8);
        assertFalse(board.placePieceAt(1, 1, PieceColor.BLACK));
        assertTrue(board.placePieceAt(2, 4, PieceColor.BLACK));
    }

    @Test
    public void testWinnerBlack(){
        Board board = new Board(8);
        Game game = new Game("p1", "p2", board);
        board.placePieceAt(2,4, PieceColor.BLACK);
        assertTrue(game.gameOver().equals("Black"));
        assertFalse(game.gameOver().equals("White"));
    }

    @Test
    public void testWinnerWhite(){
        Board board = new Board(8);
        Game game = new Game("p1", "p2", board);
        board.placePieceAt(2,3, PieceColor.WHITE);
        assertTrue(game.gameOver().equals("White"));
        assertFalse(game.gameOver().equals("Black"));
    }

    @Test
    public void testDraw(){
        Board board = new Board(8);
        Game game = new Game("p1", "p2", board);
        assertTrue(game.gameOver().equals("Draw"));
        board.placePieceAt(2,4, PieceColor.BLACK);
        assertFalse(game.gameOver().equals("Draw"));
    }

    @Test
   public void testChangeOfTurn(){
        Board board = new Board(8);
        Game game = new Game ("p1", "p2", board);
        assertTrue(game.getCurrentColor().equals(PieceColor.BLACK));
        game.changeTurn();
        assertTrue(game.getCurrentColor().equals(PieceColor.WHITE));
        assertFalse(game.getCurrentColor().equals(PieceColor.BLACK));
    }

    @Test
    public void checkPlayPossible(){
        Board board = new Board(8);
        assertTrue(board.playPossible(PieceColor.BLACK));
        assertTrue(board.playPossible(PieceColor.WHITE));
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                board.setPiece(i,j,PieceColor.BLACK);
            }
        }
        assertFalse(board.playPossible(PieceColor.WHITE));
    }

    @Test
    public void SaveLoad(){
        Board board = new Board(8);
        Game game = new Game ("p1", "p2", board);
        Options options = new Options();
        SaveInfo si1 = new SaveInfo(board,game.getP1(), game.getP2(), PieceColor.BLACK, options);
        new Save().save(si1, "testSave");

        try {
            SaveInfo si2 = new Load().load("testSave");
            assertTrue(si1.getP1().equals(si2.getP1()));
            assertTrue(si1.getP2().equals(si2.getP2()));
            assertTrue(si1.getPlayerTurn()==(si2.getPlayerTurn()));
            assertTrue(compareBoards(si1.getBoard(),si2.getBoard()));
            assertTrue(compareOptions(si1, si2));
        }
        catch (IOException e) {}
        catch (ClassNotFoundException e) {}
    }

    @Test
    public void testToggleSound(){
        Options options = new Options();
        assertTrue(options.isSoundOn().equals("On"));
        options.toggleSound();
        assertTrue(options.isSoundOn().equals("Off"));
        assertFalse(options.isSoundOn().equals("On"));
    }


    private boolean compareBoards(Board b1, Board b2) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (b1.getPiece(i, j) != b2.getPiece(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean compareOptions(SaveInfo si1, SaveInfo si2){
        return si1.getOptions().isSoundOn().equals(si2.getOptions().isSoundOn());
    }

}
