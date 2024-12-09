import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * A test stage for setting up and displaying the game interface.
 * This class initializes the main stage, creates a canvas,
 * and sets up the game controller to handle user inputs and game logic.
 *
 * @author Spas Dikov, Yuliia Shubina
 * @version 1.0.4
 */
public class ControllersTestStage extends Application {

    /**
     * Initializes and displays the main stage for the game.
     * Creates a scene, sets up the canvas, and connects the game controller
     * to handle keyboard events for game interactions.
     *
     * @param primaryStage the primary stage to be displayed
     */
    public void start(Stage primaryStage) {
        // Create a new pane to hold our GUI.
        Group root = new Group();

        // Create a scene based on the pane, with a black background.
        Scene scene = new Scene(root, 1400, 800, Color.BLACK);

        // File path to the level configuration for level one.
        String levelOne = "src/resources/levels/level1.txt";

        // Create a new canvas for drawing the game world.
        Canvas canvas = new Canvas(1400, 800);

        // Initialize the game controller with the canvas and level file.
        GameController gameController = new GameController(canvas, levelOne);

        // Add a key event filter to handle key presses during the game.
        scene.addEventFilter(KeyEvent.KEY_PRESSED,
                gameController::handleEvent);

        // Add the canvas to the root group to display it.
        root.getChildren().add(canvas);

        // Set the scene and show the primary stage.
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main entry point of the JavaFX application for testing controllers.
     * Launches the application and displays the game stage.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
