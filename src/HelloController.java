
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

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane helloWindow;

    @FXML
    private Button startApp;

    private static int WIDTH = 700;
    private static int HEIGHT = 400;

    @FXML
    void initialize() {
        startApp.setOnAction(event -> openUserWindow(event));
    }

    private void openUserWindow(ActionEvent event){
        try {
            helloWindow = (AnchorPane) FXMLLoader.
                    load(getClass().getResource("choose-user.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).
                    getScene().getWindow();
            Scene scene = new Scene(helloWindow);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
