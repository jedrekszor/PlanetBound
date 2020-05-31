package jedrekszor.planetbound.logic.data.planet;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import jedrekszor.planetbound.logic.data.resources.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Planet {
    private String color;
    private Station station;
    List<Resource> resources = new ArrayList<>();

    public Planet(Station station) {
        this.station = station;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public boolean hasBlack() {
        for(Resource r: resources) {
            if(r instanceof BlackResource)
                return true;
        }
        return false;
    }
    public boolean hasBlue() {
        for(Resource r: resources) {
            if(r instanceof BlueResource)
                return true;
        }
        return false;
    }
    public boolean hasGreen() {
        for(Resource r: resources) {
            if(r instanceof GreenResource)
                return true;
        }
        return false;
    }
    public boolean hasRed() {
        for(Resource r: resources) {
            if(r instanceof RedResource)
                return true;
        }
        return false;
    }
    public boolean hasArtefact() {
        for(Resource r: resources) {
            if(r instanceof Artefact)
                return true;
        }
        return false;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public boolean hasStation() {
        return station != null;
    }

    @Override
    public abstract String toString();
}
