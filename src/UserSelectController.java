
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserSelectController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button newProfileButton;

    @FXML
    private Button selectButton;

    @FXML
    private BorderPane selectProfile;

    @FXML
    private ListView<String> userList;

    private ArrayList<String> users = new ArrayList<>();

    @FXML
    void initialize() {
        users.add("User1");
        users.add("User2");
        users.add("User3");

        newProfileButton.setOnAction(event -> handleNewProfileButton());
        selectButton.setOnAction(event -> handleSelectButton());
        selectButton.setDisable(true);
        userList.getSelectionModel().selectedItemProperty().addListener(
                observable -> selectButton.setDisable(false)
        );

        updateUsers();
    }

    private void updateUsers() {
        userList.getItems().clear();

        for (String user: users) {
            userList.getItems().add(user);
        }
    }

    private void handleNewProfileButton() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("new-profile.fxml"));
            BorderPane newUserRoot = (BorderPane) loader.load();
            NewProfileController npfController = loader.getController();

            npfController.setListToUpdate(users);

            Scene newUserScene = new Scene(newUserRoot);
            Stage newUserStage = new Stage();

            newUserStage.setScene(newUserScene);
            newUserStage.initModality(Modality.APPLICATION_MODAL);

            newUserStage.showAndWait();

            updateUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleSelectButton() {
        int index = userList.getSelectionModel().getSelectedIndex();

        if (index < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error: No user selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a user first :) ");
            alert.showAndWait();
        } else {
           openLevelWindow();
        }
    }
    private void openLevelWindow() {
        try {
            // user.fxml
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("select-level.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 400);

            //
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Level Window");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

