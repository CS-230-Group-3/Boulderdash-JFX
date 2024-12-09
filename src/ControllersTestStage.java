import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class ControllersTestStage extends Application {
    public void start(Stage primaryStage) {
        // Create a new pane to hold our GUI.
        Group root = new Group();

        // Create a scene based on the pane.
//        Color bg = Color.web("#4B2C3D");
        Scene scene = new Scene(root, 1920, 1080, Color.BLACK);


        String levelOne = "src/resources/saves/ඞ level1.txt";
        Canvas canvas = new Canvas(1920, 1080);

        GameController gameController = new GameController(canvas, levelOne);


        scene.addEventFilter(KeyEvent.KEY_PRESSED,
                gameController::handleEvent);

//        slc.saveMapToFile(mapToDisplay);
        root.getChildren().add(canvas);


        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
