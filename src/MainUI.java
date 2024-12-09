import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

/**
 * Main class to launch the JungleGameStart application.
 * Initializes the primary stage and scene for the game.
 * @author Spas Dikov, Yuliia Shubina
 */
public class MainUI extends Application {

    public static final int MAIN_WINDOW_WIDTH = 1400;
    public static final int MAIN_WINDOW_HEIGHT = 800;

    /**
     * Starts the application and sets up the primary stage.
     * Loads the FXML view and sets the scene for the main window.
     *
     * @param stage The primary stage for this application.
     * @throws IOException If the FXML file cannot be loaded.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
        stage.setTitle("JungleGameStart");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launches the JavaFX application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        launch();
    }
}
