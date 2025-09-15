package gameLogic;

import java.util.Arrays;

public class Board
{
    private final Mark[][] grid = new Mark[3][3];
    private int moves = 0;

    public Board ()
    {
        for ( Mark[] row : grid )
            Arrays.fill( row, Mark.EMPTY );
    }

    public boolean isEmpty ( int r, int c )
    {
        return grid[r][c] == Mark.EMPTY;
    }

    public boolean place ( int r, int c, Mark mark )
    {
        if ( !isEmpty (r, c) )
            return false;
        grid[r][c] = mark;
        moves++;
        return true;
    }

    public boolean isFull ()
    {
        return moves == 9;
    }

    public boolean line ( Mark a, Mark b, Mark c )
    {
        return a != Mark.EMPTY && a == b && b == c;
    }

    public Mark winner ()
    {
        // Check if there is a winner in rows
        for (int r = 0; r < 3; r++){
            if(grid[r][0] != Mark.EMPTY &&
                    grid[r][0] == grid[r][1] &&
                    grid[r][1] == grid[r][2]){
                return grid[r][0];
            }
        }

        // Check if there is a winner in columns
        for (int c = 0; c < 3; c++){
            if (grid[0][c] != Mark.EMPTY &&
                    grid[0][c] == grid[1][c] &&
                    grid[1][c] == grid[2][c]){
                return grid[0][c];
            }
        }

        // Check if there is a winner in diagonals
        // "Back" diagonal direction
        if (grid[0][0] != Mark.EMPTY &&
                grid[0][0] == grid[1][1] &&
                grid[1][1] == grid[2][2]){
            return grid[0][0];
        }
        // "Front" diagonal
        if (grid[0][2] != Mark.EMPTY &&
                grid[0][2] == grid[1][1] &&
                grid[1][1] == grid[2][0]){
            return grid[0][2];
        }
        // Else no winner
        return Mark.EMPTY;
    }

    public void print ()
    {
        for ( int r = 0; r < 3; r++ ) {
            System.out.printf(" %s | %s | %s %n", grid[r][0], grid[r][1], grid[r][2]);
            if ( r < 2)
                System.out.println("---+---+---");
        }
    }
}
