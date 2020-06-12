package jedrekszor.planetbound.logic.states;

import jedrekszor.planetbound.logic.Logger;
import jedrekszor.planetbound.logic.Singleton;
import jedrekszor.planetbound.logic.data.planet.Planet;

public class Lost extends StateAdapter{
    @Override
    public State lose() {
        Logger.log("Losing");
        Singleton.getInstance().setLostGame(true);
        return null;
    }

    @Override
    public String toString() {
        return "lost";
    }
}
