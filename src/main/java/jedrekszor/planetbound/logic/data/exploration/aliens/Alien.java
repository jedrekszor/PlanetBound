package jedrekszor.planetbound.logic.data.exploration.aliens;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import jedrekszor.planetbound.logic.Singleton;
import jedrekszor.planetbound.logic.data.exploration.Drone;
import jedrekszor.planetbound.logic.data.exploration.Surface;
import jedrekszor.planetbound.logic.data.exploration.SurfaceWalker;

public abstract class Alien extends SurfaceWalker {
    private String color;
    public Alien() {
        if(Singleton.getInstance().getShip().getDrone() != null) {
            int randX;
            int randY;
            do {
                randX = (int) (Math.random() * Surface.getXSize());
                randY = (int) (Math.random() * Surface.getYSize());
            } while (randX == Singleton.getInstance().getShip().getDrone().getCoordinates().getX()
                    || randY == Singleton.getInstance().getShip().getDrone().getCoordinates().getY());
            this.getCoordinates().setCoordinates(randX, randY);
            setDed(false);
        }
    }

    public abstract boolean attack(Drone d);
    public abstract boolean getAttacked(Drone d);

    private BooleanProperty ded = new SimpleBooleanProperty();
    public boolean isDed() {
        return ded.get();
    }
    public BooleanProperty dedProperty() {
        return ded;
    }
    public void setDed(boolean ded) {
        this.ded.set(ded);
    }

    public void setColor(String color) {
        this.color = color;
    }
    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "alien, " + super.toString();
    }
}
