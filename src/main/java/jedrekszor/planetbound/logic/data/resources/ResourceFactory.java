package jedrekszor.planetbound.logic.data.resources;

import jedrekszor.planetbound.logic.Singleton;

import java.util.ArrayList;
import java.util.List;

public class ResourceFactory {
    public static Resource getRandomResource(boolean black, boolean blue, boolean green, boolean red, boolean artefact) {
        List<Resource> resources = new ArrayList<>();
        if(black)
            resources.add(new BlackResource());
        if(blue)
            resources.add(new BlueResource());
        if(green)
            resources.add(new GreenResource());
        if(red)
            resources.add(new RedResource());
        if(artefact)
            resources.add(new Artefact());

        if(resources.size() == 0)
            return null;
        int rand = (int)(Math.random() * resources.size());
        return resources.get(rand);
    }

    public static void removeRandomResource(boolean black, boolean blue, boolean green, boolean red, int amount) {
        Resource temp = getRandomResource(black, blue, green, red, false);
        if(temp == null)
            return;
        if(temp instanceof BlackResource)
            Singleton.getInstance().getShip().removeFromBlackStorage(amount);
        else if(temp instanceof BlueResource)
            Singleton.getInstance().getShip().removeFromBlueStorage(amount);
        else if(temp instanceof GreenResource)
            Singleton.getInstance().getShip().removeFromGreenStorage(amount);
        else if(temp instanceof RedResource)
            Singleton.getInstance().getShip().removeFromRedStorage(amount);
    }

    public static void addRandomResource(boolean black, boolean blue, boolean green, boolean red, boolean artefact, int amount) {
        Resource temp = getRandomResource(black, blue, green, red, artefact);
        if(temp instanceof BlackResource)
            Singleton.getInstance().getShip().addToBlackStorage(amount);
        else if(temp instanceof BlueResource)
            Singleton.getInstance().getShip().addToBlueStorage(amount);
        else if(temp instanceof GreenResource)
            Singleton.getInstance().getShip().addToGreenStorage(amount);
        else if(temp instanceof RedResource)
            Singleton.getInstance().getShip().addToRedStorage(amount);
        else if(temp instanceof Artefact)
            Singleton.getInstance().getShip().addArtefact();
    }

    public static void removeAllResources(int amount) {
        Singleton.getInstance().getShip().removeFromBlackStorage(amount);
        Singleton.getInstance().getShip().removeFromBlueStorage(amount);
        Singleton.getInstance().getShip().removeFromGreenStorage(amount);
        Singleton.getInstance().getShip().removeFromRedStorage(amount);
    }

    public static void removeParticularResources(boolean black, boolean blue, boolean green, boolean red, int amount) {
        if(black)
            Singleton.getInstance().getShip().removeFromBlackStorage(amount);
        if(blue)
            Singleton.getInstance().getShip().removeFromBlueStorage(amount);
        if(green)
            Singleton.getInstance().getShip().removeFromGreenStorage(amount);
        if(red)
            Singleton.getInstance().getShip().removeFromRedStorage(amount);
    }
}
