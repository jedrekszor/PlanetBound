package jedrekszor.planetbound.ui;

import javafx.fxml.FXML;
import jedrekszor.planetbound.App;

import java.io.IOException;

public class ExplorationScreenController {
    @FXML
    public void closeAll() {
        try {
            App.setRoot("actionChoice");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
