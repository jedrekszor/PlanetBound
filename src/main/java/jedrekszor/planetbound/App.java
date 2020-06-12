package jedrekszor.planetbound;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jedrekszor.planetbound.logic.Logger;


import java.io.IOException;
import java.time.Instant;
import java.util.Date;

public class App extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Logger.log("------------------------------------------------------------------------------------\nNew game: " + Date.from(Instant.now()));
        scene = new Scene(loadFXML("shipChoice"));
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}