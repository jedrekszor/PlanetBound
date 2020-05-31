package jedrekszor.planetbound.logic.data.planet;

import javafx.scene.paint.Color;
import jedrekszor.planetbound.logic.data.resources.GreenResource;
import jedrekszor.planetbound.logic.data.resources.RedResource;

public class GreenPlanet extends Planet{
    public GreenPlanet(Station station) {
        super(station);
        resources.add(new GreenResource());
        resources.add(new RedResource());
        setColor("green");
    }
    @Override
    public String toString() {
        return "Green planet";
    }
}
