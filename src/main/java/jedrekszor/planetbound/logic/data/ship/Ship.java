package jedrekszor.planetbound.logic.data.ship;

import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import jedrekszor.planetbound.logic.Singleton;
import jedrekszor.planetbound.logic.data.exploration.Drone;
import jedrekszor.planetbound.logic.data.resources.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Ship implements Serializable {

    public transient BooleanProperty captain = new SimpleBooleanProperty();
    public transient BooleanProperty navigationOfficer = new SimpleBooleanProperty();
    public transient BooleanProperty landingParty = new SimpleBooleanProperty();
    public transient BooleanProperty shieldsOfficer = new SimpleBooleanProperty();
    public transient BooleanProperty weaponsOfficer = new SimpleBooleanProperty();
    public transient BooleanProperty cargoOfficer = new SimpleBooleanProperty();

    public transient IntegerProperty black = new SimpleIntegerProperty();
    public transient IntegerProperty blue = new SimpleIntegerProperty();
    public transient IntegerProperty green = new SimpleIntegerProperty();
    public transient IntegerProperty red = new SimpleIntegerProperty();
    public transient IntegerProperty artefact = new SimpleIntegerProperty();

    public transient IntegerProperty shields = new SimpleIntegerProperty();
    public transient IntegerProperty weapons = new SimpleIntegerProperty();
    public transient IntegerProperty fuel = new SimpleIntegerProperty();


    public boolean hasCaptain() {
        return captain.get();
    }
    public BooleanProperty captainProperty() {
        return captain;
    }
    public void setCaptain(boolean captain) {
        this.captain.set(captain);
    }
    public boolean hasNavigationOfficer() {
        return navigationOfficer.get();
    }
    public BooleanProperty navigationOfficerProperty() {
        return navigationOfficer;
    }
    public void setNavigationOfficer(boolean navigationOfficer) {
        this.navigationOfficer.set(navigationOfficer);
    }
    public boolean hasLandingParty() {
        return landingParty.get();
    }
    public BooleanProperty landingPartyProperty() {
        return landingParty;
    }
    public void setLandingParty(boolean landingParty) {
        this.landingParty.set(landingParty);
    }
    public boolean hasShieldsOfficer() {
        return shieldsOfficer.get();
    }
    public BooleanProperty shieldsOfficerProperty() {
        return shieldsOfficer;
    }
    public void setShieldsOfficer(boolean shieldsOfficer) {
        this.shieldsOfficer.set(shieldsOfficer);
    }
    public boolean hasWeaponsOfficer() {
        return weaponsOfficer.get();
    }
    public BooleanProperty weaponsOfficerProperty() {
        return weaponsOfficer;
    }
    public void setWeaponsOfficer(boolean weaponsOfficer) {
        this.weaponsOfficer.set(weaponsOfficer);
    }
    public boolean hasCargoOfficer() {
        return cargoOfficer.get();
    }
    public BooleanProperty cargoOfficerProperty() {
        return cargoOfficer;
    }
    public void setCargoOfficer(boolean cargoOfficer) {
        this.cargoOfficer.set(cargoOfficer);
    }

    public int getBlack() {
        return black.get();
    }
    public IntegerProperty blackProperty() {
        return black;
    }
    public void setBlack(int black) {
        this.black.set(black);
    }
    public int getBlue() {
        return blue.get();
    }
    public IntegerProperty blueProperty() {
        return blue;
    }
    public void setBlue(int blue) {
        this.blue.set(blue);
    }
    public int getGreen() {
        return green.get();
    }
    public IntegerProperty greenProperty() {
        return green;
    }
    public void setGreen(int green) {
        this.green.set(green);
    }
    public int getRed() {
        return red.get();
    }
    public IntegerProperty redProperty() {
        return red;
    }
    public void setRed(int red) {
        this.red.set(red);
    }
    public int getArtefact() {
        return artefact.get();
    }
    public IntegerProperty artefactProperty() {
        return artefact;
    }
    public void setArtefact(int artefact) {
        this.artefact.set(artefact);
    }

    public int getShields() {
        return shields.get();
    }
    public IntegerProperty shieldsProperty() {
        return shields;
    }
    public void setShields(int shields) {
        this.shields.set(shields);
    }
    public int getWeapons() {
        return weapons.get();
    }
    public IntegerProperty weaponsProperty() {
        return weapons;
    }
    public void setWeapons(int weapons) {
        this.weapons.set(weapons);
    }
    public int getFuel() {
        return fuel.get();
    }
    public IntegerProperty fuelProperty() {
        return fuel;
    }
    public void setFuel(int fuel) {
        this.fuel.set(fuel);
    }



    private int maxShields;
    private int weaponsPerLevel = 9;
    private int weaponsLevel = 1;
    private int maxWeaponsLevel;
    private int maxFuel;
    private int crew = 6;
    private int maxCrew = 6;

    private List<Resource> blackStorage = new ArrayList<>();
    private List<Resource> redStorage = new ArrayList<>();
    private List<Resource> blueStorage = new ArrayList<>();
    private List<Resource> greenStorage = new ArrayList<>();
    private List<Resource> artefacts = new ArrayList<>();
    private int storagePerLevel = 6;
    private int storageLevel = 1;
    private int maxStorageLevel;

    private Drone drone;


    public Ship(int maxShields, int maxWeaponsLevel, int maxFuel, int maxStorageLevel) {
        this.maxShields = maxShields;
        this.maxWeaponsLevel = maxWeaponsLevel;
        this.maxFuel = maxFuel;
        this.maxStorageLevel = maxStorageLevel;
        drone = new Drone();

        setCaptain(true);
        setCargoOfficer(true);
        setShieldsOfficer(true);
        setWeaponsOfficer(true);
        setNavigationOfficer(true);
        setLandingParty(true);

        setShields(maxShields);
        setFuel(maxFuel);
        setWeapons(weaponsLevel * weaponsPerLevel);

        setBlack(0);
        setBlue(0);
        setGreen(0);
        setRed(0);
        setArtefact(0);
    }

    public boolean removeShields(int points) {
        if(getShields() - points >= 0) {
            setShields(getShields() - points);
            return true;
        }
        else setShields(0);
        return false;
    }
    public void addShields() {
        if(getShields() + 1 <= maxShields)
            setShields(getShields() + 1);
    }
    public int getMaxShields() {
        return maxShields;
    }

    public boolean removeWeapons(int points) {
        if(getWeapons() - points >= 0) {
            setWeapons(getWeapons() - points);
            return true;
        }
        else setWeapons(0);
        return false;
    }
    public void addWeapons() {
        if(getWeapons() + 1 <= weaponsLevel * weaponsPerLevel)
            setWeapons(getWeapons() + 1);
    }
    public int getWeaponsLevel() {
        return weaponsLevel;
    }
    public void addWeaponsLevel() {
        if(weaponsLevel + 1 <= maxWeaponsLevel) {
            int temp = (weaponsLevel * weaponsPerLevel) - getWeapons();
            weaponsLevel++;
            setWeapons(temp * weaponsLevel);
        }
    }
    public int getMaxWeaponsLevel() {
        return maxWeaponsLevel;
    }
    public int getWeaponsPerLevel() {
        return weaponsPerLevel;
    }


    public boolean addFuel(int value) {
        if(getFuel() + value <= maxFuel) {
            setFuel(getFuel() + value);
            return true;
        }
        return false;
    }
    public boolean removeFuel(int value) {
        if(getFuel() - value >= 0) {
            setFuel(getFuel() - value);
            return true;
        }
        return false;
    }
    public int getMaxFuel() {
        return maxFuel;
    }

    public int getCrew() {
        return crew;
    }
    public boolean addCrew(int value) {
        if(crew + value <= maxCrew) {
            crew += value;
            updateCrew();
            return true;
        }
        return false;
    }
    public boolean removeCrew(int value) {
        if(crew - value >= 0) {
            crew -= value;
            updateCrew();
            return true;
        }
        return false;
    }

    public int getMaxCrew() {
        return maxCrew;
    }

    private void updateCrew() {
        setCaptain(false);
        setCargoOfficer(false);
        setShieldsOfficer(false);
        setWeaponsOfficer(false);
        setNavigationOfficer(false);
        setLandingParty(false);

        if(crew >= 1)
            setCaptain(true);
        if(crew >= 2)
            setNavigationOfficer(true);
        if(crew >= 3)
            setLandingParty(true);
        if(crew >= 4)
            setShieldsOfficer(true);
        if(crew >= 5)
            setWeaponsOfficer(true);
        if(crew == 6)
            setCargoOfficer(true);
    }

    public int getStorageLevel() {
        return storageLevel;
    }
    public void addStorageLevel() {
        if(storageLevel + 1 <= maxStorageLevel)
            storageLevel++;
    }
    public int getMaxStorageLevel() {
        return maxStorageLevel;
    }
    public int getStoragePerLevel() {
        return storagePerLevel;
    }

    public void addToBlackStorage(int value) {
        if(blackStorage.size() + value >= storageLevel * storagePerLevel) {
            for(int i = 0; i < (storageLevel * storagePerLevel)- blackStorage.size(); i++)
                blackStorage.add(new BlackResource());
        }
        else {
            for(int i = 0; i < value; i++)
                blackStorage.add(new BlackResource());
        }
        setBlack(blackStorage.size());
    }
    public boolean removeFromBlackStorage(int value) {
        if(blackStorage.size() - value >= 0) {
            for(int i = 0; i < value; i++)
                blackStorage.remove(blackStorage.size() - 1);
            return true;
        }
        setBlack(blackStorage.size());
        return false;
    }

    public void addToRedStorage(int value) {
        if(redStorage.size() + value >= storageLevel * storagePerLevel) {
            for(int i = 0; i < (storageLevel * storagePerLevel)- redStorage.size(); i++)
                redStorage.add(new RedResource());
        }
        else {
            for(int i = 0; i < value; i++)
                redStorage.add(new RedResource());
        }
        setRed(redStorage.size());
    }
    public boolean removeFromRedStorage(int value) {
        if(redStorage.size() - value >= 0) {
            for(int i = 0; i < value; i++)
                redStorage.remove(redStorage.size() - 1);
            return true;
        }
        setRed(redStorage.size());
        return false;
    }

    public void addToBlueStorage(int value) {
        if(blueStorage.size() + value >= storageLevel * storagePerLevel) {
            for(int i = 0; i < (storageLevel * storagePerLevel)- blueStorage.size(); i++)
                blueStorage.add(new BlueResource());
        }
        else {
            for(int i = 0; i < value; i++)
                blueStorage.add(new BlueResource());
        }
        setBlue(blueStorage.size());
    }
    public boolean removeFromBlueStorage(int value) {
        if(blueStorage.size() - value >= 0) {
            for(int i = 0; i < value; i++)
                blueStorage.remove(blueStorage.size() - 1);
            return true;
        }
        setBlue(blueStorage.size());
        return false;
    }

    public void addToGreenStorage(int value) {
        if(greenStorage.size() + value >= storageLevel * storagePerLevel) {
            for(int i = 0; i < (storageLevel * storagePerLevel)- greenStorage.size(); i++)
                greenStorage.add(new GreenResource());
        }
        else {
            for(int i = 0; i < value; i++)
                greenStorage.add(new GreenResource());
        }
        setGreen(greenStorage.size());
    }
    public boolean removeFromGreenStorage(int value) {
        if(greenStorage.size() - value >= 0) {
            for(int i = 0; i < value; i++)
                greenStorage.remove(greenStorage.size() - 1);
            return true;
        }
        setGreen(greenStorage.size());
        return false;
    }

    public void addArtefact() {
        artefacts.add(new Artefact());
        setArtefact(artefacts.size());
        Singleton.getInstance().checkWin();
    }
    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    @Override
    public abstract String toString();
}
