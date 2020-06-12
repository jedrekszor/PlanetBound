package jedrekszor.planetbound.logic.states;

import jedrekszor.planetbound.logic.Logger;
import jedrekszor.planetbound.logic.Singleton;

public abstract class StateAdapter extends State{
    @Override
    public State chooseShip(boolean isMilitary) {
        Logger.log("Cannot choose ship, You already have a ship");
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public State drawPlanet() {
        Logger.log("Cannot draw a planet");
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public State advance() {
        Logger.log("Cannot advance");
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public State explore() {
        Logger.log("Cannot explore");
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public State resolveEvent() {
        Logger.log("No event to resolve");
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public State serviceDrone() {
        Logger.log("Cannot service drone here");
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public State upgradeCargo() {
        Logger.log("Cannot upgrade cargo here");
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public State upgradeWeapons() {
        Logger.log("Cannot upgrade weapons here");
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public State buyDrone() {
        Logger.log("Cannot buy drone here");
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public State hireCrew() {
        Logger.log("Cannot hire crew here");
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public State buyFuel() {
        Logger.log("Cannot buy fuel here");
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public State buyWeapon() {
        Logger.log("Cannot buy weapons here");
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public State buyShield() {
        Logger.log("Cannot buy shield here");
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public State shop() {
        Logger.log("Cannot shop here");
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public State endShopping() {
        Logger.log("Not in the shop");
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public State lose() {
        Logger.log("Have not lost yet");
        return Singleton.getInstance().getCurrentState();
    }

    @Override
    public State win() {
        Logger.log("Have not won yet");
        return Singleton.getInstance().getCurrentState();
    }
}
