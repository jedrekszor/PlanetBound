package jedrekszor.planetbound.logic.data.resources;

import java.io.Serializable;

public class GreenResource implements Resource, Serializable {
    @Override
    public String getColor() {
        return "green";
    }
}
