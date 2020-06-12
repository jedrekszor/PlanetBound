package jedrekszor.planetbound.logic.states;

import jedrekszor.planetbound.logic.Logger;
import jedrekszor.planetbound.logic.Singleton;
import jedrekszor.planetbound.logic.data.planet.Planet;

public class Won extends StateAdapter{
    @Override
    public State win() {
        Logger.log("Winning");
        Singleton.getInstance().setWonGame(true);
        return null;
    }

    @Override
    public String toString() {
        return "won";
    }
}
