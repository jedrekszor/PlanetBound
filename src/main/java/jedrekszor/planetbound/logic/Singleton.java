package jedrekszor.planetbound.logic;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import jedrekszor.planetbound.logic.data.events.Event;
import jedrekszor.planetbound.logic.data.events.EventFactory;
import jedrekszor.planetbound.logic.data.exploration.Surface;
import jedrekszor.planetbound.logic.data.planet.Planet;
import jedrekszor.planetbound.logic.data.resources.ResourceFactory;
import jedrekszor.planetbound.logic.data.ship.Ship;
import jedrekszor.planetbound.logic.states.*;

//gamedata
//save and load

public class Singleton {
    private static Singleton instance;
    private Ship ship;
    private State shipChoice = new ShipChoice();
    private State actionChoice = new ActionChoice();
    private State handlingEvent = new HandlingEvent();
    private State exploration = new Exploration();
    private State won = new Won();
    private State lost = new Lost();
    private State convertingResources = new ConvertingResources();
    private State buyingUpgrades = new BuyingUpgrades();


    private State currentState = shipChoice;
    private Planet currentPlanet = null;
    private StringProperty currentPlanetColor = new SimpleStringProperty();
    private BooleanProperty wonGame = new SimpleBooleanProperty();
    private BooleanProperty lostGame = new SimpleBooleanProperty();
    private Event currentEvent = null;
    private Surface currentSurface = null;

    public boolean isWonGame() {
        return wonGame.get();
    }
    public BooleanProperty wonGameProperty() {
        return wonGame;
    }
    public void setWonGame(boolean wonGame) {
        this.wonGame.set(wonGame);
    }

    public boolean isLostGame() {
        return lostGame.get();
    }
    public BooleanProperty lostGameProperty() {
        return lostGame;
    }
    public void setLostGame(boolean lostGame) {
        this.lostGame.set(lostGame);
    }

    public StringProperty currentPlanetColorProperty() {
        return currentPlanetColor;
    }
    public void setCurrentPlanetColor(String color) {
        currentPlanetColor.setValue(color);
    }
    public String getCurrentPlanetColor() {
        return currentPlanetColor.get();
    }

    public void chooseMilitary() {
        currentState = currentState.chooseShip(true);
        currentState = currentState.drawPlanet();
    }
    public void chooseMining() {
        currentState = currentState.chooseShip(false);
        currentState = currentState.drawPlanet();
    }
    public void advance() {
        currentState = currentState.advance();
        ship.removeFuel(1);
        checkLose();
        wormhole();
        currentEvent = EventFactory.getRandomEvent();
    }

    public void resolveEvent() {
        currentState.resolveEvent();
        currentState = currentState.checkLose();
        ship.removeFuel(1);
        checkLose();
        currentState = currentState.drawPlanet();
    }

    public void explore() {
        ship.removeFuel(1);
        currentState = currentState.explore();
    }
    public void checkWin() {
        currentState = currentState.checkWin();
        if(currentState == won)
            currentState.win();
    }
    public void checkLose() {
        currentState = currentState.checkLose();
        if(currentState == lost)
            currentState.lose();
    }

    public void quit() {
        //quit
    }
    public void playAgain() {
        setLostGame(false);
        setWonGame(false);
        currentState = shipChoice;
    }

    public void terminateExploration(boolean success) {
        currentSurface = null;
        if(success) {
            System.out.println("Success, returning to the mothership");
        } else {
            System.out.println("Failure, returning to the mothership");
            ship.setDrone(null);
        }
        currentSurface = null;
        if(currentPlanet.hasStation())
            currentState = buyingUpgrades;
        else
            currentState = convertingResources;
    }

    public void moveDrone(int direction) {
        currentSurface.moveDrone(direction);
    }

    private void wormhole() {
        int rand = (int)(Math.random()*8);
        System.out.println("Wormhole");
        if(rand == 0) {
            if(ship.hasShieldsOfficer()) {
                if(ship.getShields() == 0) {
                    ship.removeFuel(3);
                    ship.removeCrew(1);
                } else {
                    ship.removeFuel(3);
                    ship.removeShields(2);
                }
            } else {
                if(ship.getShields() == 0) {
                    ship.removeFuel(4);
                    ship.removeCrew(1);
                } else {
                    ship.removeFuel(4);
                    ship.removeShields(4);
                }
            }
            checkLose();
        }
    }



//    can do on station
    public void buyRedResource() {

    }
    public void buyBlackResource() {

    }
    public void buyBlueResource() {

    }
    public void buyGreenResource() {

    }
    public void serviceDrone() {
        currentState.serviceDrone();

    }
    public void upgradeCargo() {
        currentState.upgradeCargo();
    }
    public void upgradeWeapons() {
        currentState.upgradeWeapons();
    }
    public void buyDrone() {
        currentState.buyDrone();
    }
    public void hireCrew() {
        currentState.hireCrew();
    }

//    can do anywhere
    public void buyFuel() {
        currentState.buyFuel();
    }
    public void buyWeapon() {
        currentState.buyWeapon();
    }
    public void buyShield() {
        currentState.buyShield();
    }

    public void endShopping() {
        currentState = currentState.endShopping();
    }



    private Singleton(){}
    public static Singleton getInstance() {
        if(instance == null)
            instance = new Singleton();
        return instance;
    }

    public State getCurrentState() {
        return currentState;
    }
    public State getShipChoice() {
        return shipChoice;
    }
    public State getActionChoice() {
        return actionChoice;
    }
    public State getHandlingEvent() {
        return handlingEvent;
    }
    public State getExploration() {
        return exploration;
    }
    public State getWon() {
        return won;
    }
    public State getLost() {
        return lost;
    }
    public State getConvertingResources() {
        return convertingResources;
    }
    public State getBuyingUpgrades() {
        return buyingUpgrades;
    }
    public void setShip(Ship ship) {
        this.ship = ship;
    }
    public Ship getShip() {
        return ship;
    }
    public Event getCurrentEvent() {
        return currentEvent;
    }
    public void setCurrentSurface(Surface surface) {
        this.currentSurface = surface;
    }
    public Surface getCurrentSurface() {
        return currentSurface;
    }
    public Planet getCurrentPlanet() {
        return currentPlanet;
    }

    public void setCurrentPlanet(Planet currentPlanet) {
        this.currentPlanet = currentPlanet;
    }

    @Override
    public String toString() {
        return "Current state: " + currentState + ", Ship: " + ship + ", Current planet: " + currentPlanet + ", Has station: " + currentPlanet.hasStation();
    }
}
