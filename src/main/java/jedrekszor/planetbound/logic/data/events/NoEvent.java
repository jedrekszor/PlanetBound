package jedrekszor.planetbound.logic.data.events;

public class NoEvent extends Event{
    @Override
    public void resolve() {
        System.out.println("Nothing happens");
    }

    @Override
    public String toString() {
        return "Nothing happens";
    }
}
