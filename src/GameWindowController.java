import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;


public class GameWindowController {

    @FXML
    private AnchorPane gameWindow;
    @FXML
    private Canvas gameCanvas;
    @FXML
    private AnchorPane pauseMenu;
    @FXML
    private Text pauseTitle;
    @FXML
    private VBox buttonsBox;
    @FXML
    private Button saveAndExitButton;

    @FXML
    private Text timeLeft;
    @FXML
    private Text diamondsCollected;
    @FXML
    private Text diamondsToCollect;
    @FXML
    private Text breathRemainingInSeconds;

    private boolean pauseMenuIsVisible = false;

    private GameController gameController;
    private static Level levelToLoad;
    private int remainingTimeInSeconds;

    @FXML
    void initialize() {

        gameWindow.sceneProperty().addListener(
                (observable, oldScene, newScene) -> {
                    if (newScene != null) {
                        newScene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                            gameController.handleEvent(event);
                            if (event.getCode() == KeyCode.ESCAPE) {
                                togglePauseMenu(event);
                            }
                        });
                    }
                });

        //TODO hook to SLC to save game in progress for current player
        saveAndExitButton.setOnAction(this::handleSaveAndExitButton);

        gameWindow.setFocusTraversable(true);
        gameWindow.requestFocus();

        //Change to assign from seconds in map?
        remainingTimeInSeconds = 62;

        timeLeft.setText(formatSecondsToString(remainingTimeInSeconds));
        //Update so diamonds are players actual starting ones
        diamondsCollected.setText("0");
        diamondsToCollect.setText("/ 0");

        Timeline dTimeline = new Timeline(
                new KeyFrame(Duration.millis(200),
                        event -> {
                            handleUiUpdate();
                        })
        );
        dTimeline.setCycleCount(Animation.INDEFINITE);
        dTimeline.play();


        if (levelToLoad != null) {
            this.gameController = new GameController(gameCanvas, levelToLoad.getFilePath());
        }
    }

    private void handleUiUpdate() {
        int totalGems = gameController.getMap().getGemsToCollect();
        diamondsToCollect.setText("/ " + totalGems);
        Integer diamondsCollectedByPlayer =
                gameController.getMap().getPlayerObjectReference().getDiamonds();
        //TODO add when var is exposed

        System.out.println("Player D:" + diamondsCollectedByPlayer);
        diamondsCollected.setText(diamondsCollectedByPlayer.toString());

        if (TimeController.getTickCount() % 5 == 0) {
            timeLeft.setText(
                    formatSecondsToString(remainingTimeInSeconds));
            remainingTimeInSeconds--;
        }
    }

    private String formatSecondsToString(int seconds) {
        String output;
        if (seconds > 59) {
            int secRemaining = ((seconds % 86400) % 3600) % 60;
            int minRemaining = ((seconds % 86400) % 3600) / 60;
            if (secRemaining > 10) {
                output = minRemaining + " : " + secRemaining;
            } else {
                output = minRemaining + " : 0" + secRemaining;
            }
        } else {
            output = "0 : " + seconds;
        }
        return output;

    }

    public static void setLevel(Level level) {
        System.out.println("Setting level to " + level.getFilePath());
        levelToLoad = level;
    }

    private void togglePauseMenu(KeyEvent e) {
        if (gameController.isGameIsRunning()) {
            if (e.getCode() == KeyCode.ESCAPE) {
                if (pauseMenuIsVisible) {
                    pauseMenu.setOpacity(0);
                    pauseMenuIsVisible = false;
                    buttonsBox.getChildren().forEach(child ->
                            child.setDisable(true));
                } else {
                    pauseMenu.setOpacity(1);
                    pauseMenuIsVisible = true;
                    buttonsBox.getChildren().forEach(child ->
                            child.setDisable(false));
                }
            }
            gameWindow.requestFocus();
            e.consume();
        } else {
            Player player = gameController.getMap().getPlayerObjectReference();

            if (player.getLivingState()) {
                handleLevelComplete();
            } else {
                handleLoseScreen();
            }
        }
    }

    public void handleLoseScreen() {
        pauseMenu.setOpacity(1);
        pauseMenuIsVisible = true;
        buttonsBox.getChildren().forEach(child ->
                child.setDisable(false));

        saveAndExitButton.setText("Back to Level Select");
        saveAndExitButton.setOnAction(this::handleBackToLevels);
        pauseTitle.setText("You Lose!");

    }

    private void handleLevelComplete() {
        pauseMenu.setOpacity(1);
        pauseMenuIsVisible = true;
        buttonsBox.getChildren().forEach(child ->
                child.setDisable(false));

        saveAndExitButton.setText("Back to Level Select");
        saveAndExitButton.setOnAction(this::handleBackToLevels);
        //TODO Change
//        breathRemainingInSeconds.setText(gameController.getMap().getPlayerObjectReference().getTimer());
        pauseTitle.setText("Congratulations!");
    }

    private void handleBackToLevels(ActionEvent event) {
        try {
            gameWindow = (AnchorPane) FXMLLoader.
                    load(getClass().getResource("select-level.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).
                    getScene().getWindow();
            Scene scene = new Scene(gameWindow, MainUI.MAIN_WINDOW_WIDTH, MainUI.MAIN_WINDOW_HEIGHT);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleReturnToLevels() {

    }

    private void handleSaveAndExitButton(ActionEvent event) {
        //Save GAme in progress
        //Call to handleBackToLevels()
        try {
            gameWindow = (AnchorPane) FXMLLoader.
                    load(getClass().getResource("select-level.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).
                    getScene().getWindow();
            Scene scene = new Scene(gameWindow, MainUI.MAIN_WINDOW_WIDTH, MainUI.MAIN_WINDOW_HEIGHT);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
