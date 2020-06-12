package jedrekszor.planetbound.logic.data.exploration;

import javafx.beans.property.*;
import jedrekszor.planetbound.logic.Dice;
import jedrekszor.planetbound.logic.Logger;
import jedrekszor.planetbound.logic.Singleton;
import jedrekszor.planetbound.logic.data.exploration.aliens.Alien;
import jedrekszor.planetbound.logic.data.exploration.aliens.AlienFactory;
import jedrekszor.planetbound.logic.data.resources.Resource;
import jedrekszor.planetbound.logic.data.resources.ResourceFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Surface implements Serializable {
    private static int xSize = 6;
    private static int ySize = 6;

    public transient IntegerProperty alienX = new SimpleIntegerProperty();
    public transient IntegerProperty alienY = new SimpleIntegerProperty();
    public transient StringProperty alienColor = new SimpleStringProperty();
    public transient StringProperty eventLog = new SimpleStringProperty();

    public int getAlienX() {
        return alienX.get();
    }
    public IntegerProperty alienXProperty() {
        return alienX;
    }
    public void setAlienX(int alienX) {
        this.alienX.set(alienX);
    }

    public int getAlienY() {
        return alienY.get();
    }
    public IntegerProperty alienYProperty() {
        return alienY;
    }
    public void setAlienY(int alienY) {
        this.alienY.set(alienY);
    }

    public String getAlienColor() {
        return alienColor.get();
    }
    public StringProperty alienColorProperty() {
        return alienColor;
    }
    public void setAlienColor(String alienColor) {
        this.alienColor.set(alienColor);
    }

    public transient BooleanProperty running = new SimpleBooleanProperty();
    public boolean isRunning() {
        return running.get();
    }
    public BooleanProperty runningProperty() {
        return running;
    }
    public void setRunning(boolean running) {
        this.running.set(running);
    }

    private String resourceColor;
    public String getResourceColor() {
        return resourceColor;
    }
    public void setResourceColor(String resourceColor) {
        this.resourceColor = resourceColor;
    }

    public String getEventLog() {
        return eventLog.get();
    }
    public StringProperty eventLogProperty() {
        return eventLog;
    }
    public void setEventLog(String eventLog) {
        this.eventLog.set(eventLog);
    }
    public void addToEventLog(String log) {
        String currentLog = getEventLog();
        setEventLog(currentLog + "\n" + log);
    }

    private Drone drone;
    private Alien alien;
    private boolean success;
    public boolean getSuccess() {
        return success;
    }

    private Coordinates landingLocation = new Coordinates();
    private Coordinates resourceLocation = new Coordinates();

    public Surface() {
        setRunning(true);
        drone = Singleton.getInstance().getShip().getDrone();
        drone.setReturning(false);
        alien = (AlienFactory.getRandomAlien(
                Singleton.getInstance().getCurrentPlanet().hasBlack(),
                Singleton.getInstance().getCurrentPlanet().hasBlue(),
                Singleton.getInstance().getCurrentPlanet().hasGreen(),
                Singleton.getInstance().getCurrentPlanet().hasRed()));
        setLocations();
        setAlienX(alien.getCoordinates().getX());
        setAlienY(alien.getCoordinates().getY());
        setAlienColor(alien.getColor());
        Logger.log("New " + getAlienColor() + " alien");
        setResourceColor(ResourceFactory.getRandomResource(Singleton.getInstance().getCurrentPlanet().hasBlack(),
                    Singleton.getInstance().getCurrentPlanet().hasBlue(),
                    Singleton.getInstance().getCurrentPlanet().hasGreen(),
                    Singleton.getInstance().getCurrentPlanet().hasRed(),
                    Singleton.getInstance().getCurrentPlanet().hasArtefact()).getColor());
        Logger.log(resourceColor + " resource");
        setEventLog("");
    }

    private void setLocations() {
        int randX = (int)(Math.random() * getXSize());
        int randY = (int)(Math.random() * getYSize());
        landingLocation.setCoordinates(randX, randY);
        int rX, rY;
        do {
            rX = (int)(Math.random() * getXSize());
            rY = (int)(Math.random() * getYSize());
        } while (rX == randX && rY == randY);
        resourceLocation.setCoordinates(rX, rY);
        drone.getCoordinates().setCoordinates(landingLocation);
        drone.setDestination(resourceLocation);
        alien.setDestination(drone.getCoordinates());

    }

    public void load() {
        drone = Singleton.getInstance().getShip().getDrone();
    }

    public static int getXSize() {
        return xSize;
    }
    public static int getYSize() {
        return ySize;
    }

    public boolean canGoUp() {
        return drone.getCoordinates().getY() >= 1;
    }
    public boolean canGoDown() {
        return drone.getCoordinates().getY() <= 4;
    }
    public boolean canGoLeft() {
        return drone.getCoordinates().getX() >= 1;
    }
    public boolean canGoRight() {
        return drone.getCoordinates().getX() <= 4;
    }


    public void moveDrone(int direction) {
        switch (direction) {
            case 0: {   //UP
                drone.getCoordinates().setCoordinates(drone.getCoordinates().getX(), drone.getCoordinates().getY() - 1);
            } break;
            case 1: {   //DOWN
                drone.getCoordinates().setCoordinates(drone.getCoordinates().getX(), drone.getCoordinates().getY() + 1);
            } break;
            case 2: {   //RIGHT
                drone.getCoordinates().setCoordinates(drone.getCoordinates().getX() + 1, drone.getCoordinates().getY());
            } break;
            case 3: {   //LEFT
                drone.getCoordinates().setCoordinates(drone.getCoordinates().getX() - 1, drone.getCoordinates().getY());
            } break;
            default: Logger.log("Invalid drone movement direction");
        }

        alien.setDestination(drone.getCoordinates());
        alien.move();
        setAlienX(alien.getCoordinates().getX());
        setAlienY(alien.getCoordinates().getY());
        if(checkProximity())
            Logger.log("\n////////////////////FIGHT////////////////////");
        while(checkProximity())
            fight();
        if(!drone.isReturning() && drone.getCoordinates().getX() == drone.getDestination().getX() && drone.getCoordinates().getY() == drone.getDestination().getY()) {
            drone.setDestination(landingLocation);
            drone.setReturning(true);
            Logger.log(resourceColor + " resource picked");
        }
        if(drone.isReturning() && drone.getCoordinates().getX() == drone.getDestination().getX() && drone.getCoordinates().getY() == drone.getDestination().getY()) {
            Singleton.getInstance().terminateExploration(true);
            success = true;
            terminate();
        }
    }


    public boolean checkProximity() {
        boolean test = (Math.abs(drone.getCoordinates().getX() - alien.getCoordinates().getX()) <= 1 &&
                Math.abs(drone.getCoordinates().getY() - alien.getCoordinates().getY()) <= 1);
        return test;
    }

    public void fight() {
        if(alien.attack(drone)) {
            if(!drone.getHit(1)) {
                addToEventLog("Drone dead!, returning to mothership\"");
                Logger.log("\nDrone ded!, returning to mothership");
                Singleton.getInstance().terminateExploration(false);
                success = false;
                terminate();
            } else {
                addToEventLog("Drone hit! Current hp: " + drone.getHp());
                Logger.log("Drone hit! Current hp: " + drone.getHp());
            }
        } else {
            Logger.log("Alien missed its attack!");
            addToEventLog("Alien missed its attack!");
        }
        if(alien.getAttacked(drone)) {
            Logger.log("\nAlien ded!");
            addToEventLog("Alien dead!\n\n----------------------------");
            Logger.log("////////////////////END FIGHT////////////////////");
            alien.setDed(true);
            alien = AlienFactory.getRandomAlien(
                    Singleton.getInstance().getCurrentPlanet().hasBlack(),
                    Singleton.getInstance().getCurrentPlanet().hasBlue(),
                    Singleton.getInstance().getCurrentPlanet().hasGreen(),
                    Singleton.getInstance().getCurrentPlanet().hasRed());
            setAlienX(alien.getCoordinates().getX());
            setAlienY(alien.getCoordinates().getY());
            setAlienColor(alien.getColor());
            Logger.log("New " + getAlienColor() + " alien");
        } else {
            Logger.log("Drone missed its attack!");
            addToEventLog("Drone missed its attack!");
        }
    }

    void visualize() {
        String result = "";
        for(int i = 0; i < xSize; i++) {
            for(int j = 0; j < ySize; j++) {
                if(drone.getCoordinates().getX() == i && drone.getCoordinates().getY() == j)
                    result += "D\t";
                else if(alien.getCoordinates().getX() == i && alien.getCoordinates().getY() == j)
                    result += "A\t";
                else if(resourceLocation.getX() == i && resourceLocation.getY() == j)
                    result += "R\t";
                else if(landingLocation.getX() == i && landingLocation.getY() == j)
                    result += "L\t";
                else
                    result += "#\t";
            }
            result += "\n";
        }
    }

    public Drone getDrone() {
        return drone;
    }

    public Alien getAlien() {
        return alien;
    }

    public Coordinates getLandingLocation() {
        return landingLocation;
    }

    public Coordinates getResourceLocation() {
        return resourceLocation;
    }

    private void terminate() {
        if(success) {
            int rand = Dice.roll();
            if(resourceColor.compareTo("black") == 0)
                Singleton.getInstance().getShip().addToBlackStorage(rand);
            else if(resourceColor.compareTo("blue") == 0)
                Singleton.getInstance().getShip().addToBlueStorage(rand);
            else if(resourceColor.compareTo("green") == 0)
                Singleton.getInstance().getShip().addToGreenStorage(rand);
            else if(resourceColor.compareTo("red") == 0)
                Singleton.getInstance().getShip().addToRedStorage(rand);
            else if(resourceColor.compareTo("pink") == 0)
                Singleton.getInstance().getShip().addArtefact();
            else Logger.log("WRONG COLOR OF RESOURCE: " + resourceColor);
        }
        setRunning(false);
    }
}
