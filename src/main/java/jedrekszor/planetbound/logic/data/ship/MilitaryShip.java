package jedrekszor.planetbound.logic.data.ship;

public class MilitaryShip extends Ship{
    public MilitaryShip(int maxShields, int maxWeaponsLevel, int maxFuel, int maxStorageLevel) {
        super(maxShields, maxWeaponsLevel, maxFuel, maxStorageLevel);
    }

    @Override
    public String toString() {
        return "military ship";
    }
}
