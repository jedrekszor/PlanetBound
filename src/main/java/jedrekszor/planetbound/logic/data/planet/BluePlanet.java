package jedrekszor.planetbound.logic.data.planet;

import javafx.scene.paint.Color;
import jedrekszor.planetbound.logic.data.resources.*;

public class BluePlanet extends Planet{
    private boolean artefactCollected = false;

    public BluePlanet(Station station) {
        super(station);
        resources.add(new BlackResource());
        resources.add(new GreenResource());
        resources.add(new BlueResource());
        resources.add(new Artefact());
        setColor("blue");
    }

    @Override
    public String toString() {
        return "Blue planet";
    }
}
