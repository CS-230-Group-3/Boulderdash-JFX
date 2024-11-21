package SaveLoadController;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;

//Class for testing SaveLoadController
public class SLCTestStage extends Application {
    public void start(Stage primaryStage) {
        // Create a new pane to hold our GUI
        Group root = new Group();
        final int SPRITE_SIZE = 16;

        // Create a scene based on the pane.
        Scene scene = new Scene(root, 608, 400, Color.GRAY);


        SaveLoadController slc = new SaveLoadController();
        Map mapToDisplay = slc.loadFromFile("src/SaveLoadController/levels/level1.txt");

        javafx.scene.canvas.Canvas canvas = new Canvas(608, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();


        for (GameObjectSLC object: mapToDisplay.getObjects()) {
            gc.drawImage(object.getSprite(),
                    object.getGridPosition().getX() * SPRITE_SIZE,
                    object.getGridPosition().getY() * SPRITE_SIZE);
        }
        slc.saveMapToFile(mapToDisplay);
        root.getChildren().add(canvas);


        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
