import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * handles the functionality for the initial screen
 * of the application. It provides the logic to open user selection window
 * when Start App  is clicked. Maybe old.
 */
public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane helloWindow;

    @FXML
    private Button startApp;

    /**
     * Initializes the controller by setting up the event handler for the "Start App" button.
     * The event handler will open the user selection window when the button is clicked.
     */
    @FXML
    void initialize() {
        startApp.setOnAction(event -> openUserWindow(event));
    }

    /**
     * Opens the user selection window by loading the "choose-user.fxml" file.
     * The current stage is updated with the new scene containing the user selection screen.
     *
     * @param event the action event triggered by clicking the "Start App" button
     */
    private void openUserWindow(ActionEvent event) {
        try {
            helloWindow = (AnchorPane) FXMLLoader.
                    load(getClass().getResource("choose-user.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).
                    getScene().getWindow();
            Scene scene = new Scene(helloWindow,
                    MainUI.MAIN_WINDOW_WIDTH, MainUI.MAIN_WINDOW_HEIGHT);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
