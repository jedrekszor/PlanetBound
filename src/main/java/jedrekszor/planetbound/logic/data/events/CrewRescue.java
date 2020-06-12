package jedrekszor.planetbound.logic.data.events;

import jedrekszor.planetbound.logic.Logger;
import jedrekszor.planetbound.logic.Singleton;

public class CrewRescue extends Event{
//    9 – Crew Rescue = “You find a ship in distress with a lone crew member.
//    This crew member joins your crew and you move your white crew die to the left
//    one space if you have less than six crew”
    @Override
    public void resolve() {
        Logger.log("Crew Rescue. You find a ship in distress with a lone crew member.");
        Singleton.getInstance().getShip().addCrew(1);
    }

    @Override
    public String toString() {
        return "Crew Rescue. You find a ship in distress with a lone crew member.";
    }
}
