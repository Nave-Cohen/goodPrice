package goodPrice;

import handler.jsonHandler;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static HostServices hostServices;


    public void start(Stage stage) throws IOException {
        hostServices = getHostServices();
        FXMLLoader load = new FXMLLoader(getClass().getResource("/MAIN.fxml"));
        Parent root = load.load();

        Scene scene = new Scene(root);
        String css = this.getClass().getResource("/application.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setOnCloseRequest(e -> {
            jsonHandler.writeItems();
            System.exit(0);
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
