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
}
