package jedrekszor.planetbound.logic.states;

import jedrekszor.planetbound.logic.Logger;
import jedrekszor.planetbound.logic.Singleton;
import jedrekszor.planetbound.logic.data.planet.Planet;
import jedrekszor.planetbound.logic.data.planet.PlanetFactory;

public class HandlingEvent extends StateAdapter{
    @Override
    public State drawPlanet() {
        Logger.log("Drawing new planet");
        Singleton.getInstance().setCurrentPlanet(PlanetFactory.getPlanet());
        return Singleton.getInstance().getActionChoice();
    }

    @Override
    public State resolveEvent() {
        Logger.log("Resolving event");
        Singleton.getInstance().getCurrentEvent().resolve();
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public String toString() {
        return "handling event";
    }
}
