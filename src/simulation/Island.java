package simulation;

import simulation.utils.Settings;

public class Island {
    private Location[][] locations;

    public Island(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Island dimensions must be positive");
        }
        locations = new Location[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                locations[i][j] = new Location(Settings.LOCATION_ANIMALS_CAPACITY, Settings.LOCATION_PLANTS_CAPACITY);
            }
        }
    }

    public Island(int width, int height, int animalFullness, int plantsFullness) {
        this(width, height);
        spawnRandomAnimals(animalFullness);
        growPlants(plantsFullness);
    }


    public void spawnRandomAnimals(int fullness) {
        for (Location[] row : locations) {
            for (Location location : row) {
                location.spawnRandomAnimals(fullness);
            }
        }
    }

    public void growPlants(int maxCount) {
        for (Location[] row : locations) {
            for (Location location : row) {
                location.growPlants(maxCount);
            }
        }
    }

    public void growPlantsRandom(int maxCount) {
        for (Location[] row : locations) {
            for (Location location : row) {
                location.randomPlantsGrow(maxCount);
            }
        }
    }

    public void eatingTick() {
        for (Location[] row : locations) {
            for (Location location : row) {
                location.eatingTick();
            }
        }
    }
}
