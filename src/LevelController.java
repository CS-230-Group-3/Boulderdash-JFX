import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
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

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        selectLevel1.setOnMouseClicked(event -> openNewWindow("level1.fxml"));
        selectLevel2.setOnMouseClicked(event -> openNewWindow("level1.fxml"));
        selectLevel3.setOnMouseClicked(event -> openNewWindow("level1.fxml"));

        Data data = SaveLoadController.loadData();
        Level levelOne = data.getAvailableLevels().getFirst();
        System.out.println(levelOne);
        for (HighScore hs: levelOne.getHighScores()) {
            levelOneHighScores.getItems().add(hs.getUserName() + "              " + hs.getScore());
        }

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

}
