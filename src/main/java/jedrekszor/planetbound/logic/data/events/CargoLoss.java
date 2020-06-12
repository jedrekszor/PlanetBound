package jedrekszor.planetbound.logic.data.events;

import jedrekszor.planetbound.logic.Dice;
import jedrekszor.planetbound.logic.Logger;
import jedrekszor.planetbound.logic.Singleton;
import jedrekszor.planetbound.logic.data.resources.Resource;
import jedrekszor.planetbound.logic.data.resources.ResourceFactory;

public class CargoLoss extends Event{
//    5 – Cargo Loss = “A cargo mishap causes you to lose some of your resources.
//    Place the colored cubes of the resources you have in the cargo hold and draw one.
//    Roll a d3 [1-3] to see how much of that resource you lose”
    @Override
    public void resolve() {
        Logger.log("Cargo Loss. A cargo mishap causes you to lose some of your resources.");
        int rand = Dice.roll(1, 3);
        ResourceFactory.removeRandomResource(Singleton.getInstance().getShip().getBlack() > 0,
                Singleton.getInstance().getShip().getBlue() > 0,
                Singleton.getInstance().getShip().getGreen() > 0,
                Singleton.getInstance().getShip().getRed() > 0,
                rand);
    }

    @Override
    public String toString() {
        return "Cargo Loss. A cargo mishap causes you to lose some of your resources.";
    }
}
