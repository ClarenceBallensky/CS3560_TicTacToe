package gameLogic;
import java.util.Scanner;


public class HumanPlayer extends Player
{
    private Scanner in;
    private int[] selectedMove;

    public HumanPlayer ( String name, Mark mark, Scanner in)
    {
        super(name, mark);
        this.in = in;
    }

    public HumanPlayer (String name, Mark mark)
    {
        super(name, mark);
    }


    // Selected move from UI
    public void setSelectedMove(int row, int col) {
        this.selectedMove = new int[]{row, col};
    }


    @Override
    public int[] chooseMove ( Board board )
    {
        // Wait until UI sets a move
        while (selectedMove == null || !board.isEmpty(selectedMove[0], selectedMove[1])) {
            try {
                Thread.sleep(50); // poll until user clicks
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int[] move = selectedMove;
        selectedMove = null; // reset move
        return move;
    }
}
