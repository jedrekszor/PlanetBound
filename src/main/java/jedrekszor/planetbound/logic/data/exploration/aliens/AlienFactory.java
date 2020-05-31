package jedrekszor.planetbound.logic.data.exploration.aliens;

import jedrekszor.planetbound.ui.ExplorationController;

import java.util.ArrayList;
import java.util.List;

public class AlienFactory {
    public static Alien getRandomAlien(boolean black, boolean blue, boolean green, boolean red) {
        List<Alien> aliens = new ArrayList<>();
        if(black)
            aliens.add(new BlackAlien());
        if(blue)
            aliens.add(new BlueAlien());
        if(green)
            aliens.add(new GreenAlien());
        if(red)
            aliens.add(new RedAlien());

        int rand = (int)(Math.random() * aliens.size());
        return aliens.get(rand);
    }
}
