package Othello.model;

import org.junit.jupiter.api.Test;
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
        SaveInfo si = new SaveInfo();
    }

}
