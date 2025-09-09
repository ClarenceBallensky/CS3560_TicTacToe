package gameUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Sets up window
public class GameApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Load in scene builder file
        Parent root = FXMLLoader.load(getClass().getResource("/BoardView.fxml"));
        Scene scene = new Scene(root);


        stage.setResizable(false);  // Window cannot be resized for consistency
        stage.setTitle("Tic-Tac-Toe");
        stage.setScene(scene);
        stage.show();
    }
}
