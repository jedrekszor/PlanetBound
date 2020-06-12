package jedrekszor.planetbound.logic.data.events;

import jedrekszor.planetbound.logic.Dice;
import jedrekszor.planetbound.logic.Logger;
import jedrekszor.planetbound.logic.data.resources.ResourceFactory;

public class SalvageShip extends Event{
//    4 – Salvage Ship = “Your ship comes across an abandoned ship and you
//    find a random resource. Place all four of the resource cubes in a bag and draw one.
//    Roll the d6 for that resource and add it to your cargo”
    @Override
    public void resolve() {
        Logger.log("Salvage Ship. Your ship comes across an abandoned ship and you find a random resource.");
        int draw = Dice.roll();
        ResourceFactory.addRandomResource(true, true, true, true, false,  draw);
    }

    @Override
    public String toString() {
        return "Salvage Ship. Your ship comes across an abandoned ship and you find a random resource.";
    }
}
