package goodPrice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application{

        public void start(Stage stage) throws IOException {
            FXMLLoader load = new FXMLLoader(getClass().getResource("/MAIN.fxml"));
            Parent root = load.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
}
