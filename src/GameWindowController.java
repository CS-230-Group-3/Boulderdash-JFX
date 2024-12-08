import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class GameWindowController {

    @FXML private AnchorPane gameWindow;
    @FXML private Canvas gameCanvas;
    @FXML private AnchorPane pauseMenu;
    @FXML private VBox buttonsBox;
    @FXML private Button saveAndExitButton;

    private boolean pauseMenuIsVisible = false;

    private GameController gameController;
    private static Level levelToLoad;

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
        saveAndExitButton.setOnAction(this::handleExitButtonAction);

        gameWindow.setFocusTraversable(true);
        gameWindow.requestFocus();

        if (levelToLoad != null) {
            this.gameController = new GameController(gameCanvas, levelToLoad.getFilePath());
        }
    }

    public static void setLevel(Level level) {
        System.out.println("Setting level to " + level.getFilePath());
        levelToLoad = level;
    }

    private void togglePauseMenu(KeyEvent e) {
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
    }

    private void handleExitButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
