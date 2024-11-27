import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class ControllersTestStage extends Application {
    public void start(Stage primaryStage) {
        // Create a new pane to hold our GUI
        Group root = new Group();

        // Create a scene based on the pane.
        Color bg = Color.web("#4B2C3D");
        Scene scene = new Scene(root, 640, 400, bg);


        SaveLoadController slc = new SaveLoadController();
        Map mapToDisplay = slc.loadFromFile("src/resources/levels/level1.txt");

        Canvas canvas = new Canvas(640, 400);

        GraphicsController graphicsController =
                new GraphicsController(canvas, mapToDisplay);
        graphicsController.drawGame();

        scene.addEventFilter(KeyEvent.KEY_PRESSED,
                graphicsController::handleEvent);

//        slc.saveMapToFile(mapToDisplay);
        root.getChildren().add(canvas);


        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
