package jedrekszor.planetbound.logic.data.exploration;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Drone extends SurfaceWalker{
    private final int maxHp = 6;
    public transient IntegerProperty hp = new SimpleIntegerProperty();
    public transient BooleanProperty returning = new SimpleBooleanProperty();

    public int getHp() {
        return hp.get();
    }
    public IntegerProperty hpProperty() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp.setValue(hp);
    }


    public boolean isReturning() {
        return returning.get();
    }
    public BooleanProperty returningProperty() {
        return returning;
    }
    public void setReturning(boolean returning) {
        this.returning.set(returning);
    }

    public Drone() {
        setHp(maxHp);
        setReturning(false);
    }

    public int getMaxHp() {
        return maxHp;
    }
    public void service() {
        setHp(maxHp);
    }
    public boolean getHit(int value) {
        if(getHp() - value >= 0) {
            setHp(getHp() - value);
            return true;
        } else {
            setHp(0);
            return false;
        }
    }

    @Override
    public String toString() {
        return "Drone, " + super.toString() + ", Hp: " + hp;
    }
}
