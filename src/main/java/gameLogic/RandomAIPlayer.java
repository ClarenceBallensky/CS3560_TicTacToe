package gameLogic;

public class RandomAIPlayer extends Player {
    private final MoveStrategy strategy;

    public RandomAIPlayer(String name, Mark mark, MoveStrategy strategy) {
        super(name, mark);
        this.strategy = strategy;
    }

    @Override
    public int[] chooseMove(Board board) {
        return strategy.pickMove(board, mark);
    }

}
