package jedrekszor.planetbound.logic.data.exploration.aliens;

import jedrekszor.planetbound.logic.Dice;
import jedrekszor.planetbound.logic.data.exploration.Drone;

public class BlackAlien extends Alien{
    public BlackAlien() {
        setColor("black");
    }

    @Override
    public boolean attack(Drone d) {
        int result = Dice.roll();
        return result == 1;
    }

    @Override
    public boolean getAttacked(Drone d) {
        int result = Dice.roll();
        return result == 5 || result == 6;
    }

    @Override
    public String toString() {
        return "Black " + super.toString();
    }
}
