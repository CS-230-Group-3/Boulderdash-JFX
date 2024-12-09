
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

import java.io.IOException;


public class MainUI extends Application {
    public static final int MAIN_WINDOW_WIDTH = 1400;
    public static final int MAIN_WINDOW_HEIGHT = 800;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader =
				new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),
				MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
        stage.setTitle("JungleGameStart");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
