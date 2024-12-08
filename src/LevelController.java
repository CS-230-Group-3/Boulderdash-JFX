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

    @FXML
    private ListView levelTwoHighScores;

    @FXML
    private ListView levelThreeHighScores;

    @FXML
    void initialize() {


//        selectLevel2.setOnMouseClicked(event ->
//          playLevel(event, selectLevel2));
//        selectLevel3.setOnMouseClicked(event ->
//          playLevel(event, selectLevel3));

        //Populate lvl 1 HS
        Level levelOne = Data.getInstance().getAvailableLevels().getFirst();
        for (HighScore hs: levelOne.getHighScores()) {
            levelOneHighScores.getItems().add(
                    hs.getUserName() + "\t\t" + hs.getScore());
        }

        //Populate lvl 2 HS
        Level levelTwo = Data.getInstance().getAvailableLevels().get(1);
        for (HighScore hs: levelTwo.getHighScores()) {
            levelTwoHighScores.getItems().add(
                    hs.getUserName() + "\t\t" + hs.getScore());
        }

        //Populate lvl 3 HS
        Level levelThree = Data.getInstance().getAvailableLevels().getLast();
        for (HighScore hs: levelThree.getHighScores()) {
            levelThreeHighScores.getItems().add(
                    hs.getUserName() + "\t\t" + hs.getScore());
        }

        //Hard coded level to each Pane, name needs to match level name,
        // no file extension
        selectLevel1.setUserData("level1");
        selectLevel2.setUserData("level2");
        selectLevel3.setUserData("level3");


        User currentUser = Data.getInstance().getCurrentUser();

        //TODO assign list views to take mouse click even
        if (currentUser.getUnlockedLevels().size() < 2) {
            // 1 level unlocked
            selectLevel2.setOpacity(0.5);
            selectLevel3.setOpacity(0.5);
            selectLevel1.setOnMouseClicked(event ->
                    playLevel(event, selectLevel1));

        } else if (currentUser.getUnlockedLevels().size() < 3) {
            // 2 levels unlocked
            selectLevel3.setOpacity(0.5);
            selectLevel1.setOnMouseClicked(event ->
                    playLevel(event, selectLevel1));
            selectLevel2.setOnMouseClicked(event ->
                    playLevel(event, selectLevel2));

        } else {
            //All levels unlocked
            selectLevel1.setOnMouseClicked(event ->
                    playLevel(event, selectLevel1));
            selectLevel2.setOnMouseClicked(event ->
                    playLevel(event, selectLevel2));
            selectLevel3.setOnMouseClicked(event ->
                    playLevel(event, selectLevel3));
        }

    }
    private void openNewWindow(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(fxmlFileName));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Level Window");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void playLevel(MouseEvent event, Pane selectedPane) {
        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("GameWindow.fxml"));
        try {
            Level levelToStart = getLevelFromPane(selectedPane);
            if (levelToStart != null) {
                GameWindowController.setLevel(levelToStart);
            }

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            event.consume();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Level getLevelFromPane(Pane pane) {
        String levelNameAssignedToPane = (String) pane.getUserData();
        Level levelToStart = null;
        for (Level level : Data.getInstance().getAvailableLevels()) {
            if (level.getLevelName().equals(levelNameAssignedToPane)) {
                levelToStart = level;
            }
        }
        return levelToStart;
    }


}
