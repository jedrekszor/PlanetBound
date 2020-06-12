package jedrekszor.planetbound.logic.data.resources;

import java.io.Serializable;

public class BlueResource implements Resource, Serializable {
    @Override
    public String getColor() {
        return "blue";
    }
}
