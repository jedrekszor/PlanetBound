package jedrekszor.planetbound.logic.states;

import jedrekszor.planetbound.logic.Logger;
import jedrekszor.planetbound.logic.Singleton;
import jedrekszor.planetbound.logic.data.exploration.Surface;
import jedrekszor.planetbound.logic.data.planet.Planet;
import jedrekszor.planetbound.logic.data.planet.PlanetFactory;

public class ActionChoice extends StateAdapter{
    @Override
    public State drawPlanet() {
        Logger.log("Drawing new planet");
        Singleton.getInstance().setCurrentPlanet(PlanetFactory.getPlanet());
        return Singleton.getInstance().getActionChoice();
    }

    @Override
    public State advance() {
        Logger.log("Drawing event");
        return Singleton.getInstance().getHandlingEvent();
    }

    @Override
    public State explore() {
        Logger.log("Landing on a planet");
        Singleton.getInstance().setCurrentSurface(new Surface());
        return Singleton.getInstance().getExploration();
    }

    @Override
    public State shop() {
        Logger.log("Entering shop");
        Singleton singleton = Singleton.getInstance();
        if(singleton.getCurrentPlanet().hasStation())
            return singleton.getBuyingUpgrades();
        else
            return singleton.getConvertingResources();
    }

    @Override
    public String toString() {
        return "action choice";
    }
}
