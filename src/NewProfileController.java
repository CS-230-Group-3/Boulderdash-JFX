import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class NewProfileController {
    @FXML
    TextField nameField;
    @FXML
    Button cancelButton;
    @FXML
    Button createButton;
    @FXML
    Pane anchorPane;

    private ArrayList<User> users = new ArrayList<>();

    /**
     * initializes what button should do when clicked
     */
    public void initialize() {

        createButton.setOnAction(event -> handleCreateButton());
        cancelButton.setOnAction(event -> handleCancelButton());
    }

    /**
     * @param users ArrayList of users
     */
    public void setListToUpdate(ArrayList<User> users) {
        this.users = users;
    }

    /**
     * Adds new users if they don't already exist
     */
    private void handleCreateButton() {
        String name = nameField.getText();
        User newUser = new User(name);

        if (!users.contains(newUser)) {
            Data.getInstance().addNewUser(newUser);

            close();
        } else {
            displayUserExistsAlert();
        }
    }

    /**
     * closes window
     */
    private void handleCancelButton() {
        close();
    }

    /**
     * Closes window
     */
    private void close() {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }

    /**
     * Creates alert for a user being preexisting
     */
    private void displayUserExistsAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error: User already exists");
        alert.setHeaderText(null);
        alert.setContentText("Please select a unique user name");
        alert.showAndWait();
    }
}
