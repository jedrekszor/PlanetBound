package jedrekszor.planetbound.logic.states;

import jedrekszor.planetbound.logic.Singleton;
import jedrekszor.planetbound.logic.data.exploration.Surface;
import jedrekszor.planetbound.logic.data.planet.Planet;
import jedrekszor.planetbound.logic.data.resources.Resource;

import java.io.Serializable;

public abstract class State implements Serializable {
    public abstract State chooseShip(boolean isMilitary);
    public abstract State drawPlanet();
    public abstract State advance();
    public abstract State explore();
    public abstract State resolveEvent();
    public State checkWin() {   //remove
        if(Singleton.getInstance().getShip().getArtefact() == 5) {
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
    public abstract State serviceDrone();
    public abstract State upgradeCargo();
    public abstract State upgradeWeapons();
    public abstract State buyDrone();
    public abstract State hireCrew();
    public abstract State buyFuel();
    public abstract State buyWeapon();
    public abstract State buyShield();
    public abstract State shop();
    public abstract State endShopping();

    public abstract State lose();   //remove
    public abstract State win();    //remove

    @Override
    public abstract String toString();

    //state adapter
    //no printlines
}
