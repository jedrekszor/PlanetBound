package jedrekszor.planetbound.logic;

import javafx.beans.property.*;
import jedrekszor.planetbound.logic.data.events.Event;
import jedrekszor.planetbound.logic.data.events.EventFactory;
import jedrekszor.planetbound.logic.data.exploration.Surface;
import jedrekszor.planetbound.logic.data.planet.Planet;
import jedrekszor.planetbound.logic.data.resources.ResourceFactory;
import jedrekszor.planetbound.logic.data.ship.Ship;
import jedrekszor.planetbound.logic.states.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

//gamedata
//save and load

public class Singleton implements Serializable {
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
    private transient StringProperty currentPlanetColor = new SimpleStringProperty();
    private transient BooleanProperty wonGame = new SimpleBooleanProperty();
    private transient BooleanProperty lostGame = new SimpleBooleanProperty();
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
        if (currentState == won)
            currentState.win();
    }

    public void checkLose() {
        currentState = currentState.checkLose();
        if (currentState == lost)
            currentState.lose();
    }

    public void quit() {
        System.exit(0);
    }

    public void playAgain() {
        setLostGame(false);
        setWonGame(false);
        currentState = shipChoice;
    }

    public void terminateExploration(boolean success) {
        currentSurface = null;
        if (success) {
            Logger.log("Success, returning to the mothership");
        } else {
            Logger.log("Failure, returning to the mothership");
            ship.setDrone(null);
        }
        currentSurface = null;
        currentState = actionChoice;
    }

    public void shop() {
        currentState = currentState.shop();
    }

    public void moveDrone(int direction) {
        currentSurface.moveDrone(direction);
    }

    private void wormhole() {
        int rand = (int) (Math.random() * 8);
        Logger.log("Wormhole");
        if (rand == 0) {
            if (ship.hasShieldsOfficer()) {
                if (ship.getShields() == 0) {
                    ship.removeFuel(3);
                    ship.removeCrew(1);
                } else {
                    ship.removeFuel(3);
                    ship.removeShields(2);
                }
            } else {
                if (ship.getShields() == 0) {
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


    public void save() {
        try {
            FileOutputStream fileOut = new FileOutputStream("save/singleton.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(instance);
            out.writeUTF(new String(getCurrentPlanetColor().getBytes(), StandardCharsets.UTF_8));
            out.writeBoolean(isLostGame());
            out.writeBoolean(isWonGame());

            if(currentSurface != null) {
                out.writeInt(currentSurface.getAlienX());
                out.writeInt(currentSurface.getAlienY());
                out.writeUTF(currentSurface.getAlienColor());
                out.writeBoolean(currentSurface.getAlien().isDed());
                out.writeBoolean(currentSurface.isRunning());
                out.writeInt(currentSurface.getLandingLocation().getX());
                out.writeInt(currentSurface.getLandingLocation().getY());
                out.writeInt(currentSurface.getResourceLocation().getX());
                out.writeInt(currentSurface.getResourceLocation().getY());
                out.writeUTF(currentSurface.getEventLog());
                out.writeBoolean(currentSurface.getDrone().isReturning());
            } else {
                out.writeInt(-1);
                out.writeInt(-1);
                out.writeUTF("");
                out.writeBoolean(false);
                out.writeBoolean(false);
                out.writeInt(-1);
                out.writeInt(-1);
                out.writeInt(-1);
                out.writeInt(-1);
                out.writeUTF("");
                out.writeBoolean(false);
            }

            out.writeBoolean(ship.hasCaptain());
            out.writeBoolean(ship.hasNavigationOfficer());
            out.writeBoolean(ship.hasLandingParty());
            out.writeBoolean(ship.hasShieldsOfficer());
            out.writeBoolean(ship.hasWeaponsOfficer());
            out.writeBoolean(ship.hasCargoOfficer());
            out.writeInt(ship.getBlack());
            out.writeInt(ship.getBlue());
            out.writeInt(ship.getGreen());
            out.writeInt(ship.getRed());
            out.writeInt(ship.getArtefact());
            out.writeInt(ship.getShields());
            out.writeInt(ship.getWeapons());
            out.writeInt(ship.getFuel());

            if(ship.getDrone() != null) {
                out.writeInt(ship.getDrone().getCoordinates().getX());
                out.writeInt(ship.getDrone().getCoordinates().getY());
                out.writeInt(ship.getDrone().getHp());
            } else {
                out.writeInt(-1);
                out.writeInt(-1);
                out.writeInt(-1);
            }

            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            FileInputStream fileIn = new FileInputStream("save/singleton.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            instance = (Singleton) in.readObject();
            instance.currentPlanetColor = new SimpleStringProperty(in.readUTF());
            instance.lostGame = new SimpleBooleanProperty(in.readBoolean());
            instance.wonGame = new SimpleBooleanProperty(in.readBoolean());

            int alienx = in.readInt();
            int alieny = in.readInt();
            String aliencolor = in.readUTF();
            boolean alienDed = in.readBoolean();
            boolean surfaceRunning = in.readBoolean();
            int xLanding = in.readInt();
            int yLanding = in.readInt();
            int xResource = in.readInt();
            int yResource = in.readInt();
            String eventLog = in.readUTF();
            boolean droneReturning = in.readBoolean();

            if(alienx != -1 && alieny != -1 && aliencolor.compareTo("") != 0) {
                instance.currentSurface.alienX = new SimpleIntegerProperty(alienx);
                instance.currentSurface.alienY = new SimpleIntegerProperty(alieny);
                instance.currentSurface.alienColor = new SimpleStringProperty(aliencolor);
                instance.currentSurface.getAlien().ded = new SimpleBooleanProperty(alienDed);
                instance.currentSurface.running = new SimpleBooleanProperty(surfaceRunning);
                instance.currentSurface.getLandingLocation().x = new SimpleIntegerProperty(xLanding);
                instance.currentSurface.getLandingLocation().y = new SimpleIntegerProperty(yLanding);
                instance.currentSurface.getResourceLocation().x = new SimpleIntegerProperty(xResource);
                instance.currentSurface.getResourceLocation().y = new SimpleIntegerProperty(yResource);
                instance.currentSurface.eventLog = new SimpleStringProperty(eventLog);
                instance.currentSurface.getDrone().returning = new SimpleBooleanProperty(droneReturning);
                instance.currentSurface.load();
            }


            instance.ship.captain = new SimpleBooleanProperty(in.readBoolean());
            instance.ship.navigationOfficer = new SimpleBooleanProperty(in.readBoolean());
            instance.ship.landingParty = new SimpleBooleanProperty(in.readBoolean());
            instance.ship.shieldsOfficer = new SimpleBooleanProperty(in.readBoolean());
            instance.ship.weaponsOfficer = new SimpleBooleanProperty(in.readBoolean());
            instance.ship.cargoOfficer = new SimpleBooleanProperty(in.readBoolean());
            instance.ship.black = new SimpleIntegerProperty(in.readInt());
            instance.ship.blue = new SimpleIntegerProperty(in.readInt());
            instance.ship.green = new SimpleIntegerProperty(in.readInt());
            instance.ship.red = new SimpleIntegerProperty(in.readInt());
            instance.ship.artefact = new SimpleIntegerProperty(in.readInt());
            instance.ship.shields = new SimpleIntegerProperty(in.readInt());
            instance.ship.weapons = new SimpleIntegerProperty(in.readInt());
            instance.ship.fuel = new SimpleIntegerProperty(in.readInt());


            int x = in.readInt();
            int y = in.readInt();
            int hp = in.readInt();

            if(x != -1 && y != -1 && hp != -1) {
                instance.ship.getDrone().getCoordinates().x = new SimpleIntegerProperty(x);
                instance.ship.getDrone().getCoordinates().y = new SimpleIntegerProperty(y);
                instance.ship.getDrone().hp = new SimpleIntegerProperty(hp);
            }
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
    }


    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null)
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
