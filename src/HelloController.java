
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button startApp;

    @FXML
    void initialize() {
        startApp.setOnAction(event -> openUserWindow());
    }

    private void openUserWindow() {
        try {
            // user.fxml
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("choose-user.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 400);

            //
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("User Window");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
