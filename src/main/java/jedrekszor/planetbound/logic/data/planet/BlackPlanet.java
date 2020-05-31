package jedrekszor.planetbound.logic.data.planet;

import javafx.scene.paint.Color;
import jedrekszor.planetbound.logic.data.resources.BlackResource;
import jedrekszor.planetbound.logic.data.resources.BlueResource;

public class BlackPlanet extends Planet{
    public BlackPlanet(Station station) {
        super(station);
        resources.add(new BlackResource());
        resources.add(new BlueResource());
        setColor("black");
    }

    @Override
    public String toString() {
        return "Black planet";
    }
}
