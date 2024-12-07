import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LevelController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane selectLevel1;

    @FXML
    private Pane selectLevel2;

    @FXML
    private Pane selectLevel3;

    @FXML
    private ListView levelOneHighScores;

    //@FXML
    //private ListView levelTwoHighScores;

    //@FXML
    //private ListView levelThreeHighScores;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        selectLevel1.setOnMouseClicked(this::testLvlOne);
        selectLevel2.setOnMouseClicked(event -> openNewWindow("level1.fxml"));
        selectLevel3.setOnMouseClicked(event -> openNewWindow("level1.fxml"));
        levelOneHighScores.setOnMouseClicked(this::testLvlOne);
        //levelTwoHighScores.setOnMouseClicked(event -> openNewWindow("level1.fxml"));
        //levelThreeHighScores.setOnMouseClicked(event -> openNewWindow("level1.fxml"));

        Data data = Data.getInstance();
        Level levelOne = data.getAvailableLevels().getFirst();
        System.out.println(levelOne);
        for (HighScore hs: levelOne.getHighScores()) {           //TODO: Style the spaces
            levelOneHighScores.getItems().add(hs.getUserName() + "              " + hs.getScore());
        }

        //TODO: Get scores for other levels

    }
    private void openNewWindow(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Level Window");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void testLvlOne(MouseEvent event) {
        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("GameWindow.fxml"));
        try {
            GameWindowController.setLevel(new Level("level1"));

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Level One");
            stage.show();

            event.consume();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
