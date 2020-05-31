package jedrekszor.planetbound.logic.data.planet;

import jedrekszor.planetbound.logic.Singleton;

public class PlanetFactory {
    public static Planet getPlanet() {
        Station station = null;
        int rands = (int)(Math.random()*10);
        if(rands <= 2)
            station = new Station();
        int rand = (int)(Math.random()*4);
        Planet p;
        switch (rand) {
            case 0: p = new GreenPlanet(station); break;
            case 1: p = new BlackPlanet(station); break;
            case 2: p = new RedPlanet(station); break;
            case 3: p = new BluePlanet(station); break;
            default: p = null;
        }
        Singleton.getInstance().setCurrentPlanetColor(p.getColor());
        return p;
    }
}
