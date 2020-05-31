package jedrekszor.planetbound.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import jedrekszor.planetbound.App;
import jedrekszor.planetbound.logic.Singleton;
import java.io.IOException;


public class WinLoseScreenController {
    @FXML Button exitB;

    public void playAgain() {
        Singleton.getInstance().playAgain();
        try {
            App.setRoot("shipChoice");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void exit() {
        Singleton.getInstance().quit();
        Stage stage = (Stage) exitB.getScene().getWindow();
        stage.close();
    }
}
