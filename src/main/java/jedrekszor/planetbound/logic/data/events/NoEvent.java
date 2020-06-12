package jedrekszor.planetbound.logic.data.events;

import jedrekszor.planetbound.logic.Logger;

public class NoEvent extends Event{
    @Override
    public void resolve() {
        Logger.log("Nothing happens");
    }

    @Override
    public String toString() {
        return "Nothing happens";
    }
}
