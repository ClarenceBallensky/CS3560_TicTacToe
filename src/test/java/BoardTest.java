import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import gameLogic.Board;
import gameLogic.Mark;

public class BoardTest {

    //makes sure board is empty at the start of the game
    @Test
    void testBoardStartsEmpty()
    {
        Board board = new Board();
        assertTrue(board.isEmpty(0,0));
        assertTrue(board.isEmpty(0,0));
    }

    //makes sure the marks actually appear on the board when placed
    @Test
    void testMarkPlaced()
    {
        Board board = new Board();
        assertTrue(board.place(0, 0, Mark.X));
        assertFalse(board.isEmpty(0, 0));
    }

    //makes sure the correct mark (X or O) appears on the board when placed
    @Test
    void testCorrectMarkPlaced()
    {
        Board board = new Board();
        board.place(0, 0, Mark.X);
        assertFalse(board.place(0, 0, Mark.O));
    }

    //tests if board is full after placing marks in every spot on the grid
    @Test
    void testBoardEndsFull()
    {
        Board board = new Board();
        for (int r = 0; r < 3; r++)
        {
            for (int c = 0; c < 3; c++)
            {
                board.place(r, c, Mark.O);
            }
        }
        assertTrue(board.isFull());
    }
}
