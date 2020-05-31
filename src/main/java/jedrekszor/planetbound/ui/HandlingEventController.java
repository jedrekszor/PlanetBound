package jedrekszor.planetbound.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import jedrekszor.planetbound.App;
import jedrekszor.planetbound.logic.Singleton;

import java.io.IOException;

public class HandlingEventController {
    @FXML Button close;
    @FXML Label text;

    @FXML
    public void initialize() {
        text.setText(Singleton.getInstance().getCurrentEvent().toString());
    }

    @FXML
    public void closeStage() {
        try {
            Singleton.getInstance().resolveEvent();
            App.setRoot("actionChoice");
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println(Singleton.getInstance());
//        Stage stage = (Stage) close.getScene().getWindow();
//        stage.close();
    }
}
