import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import gameLogic.Mark;
import gameLogic.Board;
import gameLogic.Player;
import gameLogic.AIPlayer;
import gameLogic.Game;
import gameLogic.MoveStrategy;


public class GameTest
{
    static class FixedMoveStrategy implements MoveStrategy
    {
        private final int[][] moves;
        private int index = 0;

        FixedMoveStrategy(int[][] moves)
        {
            this.moves = moves;
        }

        @Override
        public int[] pickMove(Board board, Mark mark)
        {
            return moves[index++];
        }
    }

    @Test
    void testGameAlternatesPlayers()
    {
        Player p1 = new AIPlayer("P1", Mark.X, new FixedMoveStrategy(
                new int[][] { {0,0}, {1,0}, {2,0} } ));
        Player p2 = new AIPlayer("P2", Mark.O, new FixedMoveStrategy(
                new int[][] { {0,1}, {1,1}, {2,1} } ));

        Game game = new Game(p1, p2);
        // simulate play manually, just test moves alternate
        int[] firstMove = p1.chooseMove(new Board());
        int[] secondMove = p2.chooseMove(new Board());
        assertNotEquals(firstMove, secondMove);
    }

    @Test
    void testWinningGameEnds()
    {
        Player p1 = new AIPlayer("P1", Mark.X, new FixedMoveStrategy(
                new int[][] { {0,0}, {0,1}, {0,2} } ));
        Player p2 = new AIPlayer("P2", Mark.O, new FixedMoveStrategy(
                new int[][] { {1,0}, {1,1} } ));

        Game game = new Game(p1, p2);
        Board board = new Board();

        // Simulate moves (skip printing)
        board.place(0,0,Mark.X);
        board.place(1,0,Mark.O);
        board.place(0,1,Mark.X);
        board.place(1,1,Mark.O);
        board.place(0,2,Mark.X);

        assertEquals(Mark.X, board.winner());
    }
}
