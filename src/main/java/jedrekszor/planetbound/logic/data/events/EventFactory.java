package jedrekszor.planetbound.logic.data.events;

public class EventFactory {
    public static Event getRandomEvent() {
        Event event;
        int rand = (int)(Math.random()*6);
        switch (rand) {
            case 0: event = new CrewDeath(); break;
            case 1: event = new CargoLoss(); break;
            case 2: event = new FuelLoss(); break;
            case 3: event = new CrewRescue(); break;
            case 4: event = new NoEvent(); break;
            case 5: event = new SalvageShip(); break;
            default: event = null;
        }
        return event;
    }
}
