package jedrekszor.planetbound.logic.states;

import jedrekszor.planetbound.logic.Singleton;
import jedrekszor.planetbound.logic.data.planet.Planet;
import jedrekszor.planetbound.logic.data.resources.ResourceFactory;

public class ConvertingResources extends State{
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
        System.out.println("Cannot service drone here");
    }

    @Override
    public void upgradeCargo() {
        System.out.println("Cannot upgrade cargo here");
    }

    @Override
    public void upgradeWeapons() {
        System.out.println("Cannot upgrade weapons here");
    }

    @Override
    public void buyDrone() {
        System.out.println("Cannot buy drone here");
    }

    @Override
    public void hireCrew() {
        System.out.println("Cannot hire crew here");
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
        return "converting resources";
    }
}
