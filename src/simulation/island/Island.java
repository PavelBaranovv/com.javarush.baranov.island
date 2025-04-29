package simulation.island;

import simulation.entities.Animal;
import simulation.utils.MyRandom;
import simulation.utils.Settings;
import simulation.utils.Statistics;
import simulation.utils.position.Position;
import simulation.utils.position.Vector;

import java.util.List;

public class Island {
    private final Location[][] locations;

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

    public void reproducingTick() {
        for (Location[] row : locations) {
            for (Location location : row) {
                location.reproducingTick();
            }
        }
    }
    
    public void moveTick() {
        final Position minPos = new Position(0, 0);
        final Position maxPos = new Position(locations.length - 1, locations[0].length - 1);

        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                Location location = locations[i][j];

                List<Animal> animals = location.removeRandomAnimals(Settings.MOVE_CHANCE);

                for (Animal animal : animals) {
                    Position basePos = new Position(i, j);
                    Vector r = MyRandom.getRandomVector(animal.getMaxSpeed());
                    Position newPos = basePos.addVector(r).limit(minPos, maxPos);

                    locations[newPos.getX()][newPos.getY()].addAnimal(animal);
                    Statistics.getInstance().addMoves();
                }
            }
        }
    }
}
