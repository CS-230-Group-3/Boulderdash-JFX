import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Controller for managing the level selection and game state.
 * This handles the logic for selecting levels, displaying high scores,
 * and continuing an in-progress game.
 * @author Spas Dikov
 * @version 1.0.0
 */
public class LevelController {
    private static final double DISABLED_ELEMENT_OPACITY = 0.5;

    @FXML
    private AnchorPane selectLevelWindow;

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
    private Button backButton;

    @FXML
    private Button continueButton;

    private static int MAX_LEVEL = 3;

    /**
     * Initializes the level selection screen by populating high score lists
     * and setting up event handlers based on the current user's unlocked levels
     * and whether they have a level in progress.
     */
    @FXML
    void initialize() {
        // Populate high score lists
        populateHighScores();

        selectLevel1.setUserData("level1");
        selectLevel2.setUserData("level2");
        selectLevel3.setUserData("level3");

        // Handle level selection based on unlocked levels or level in progress
        if (Data.getInstance().getCurrentUser().hasLevelInProgress()) {
            handleLevelInProgress();
        } else {
            handleUnlockedLevels();
        }
    }

    /**
     * Populates the high score lists for all available levels.
     */
    private void populateHighScores() {
        // Populate level 1 high scores
        Level levelOne = Data.getInstance().getAvailableLevels().getFirst();
        for (HighScore hs : levelOne.getHighScores()) {
            levelOneHighScores.getItems().add(hs.getUserName() + "\t\t" + hs.getScore());
        }

        // Populate level 2 high scores
        Level levelTwo = Data.getInstance().getAvailableLevels().get(1);
        for (HighScore hs : levelTwo.getHighScores()) {
            levelTwoHighScores.getItems().add(hs.getUserName() + "\t\t" + hs.getScore());
        }

        // Populate level 3 high scores
        Level levelThree = Data.getInstance().getAvailableLevels().getLast();
        for (HighScore hs : levelThree.getHighScores()) {
            levelThreeHighScores.getItems().add(hs.getUserName() + "\t\t" + hs.getScore());
        }
    }

    /**
     * Handles level selection and unlocking based on the user's progress.
     */
    private void handleUnlockedLevels() {
        User currentUser = Data.getInstance().getCurrentUser();

        // Check number of unlocked levels and adjust UI accordingly
        if (currentUser.getUnlockedLevels().size() < 2) {
            // 1 level unlocked
            selectLevel2.setOpacity(DISABLED_ELEMENT_OPACITY);
            selectLevel3.setOpacity(DISABLED_ELEMENT_OPACITY);
            selectLevel1.setOnMouseClicked(event -> playLevel(event, selectLevel1));
            levelOneHighScores.setOnMouseClicked(event -> playLevel(event, selectLevel1));
        } else if (currentUser.getUnlockedLevels().size() < MAX_LEVEL) {
            // 2 levels unlocked
            selectLevel3.setOpacity(DISABLED_ELEMENT_OPACITY);
            selectLevel1.setOnMouseClicked(event -> playLevel(event, selectLevel1));
            levelOneHighScores.setOnMouseClicked(event -> playLevel(event, selectLevel1));
            selectLevel2.setOnMouseClicked(event -> playLevel(event, selectLevel2));
            levelTwoHighScores.setOnMouseClicked(event -> playLevel(event, selectLevel2));
        } else {
            // All levels unlocked
            selectLevel1.setOnMouseClicked(event -> playLevel(event, selectLevel1));
            levelOneHighScores.setOnMouseClicked(event -> playLevel(event, selectLevel1));
            selectLevel2.setOnMouseClicked(event -> playLevel(event, selectLevel2));
            levelTwoHighScores.setOnMouseClicked(event -> playLevel(event, selectLevel2));
            selectLevel3.setOnMouseClicked(event -> playLevel(event, selectLevel3));
            levelThreeHighScores.setOnMouseClicked(event -> playLevel(event, selectLevel3));
        }
    }

    /**
     * Handles the case when a level is in progress, disabling level selection
     * and enabling the continue button.
     */
    private void handleLevelInProgress() {
        selectLevel1.setOpacity(DISABLED_ELEMENT_OPACITY);
        selectLevel2.setOpacity(DISABLED_ELEMENT_OPACITY);
        selectLevel3.setOpacity(DISABLED_ELEMENT_OPACITY);

        continueButton.setOpacity(1);
        continueButton.setOnAction(this::handleContinue);
    }

    /**
     * Continues the in-progress level by loading the appropriate level data and
     * transitioning to the game window.
     *
     * @param event The action event for continuing the level.
     */
    private void handleContinue(ActionEvent event) {
        User currentUser = Data.getInstance().getCurrentUser();
        Level levelToLoad = new Level(currentUser.getName() + " " + currentUser.getCurrentLevel().getLevelName());

        continueLevel(event, levelToLoad);
    }

    /**
     * Starts the selected level, updating the game window with the level data.
     *
     * @param event The mouse event that triggered the level selection.
     * @param selectedPane The pane representing the selected level.
     */
    private void playLevel(MouseEvent event, Pane selectedPane) {
        try {
            Level levelToStart = getLevelFromPane(selectedPane);
            Data.getInstance().getCurrentUser().setCurrentLevel(levelToStart);
            if (levelToStart != null) {
                GameWindowController.setLevel(levelToStart);
            }

            selectLevelWindow = (AnchorPane) FXMLLoader.load(getClass().getResource("GameWindow.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(selectLevelWindow, MainUI.MAIN_WINDOW_WIDTH, MainUI.MAIN_WINDOW_HEIGHT);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Continues an in-progress level, loading the level and transitioning to the game window.
     *
     * @param event The action event for continuing the level.
     * @param levelToLoad The level to load.
     */
    private void continueLevel(ActionEvent event, Level levelToLoad) {
        try {
            if (levelToLoad != null) {
                GameWindowController.setLevel(levelToLoad);
            }

            selectLevelWindow = (AnchorPane) FXMLLoader.load(getClass().getResource("GameWindow.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(selectLevelWindow, MainUI.MAIN_WINDOW_WIDTH, MainUI.MAIN_WINDOW_HEIGHT);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the level associated with the given pane.
     *
     * @param pane The pane representing the selected level.
     * @return The level corresponding to the selected pane.
     */
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

    /**
     * Handles the back button click, navigating the user back to the "choose-user" screen.
     *
     * @param event The action event for the back button.
     */
    private void handleBackButton(ActionEvent event) {
        try {
            selectLevelWindow = (AnchorPane) FXMLLoader.load(getClass().getResource("choose-user.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(selectLevelWindow, MainUI.MAIN_WINDOW_WIDTH, MainUI.MAIN_WINDOW_HEIGHT);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
