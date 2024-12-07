
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class MainUI extends Application {
	private static final int MAIN_WINDOW_WIDTH = 700;
	private static final int MAIN_WINDOW_HEIGHT = 400;

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load());
		stage.setTitle("JungleGameStart");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}
