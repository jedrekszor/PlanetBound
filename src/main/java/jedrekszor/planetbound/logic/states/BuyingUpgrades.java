package jedrekszor.planetbound.logic.states;

import jedrekszor.planetbound.logic.Singleton;
import jedrekszor.planetbound.logic.data.exploration.Drone;
import jedrekszor.planetbound.logic.data.planet.Planet;
import jedrekszor.planetbound.logic.data.resources.ResourceFactory;

public class BuyingUpgrades extends State{
    @Override
    public State chooseShip(boolean isMilitary) {
        System.out.println("Cannot choose ship, You already have a ship");
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public State drawPlanet() {
        System.out.println("Cannot draw a planet");
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public State advance() {
        System.out.println("Cannot advance");
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public State explore() {
        System.out.println("Cannot explore");
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public void resolveEvent() {
        System.out.println("No event to resolve");
    }



    @Override
    public void serviceDrone() {
        System.out.println("Servicing drone");
        ResourceFactory.removeAllResources(1);
        Singleton.getInstance().getShip().getDrone().service();
    }

    @Override
    public void upgradeCargo() {
        System.out.println("Upgrading cargo");
        ResourceFactory.removeAllResources(2);
        Singleton.getInstance().getShip().addStorageLevel();
    }

    @Override
    public void upgradeWeapons() {
        System.out.println("Upgrading weapons");
        ResourceFactory.removeAllResources(2);
        Singleton.getInstance().getShip().addWeaponsLevel();
    }

    @Override
    public void buyDrone() {
        System.out.println("Buying drone");
        ResourceFactory.removeAllResources(2);
        Singleton.getInstance().getShip().setDrone(new Drone());
    }

    @Override
    public void hireCrew() {
        System.out.println("Hiring crew");
        ResourceFactory.removeAllResources(1);
        Singleton.getInstance().getShip().addCrew(1);
    }

    @Override
    public void buyFuel() {
        System.out.println("Buying fuel");
        ResourceFactory.removeParticularResources(true, false, true, true, 1);
        Singleton.getInstance().getShip().addFuel(1);
    }

    @Override
    public void buyWeapon() {
        System.out.println("Buying weapons");
        ResourceFactory.removeParticularResources(true, true, false, false, 1);
        Singleton.getInstance().getShip().addWeapons();
    }

    @Override
    public void buyShield() {
        System.out.println("Buying shield");
        ResourceFactory.removeParticularResources(true, true, true, false, 1);
        Singleton.getInstance().getShip().addShields();
    }

    @Override
    public State endShopping() {
        System.out.println("Ending shopping");
        return Singleton.getInstance().getActionChoice();
    }

    @Override
    public State lose() {
        System.out.println("Have not lost yet");
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public State win() {
        System.out.println("Have not won yet");
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public String toString() {
        return "buying upgrades";
    }
}
