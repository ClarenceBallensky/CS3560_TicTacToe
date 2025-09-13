package gameUI;

import gameLogic.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

// connects logic classes to UI
public class GameController{

    @FXML
    private GridPane boardGrid;
    @FXML
    private Text textUI;
    @FXML
    private Button play;

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
            clickedButton.setStyle("-fx-text-fill: #6BDAFF;");

            // Check if game ended
            if (checkGameEnd()) return;

            // AI turn
            current = ai;
            handleAIMove();
        }
    }

    @FXML
    private void handleAIMove(){
        if (current == ai){
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
                    ((Button) node).setStyle("-fx-text-fill: #FF822E;");
                    break;
                }
            }

            // Check if game ended
            if (checkGameEnd()) return;

            // Human turn
            current = humanPlayer;
        }
    }

    @FXML
    private void playGame(ActionEvent e) {
        textUI.setText("Place an 'X' by clicking a box below:");
        current = humanPlayer;
        play.setDisable(true);
    }

    private boolean checkGameEnd(){
        if (board.winner() != Mark.EMPTY) {
            System.out.println("Winner: " + current.name());
            textUI.setText("The " + current.name()+" has won! Thanks for playing!");
            current = null;
            return true;
        }
        if (board.isFull()) {
            System.out.println("Draw!");
            textUI.setText("The game is a draw! Thanks for playing!");
            current = null;
            return true;
        }
        return false;
    }

}
