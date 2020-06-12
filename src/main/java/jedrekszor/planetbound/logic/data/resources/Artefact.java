package jedrekszor.planetbound.logic.data.resources;

import java.io.Serializable;

public class Artefact implements Resource, Serializable {
    @Override
    public String getColor() {
        return "pink";
    }
}
