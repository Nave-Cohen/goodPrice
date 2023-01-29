package goodPrice;

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
        mainController controller = load.getController();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setOnCloseRequest(e->{controller.writeAll();});
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
