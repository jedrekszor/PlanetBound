package jedrekszor.planetbound.logic.data.ship;

public class MiningShip extends Ship{
    public MiningShip(int maxShields, int maxWeaponsLevel, int maxFuel, int maxStorageLevel) {
        super(maxShields, maxWeaponsLevel, maxFuel, maxStorageLevel);
    }

    @Override
    public String toString() {
        return "mining ship";
    }
}
