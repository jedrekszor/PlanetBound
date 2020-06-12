package jedrekszor.planetbound.logic.states;

import jedrekszor.planetbound.logic.Logger;
import jedrekszor.planetbound.logic.Singleton;
import jedrekszor.planetbound.logic.data.exploration.Drone;
import jedrekszor.planetbound.logic.data.planet.Planet;
import jedrekszor.planetbound.logic.data.resources.ResourceFactory;

public class BuyingUpgrades extends StateAdapter{
    @Override
    public State serviceDrone() {
        Logger.log("Servicing drone");
        ResourceFactory.removeAllResources(1);
        Singleton.getInstance().getShip().getDrone().service();
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public State upgradeCargo() {
        Logger.log("Upgrading cargo");
        ResourceFactory.removeAllResources(2);
        Singleton.getInstance().getShip().addStorageLevel();
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public State upgradeWeapons() {
        Logger.log("Upgrading weapons");
        ResourceFactory.removeAllResources(2);
        Singleton.getInstance().getShip().addWeaponsLevel();
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public State buyDrone() {
        Logger.log("Buying drone");
        ResourceFactory.removeAllResources(2);
        Singleton.getInstance().getShip().setDrone(new Drone());
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public State hireCrew() {
        Logger.log("Hiring crew");
        ResourceFactory.removeAllResources(1);
        Singleton.getInstance().getShip().addCrew(1);
        return Singleton.getInstance().getCurrentState();
    }

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
        return "buying upgrades";
    }
}
