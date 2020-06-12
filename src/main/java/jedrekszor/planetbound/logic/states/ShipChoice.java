package jedrekszor.planetbound.logic.states;

import jedrekszor.planetbound.logic.Logger;
import jedrekszor.planetbound.logic.data.ship.MilitaryShip;
import jedrekszor.planetbound.logic.data.ship.MiningShip;
import jedrekszor.planetbound.logic.Singleton;
import jedrekszor.planetbound.logic.data.planet.Planet;

public class ShipChoice extends StateAdapter{
    @Override
    public State chooseShip(boolean isMilitary) {
        Logger.log("Choosing ship");
        if(isMilitary)
            Singleton.getInstance().setShip(new MilitaryShip(9, 2, 35, 2));
        else
            Singleton.getInstance().setShip(new MiningShip(18, 1, 53, 4));

        return Singleton.getInstance().getActionChoice();
    }

    @Override
    public String toString() {
        return "ship choice";
    }
}
