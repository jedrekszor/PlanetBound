package jedrekszor.planetbound.logic.data.exploration;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Drone extends SurfaceWalker{
    private final int maxHp = 6;
    private IntegerProperty hp = new SimpleIntegerProperty();
    public int getHp() {
        return hp.get();
    }
    public IntegerProperty hpProperty() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp.setValue(hp);
    }
    public boolean returning = false;

    public Drone() {
        setHp(maxHp);
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
