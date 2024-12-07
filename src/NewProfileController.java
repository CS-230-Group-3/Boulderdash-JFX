import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class NewProfileController {
    @FXML TextField nameField;
    @FXML Button cancelButton;
    @FXML Button createButton;
    @FXML Pane rootPane;

    private ArrayList<User> users = new ArrayList<>();

    public void initialize() {

        createButton.setOnAction(event -> {
            try {
                handleCreateButton();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        cancelButton.setOnAction(event -> handleCancelButton());
    }

    public void setListToUpdate(ArrayList<User> users) {
        this.users = users;
    }

    private void handleCreateButton() throws IOException, ClassNotFoundException {
        String name = nameField.getText();
        User newUser = new User(name);

        if (!users.contains(newUser)) {
            users.add(newUser);

            Data data = Data.getInstance();
            data.addNewUser(newUser.getName());

            close();
        } else {
            displayUserExistsAlert();
        }
    }

    private void handleCancelButton() {
        close();
    }

    private void close() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    private void displayUserExistsAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error: User already exists");
        alert.setHeaderText(null);
        alert.setContentText("Please select a unique user name");
        alert.showAndWait();
    }
}
