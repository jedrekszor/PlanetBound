package jedrekszor.planetbound.logic.data.exploration.aliens;

import jedrekszor.planetbound.logic.Dice;
import jedrekszor.planetbound.logic.data.exploration.Drone;

public class BlueAlien extends Alien{
    public BlueAlien() {
        setColor("blue");
    }

    @Override
    public boolean attack(Drone d) {
        int result = Dice.roll();
        return result >= 3 && result <= 5;
    }

    @Override
    public boolean getAttacked(Drone d) {
        int result = Dice.roll();
        return result >= 3 && result <= 5;
    }

    @Override
    public String toString() {
        return "Blue " + super.toString();
    }
}
