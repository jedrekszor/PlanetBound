package jedrekszor.planetbound.logic.data.exploration.aliens;

import jedrekszor.planetbound.logic.Dice;
import jedrekszor.planetbound.logic.data.exploration.Drone;

public class GreenAlien extends Alien{
    public GreenAlien() {
        setColor("green");
    }

    @Override
    public boolean attack(Drone d) {
        int result = Dice.roll();
        return result == 1 || result == 2;
    }

    @Override
    public boolean getAttacked(Drone d) {
        int result = Dice.roll();
        return result >= 4 && result <= 6;
    }

    @Override
    public String toString() {
        return "Green " + super.toString();
    }
}
