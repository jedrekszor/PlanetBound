package jedrekszor.planetbound.logic.data.exploration;

public abstract class SurfaceWalker {
    private Coordinates coordinates = new Coordinates();
    private Coordinates destination = new Coordinates();
    public boolean isFighting = false;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setDestination(Coordinates destination) {
        this.destination = destination;
    }

    public Coordinates getDestination() {
        return destination;
    }

    public void move() {
        int xDiff = destination.getX() - coordinates.getX();
        int yDiff = destination.getY() - coordinates.getY();
        if(Math.abs(xDiff) > Math.abs(yDiff)) {
            if(xDiff > 0)
                coordinates.setCoordinates(coordinates.getX() + 1, coordinates.getY());
            else
                coordinates.setCoordinates(coordinates.getX() - 1, coordinates.getY());
        } else {
            if(yDiff > 0)
                coordinates.setCoordinates(coordinates.getX(), coordinates.getY() + 1);
            else
                coordinates.setCoordinates(coordinates.getX(), coordinates.getY() - 1);
        }
    }

    @Override
    public String toString() {
        return "X: " + coordinates.getX() + ", Y: " + coordinates.getY();
    }
}
