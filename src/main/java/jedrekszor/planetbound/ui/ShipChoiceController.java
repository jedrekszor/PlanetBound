package jedrekszor.planetbound.ui;

import javafx.fxml.FXML;
import jedrekszor.planetbound.App;
import jedrekszor.planetbound.logic.Singleton;
import java.io.IOException;

public class ShipChoiceController {
    @FXML
    public void chooseMining() throws IOException {
        Singleton.getInstance().chooseMining();
        App.setRoot("actionChoice");
    }

    @FXML
    public void chooseMilitary() throws IOException {
        Singleton.getInstance().chooseMilitary();
        App.setRoot("actionChoice");
    }
}
