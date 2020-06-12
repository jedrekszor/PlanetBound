package jedrekszor.planetbound.logic.states;

import jedrekszor.planetbound.logic.Logger;
import jedrekszor.planetbound.logic.Singleton;
import jedrekszor.planetbound.logic.data.exploration.Surface;
import jedrekszor.planetbound.logic.data.planet.Planet;

public class Exploration extends StateAdapter{
    @Override
    public State explore() {
        Logger.log("Landing on the planet");
        Singleton.getInstance().setCurrentSurface(new Surface());
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public String toString() {
        return "exploration";
    }
}
