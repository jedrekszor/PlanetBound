package jedrekszor.planetbound.logic.states;

import jedrekszor.planetbound.logic.Singleton;
import jedrekszor.planetbound.logic.data.exploration.Surface;
import jedrekszor.planetbound.logic.data.planet.Planet;
import jedrekszor.planetbound.logic.data.resources.Resource;

public abstract class State {
    public abstract State chooseShip(boolean isMilitary);
    public abstract State drawPlanet();
    public abstract State advance();
    public abstract State explore();
    public abstract void resolveEvent();
    public State checkWin() {   //remove
        if(Singleton.getInstance().getShip().getArtefacts() == 5) {
            return Singleton.getInstance().getWon();
        }
        return Singleton.getInstance().getCurrentState();
    }
    public State checkLose() {  //remove
        if(Singleton.getInstance().getShip().getCrew() == 0 || Singleton.getInstance().getShip().getFuel() == 0) {
            return Singleton.getInstance().getLost();
        }
        return Singleton.getInstance().getCurrentState();
    }
//    public abstract void convertResource(Resource source, Resource destination);
    public abstract void serviceDrone();
    public abstract void upgradeCargo();
    public abstract void upgradeWeapons();
    public abstract void buyDrone();
    public abstract void hireCrew();
    public abstract void buyFuel();
    public abstract void buyWeapon();
    public abstract void buyShield();
    public abstract State endShopping();

    public abstract State lose();   //remove
    public abstract State win();    //remove

    @Override
    public abstract String toString();

    //state adapter
    //no printlines
}
