package jedrekszor.planetbound.logic.data.exploration.aliens;

import jedrekszor.planetbound.logic.Dice;
import jedrekszor.planetbound.logic.data.exploration.Drone;

public class RedAlien extends Alien{
    public RedAlien() {
        setColor("red");
    }

    @Override
    public boolean attack(Drone d) {
        int result = Dice.roll();
        return result >= 5 && result <= 6;
    }

    @Override
    public boolean getAttacked(Drone d) {
        int result = Dice.roll();
        return result >= 1 && result <= 2;
    }

    @Override
    public String toString() {
        return "Red " + super.toString();
    }
}
