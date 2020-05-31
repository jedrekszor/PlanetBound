package jedrekszor.planetbound.logic;

public class Dice {
    public static int roll() {
        return (int)(Math.random()*6) + 1;
    }

    public static int roll(int min, int max) {
        return (int)(Math.random()*(max - min)) + min;
    }
}
