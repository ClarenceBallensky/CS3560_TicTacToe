package gameUI;

import gameLogic.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

// connects logic classes to UI
public class GameController {

    @FXML
    private GridPane boardGrid;

    private Board board = new Board();
    private HumanPlayer humanPlayer = new HumanPlayer("Human", Mark.X);
    private AIPlayer ai = new AIPlayer("Computer", Mark.O, new RandomStrategy());;
    private Player current = null;

    @FXML
    private void handleMove(ActionEvent e){
        if (current == humanPlayer){
            Button clickedButton = (Button) e.getSource();

            int row = GridPane.getRowIndex(clickedButton);
            int col = GridPane.getColumnIndex(clickedButton);

            // Tell HumanPlayer selected move
            humanPlayer.setSelectedMove(row, col);
            board.place(row, col, current.mark());
            clickedButton.setText(current.mark().toString());
            current = ai;
            handleAIMove();

        }
    }

    @FXML
    private void handleAIMove(){
        int[] move = ai.chooseMove(board);
        int row = move[0];
        int col = move[1];

        board.place(row, col, ai.mark());

        // Find button at row/col
        for (javafx.scene.Node node : boardGrid.getChildren()) {
            int r = GridPane.getRowIndex(node);
            int c = GridPane.getColumnIndex(node);

            if (r == row && c == col) {
                ((Button) node).setText(ai.mark().toString());
                break;
            }
        }

        current = humanPlayer; // back to human
    }

    @FXML
    private void playGame(ActionEvent e){
        current = humanPlayer;
    }


}
