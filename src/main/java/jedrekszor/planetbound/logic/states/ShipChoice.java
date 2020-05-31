package jedrekszor.planetbound.logic.states;

import jedrekszor.planetbound.logic.data.ship.MilitaryShip;
import jedrekszor.planetbound.logic.data.ship.MiningShip;
import jedrekszor.planetbound.logic.Singleton;
import jedrekszor.planetbound.logic.data.planet.Planet;

public class ShipChoice extends State{
    @Override
    public State chooseShip(boolean isMilitary) {
        System.out.println("Choosing ship");
        if(isMilitary)
            Singleton.getInstance().setShip(new MilitaryShip(9, 2, 35, 2));
        else
            Singleton.getInstance().setShip(new MiningShip(18, 1, 53, 4));

        return Singleton.getInstance().getActionChoice();
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
        System.out.println("Cannot buy fuel here");
    }

    @Override
    public void buyWeapon() {
        System.out.println("Cannot buy weapons here");
    }

    @Override
    public void buyShield() {
        System.out.println("Cannot buy shield here");
    }

    @Override
    public State endShopping() {
        System.out.println("Not in the shop");
        return Singleton.getInstance().getCurrentState();
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
        return "ship choice";
    }
}
