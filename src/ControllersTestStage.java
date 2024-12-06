import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

public class ControllersTestStage extends Application {
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
        // Create a new pane to hold our GUI
        Group root = new Group();

        // Create a scene based on the pane.
//        Color bg = Color.web("#4B2C3D");
        Scene scene = new Scene(root, 640, 400, Color.GRAY);


        String levelOne = "src/resources/levels/level1.txt";
        Canvas canvas = new Canvas(640, 400);

        GameController gameController = new GameController(canvas, levelOne);

//        Data dataTest = new Data();
//        dataTest.setLevelsFromDirectory();
//        dataTest.addNewUser("Test User 1");
//        dataTest.addNewUser("Test User 2");
//        dataTest.addNewUser("Test User 3");
//
//        SaveLoadController.saveData(dataTest);

//        Data newData = SaveLoadController.loadData();
//        newData.getAvailableLevels().getFirst().getHighScores().add(new HighScore(
//                "Test User 1", 14
//        ));
//
//        SaveLoadController.saveData(newData);
//        newData.setLevelsFromDirectory();

//        SaveLoadController.saveData(newData);

//
//        for (User user: newData.getUsers()) {
//            System.out.println(user.getName());
//        }





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
