package jedrekszor.planetbound.logic.data.events;

import jedrekszor.planetbound.logic.Logger;
import jedrekszor.planetbound.logic.Singleton;

public class FuelLoss extends Event{
//    6 – Fuel Loss = “You accidentally use too much fuel in a test run.
//    Remove [1] fuel cell”
    @Override
    public void resolve() {
        Logger.log("Fuel Loss. You accidentally use too much fuel in a test run.");
        Singleton.getInstance().getShip().removeFuel(1);
        Singleton.getInstance().checkLose();
    }

    @Override
    public String toString() {
        return "Fuel Loss. You accidentally use too much fuel in a test run.";
    }
}
