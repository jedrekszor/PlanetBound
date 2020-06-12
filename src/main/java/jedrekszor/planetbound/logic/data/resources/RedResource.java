package jedrekszor.planetbound.logic.data.resources;

import java.io.Serializable;

public class RedResource implements Resource, Serializable {
    @Override
    public String getColor() {
        return "red";
    }
}
