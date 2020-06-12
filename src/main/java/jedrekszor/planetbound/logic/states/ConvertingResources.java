package jedrekszor.planetbound.logic.states;

import jedrekszor.planetbound.logic.Logger;
import jedrekszor.planetbound.logic.Singleton;
import jedrekszor.planetbound.logic.data.planet.Planet;
import jedrekszor.planetbound.logic.data.resources.ResourceFactory;

public class ConvertingResources extends StateAdapter{
    @Override
    public State buyFuel() {
        Logger.log("Buying fuel");
        ResourceFactory.removeParticularResources(true, false, true, true, 1);
        Singleton.getInstance().getShip().addFuel(1);
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public State buyWeapon() {
        Logger.log("Buying weapons");
        ResourceFactory.removeParticularResources(true, true, false, false, 1);
        Singleton.getInstance().getShip().addWeapons();
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public State buyShield() {
        Logger.log("Buying shield");
        ResourceFactory.removeParticularResources(true, true, true, false, 1);
        Singleton.getInstance().getShip().addShields();
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public State endShopping() {
        Logger.log("Ending shopping");
        return Singleton.getInstance().getActionChoice();
    }

    @Override
    public String toString() {
        return "converting resources";
    }
}
