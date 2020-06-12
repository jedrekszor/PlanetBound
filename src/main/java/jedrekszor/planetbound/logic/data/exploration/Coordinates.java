package jedrekszor.planetbound.logic.data.exploration;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.Serializable;

public class Coordinates implements Serializable {
    public transient IntegerProperty x = new SimpleIntegerProperty();
    public transient IntegerProperty y = new SimpleIntegerProperty();

    public IntegerProperty xProperty() {
        return x;
    }
    public IntegerProperty yProperty() {
        return y;
    }
    public int getX() {
        return x.get();
    }
    public int getY() {
        return y.get();
    }
    public void setCoordinates(int x, int y) {
        if(x >= 0 && x < 6 && y >= 0 && y < 6) {
            this.x.setValue(x);
            this.y.setValue(y);
        }
    }
    public void setCoordinates(Coordinates c) {
        if(c.getX() >= 0 && c.getX() < 6 && c.getY() >= 0 && c.getY() < 6) {
            this.x.setValue(c.getX());
            this.y.setValue(c.getY());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Coordinates))
            return false;

        Coordinates temp = (Coordinates)obj;
        if(getX() == temp.getX() && getY() == temp.getY())
            return true;
        return false;
    }
}
