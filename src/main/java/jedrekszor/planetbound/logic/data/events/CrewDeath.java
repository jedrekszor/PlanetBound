package jedrekszor.planetbound.logic.data.events;

import jedrekszor.planetbound.logic.Logger;
import jedrekszor.planetbound.logic.Singleton;

public class CrewDeath extends Event{
//    3 – Crew Death = “A crew member is injured due to a system malfunction,
//    move the ship crew die to the right one space”
    @Override
    public void resolve() {
        Logger.log("Crew Death! A crew member is injured due to a system malfunction");
        Singleton.getInstance().getShip().removeCrew(1);
        Singleton.getInstance().checkLose();
    }

    @Override
    public String toString() {
        return "Crew Death! A crew member is injured due to a system malfunction";
    }
}
