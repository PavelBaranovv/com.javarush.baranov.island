package simulation;

public class Island {
    private Location[][] locations;

    public Island(int width, int height) {
        locations = new Location[width][height];
    }

    public Island(int width, int height, int fullness) {
        locations = new Location[width][height];
        randomFill(fullness);
    }

    private void randomFill(int fullness) {
        if (fullness < 0 || fullness > 100) {
            throw new IllegalArgumentException("Fullness must be from 0 to 100");
        }

    }
}
