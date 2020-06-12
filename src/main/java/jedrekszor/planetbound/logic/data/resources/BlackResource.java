package jedrekszor.planetbound.logic.data.resources;

import java.io.Serializable;

public class BlackResource implements Resource, Serializable {
    @Override
    public String getColor() {
        return "black";
    }
}
