package jedrekszor.planetbound.logic.data.events;

import java.io.Serializable;

public abstract class Event implements Serializable {
    public abstract void resolve();

    @Override
    public abstract String toString();
}
