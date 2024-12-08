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


        Level levelOne = Data.getInstance().getAvailableLevels().getFirst();
        for (HighScore hs: levelOne.getHighScores()) {
            levelOneHighScores.getItems().add(hs.getUserName() + "\t\t" + hs.getScore());
        }

        //TODO Get scores for other levels
        //TODO Get levels available and unavailable
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
            GameWindowController.setLevel(Data.getInstance().getAvailableLevels().getFirst());

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
