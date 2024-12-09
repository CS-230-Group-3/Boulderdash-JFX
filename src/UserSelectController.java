
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller class for the user selection screen in the application.
 * Provides functionality for selecting an existing user profile, creating a new profile,
 * or navigating back to the main screen.
 *
 * @author: Yuliia Shubina, Spas Dikov
 */

public class UserSelectController {

    @FXML
    private AnchorPane selectWindow;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button newProfileButton;

    @FXML
    private Button selectButton;

    @FXML
    private Button backButton;

    @FXML
    private BorderPane selectProfile;

    @FXML
    private ListView<String> userList;

    private ArrayList<User> users = new ArrayList<>();

    /**
     * Initializes the user selection screen by loading user data,
     * setting up button actions, and updating the user list.
     *
     * @throws IOException            if an error occurs while loading user data.
     * @throws ClassNotFoundException if the user data cannot be deserialised.
     */
    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        users = Data.getInstance().getUsers();


        newProfileButton.setOnAction(event -> handleNewProfileButton());
        backButton.setOnAction(event -> handleBackButton(event));
        selectButton.setOnAction(event -> handleSelectButton(event));
        selectButton.setDisable(true);
        userList.getSelectionModel().selectedItemProperty().addListener(
                observable -> selectButton.setDisable(false)
        );

        updateUsers();
    }

    /**
     * Updates the displayed list of users in the ListView.
     */
    private void updateUsers() {
        userList.getItems().clear();

        for (User user : users) {
            userList.getItems().add(user.getName());
        }
    }

    /**
     * Handles the action of creating a new user profile.
     * Opens a new window for profile creation and updates the user list after completion.
     */
    private void handleNewProfileButton() {
        try {
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("new-profile.fxml"));
            AnchorPane newUserRoot = (AnchorPane) loader.load();
            NewProfileController npfController = loader.getController();

            npfController.setListToUpdate(users);

            Scene newUserScene = new Scene(newUserRoot, MainUI.MAIN_WINDOW_WIDTH,
                    MainUI.MAIN_WINDOW_HEIGHT);
            Stage newUserStage = new Stage();

            newUserStage.setScene(newUserScene);
            newUserStage.initModality(Modality.APPLICATION_MODAL);

            newUserStage.showAndWait();

            updateUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action of selecting an existing user profile.
     * Validates that a user is selected, sets the selected user
     * as the current user,
     * and opens the level selection window.
     *
     * @param event the ActionEvent triggered by the button click.
     */
    private void handleSelectButton(ActionEvent event) {
        int index = userList.getSelectionModel().getSelectedIndex();

        if (index < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error: No user selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a user first :) ");
            alert.showAndWait();
        } else {
            User selectedUser = users.get(index);
            Data.getInstance().setCurrentUser(selectedUser);
            openLevelWindow(event);
        }
    }

    /**
     * Opens the level selection window.
     *
     * @param event the ActionEvent triggered by the button click.
     */
    private void openLevelWindow(ActionEvent event) {
        try {
            selectWindow = (AnchorPane) FXMLLoader.
                    load(getClass().getResource("select-level.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).
                    getScene().getWindow();
            Scene scene = new Scene(selectWindow, MainUI.MAIN_WINDOW_WIDTH,
                    MainUI.MAIN_WINDOW_HEIGHT);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action of navigating back to the main menu.
     *
     * @param event the ActionEvent triggered by the button click.
     */
    private void handleBackButton(ActionEvent event) {
        try {
            selectWindow = (AnchorPane) FXMLLoader.
                    load(getClass().getResource("hello-view.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).
                    getScene().getWindow();
            Scene scene = new Scene(selectWindow, MainUI.MAIN_WINDOW_WIDTH,
                    MainUI.MAIN_WINDOW_HEIGHT);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
