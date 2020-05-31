package jedrekszor.planetbound.logic.data.planet;

import javafx.scene.paint.Color;
import jedrekszor.planetbound.logic.data.resources.BlueResource;
import jedrekszor.planetbound.logic.data.resources.RedResource;

public class RedPlanet extends Planet{
    public RedPlanet(Station station) {
        super(station);
        resources.add(new RedResource());
        resources.add(new BlueResource());
        setColor("red");
    }
    @Override
    public String toString() {
        return "Red planet";
    }
}
