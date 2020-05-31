package jedrekszor.planetbound.logic.data.events;

public abstract class Event {
    public abstract void resolve();

    @Override
    public abstract String toString();
}
