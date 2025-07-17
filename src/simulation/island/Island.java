package simulation.island;

import simulation.entities.Animal;
import simulation.utils.MyRandom;
import simulation.utils.Settings;
import simulation.utils.statistics.Statistics;
import simulation.utils.position.Position;
import simulation.utils.position.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;

public class Island {
    private final Location[][] locations;
    private final ExecutorService fixedTP = Executors.newFixedThreadPool(8);

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
        if (animalFullness <= 0 || plantsFullness < 0 || animalFullness > 100 || plantsFullness > 100) {
            throw new IllegalArgumentException("Invalid fullness dimensions");
        }
        spawnRandomAnimals(animalFullness);
        fillPlants(plantsFullness);
    }


    public void spawnRandomAnimals(int fullness) {
        if (fullness < 0 || fullness > 100) {
            throw new IllegalArgumentException("Fullness must be from 0 to zero");
        }
        List<Callable<Void>> tasks = new ArrayList<>();
        for (Location[] row : locations) {
            for (Location location : row) {
                tasks.add(() -> {
                    location.spawnRandomAnimals(fullness);
                    return null;
                });
            }
        }
        try {
            fixedTP.invokeAll(tasks);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void fillPlants(int fullness) {
        if (fullness < 0 || fullness > 100) {
            throw new IllegalArgumentException("Fullness must be from 0 to zero");
        }
        List<Callable<Void>> tasks = new ArrayList<>();
        for (Location[] row : locations) {
            for (Location location : row) {
                tasks.add(() -> {
                    location.fillPlants(fullness);
                    return null;
                });
            }
        }
        try {
            fixedTP.invokeAll(tasks);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void growPlantsRandom(int minCount, int maxCount) {
        if (minCount <= 0 || minCount > maxCount) {
            throw new IllegalArgumentException("Illegal counts");
        }
        List<Callable<Void>> tasks = new ArrayList<>();
        for (Location[] row : locations) {
            for (Location location : row) {
                tasks.add(() -> {
                    location.randomPlantsGrow(minCount, maxCount);
                    return null;
                });
            }
        }
        try {
            fixedTP.invokeAll(tasks);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void eatingTick() {
        executeForAllLocations(Location::eatingTick);
    }


    public void reproducingTick() {
        executeForAllLocations(Location::reproducingTick);
    }

    public void hungerTick() {
        executeForAllLocations(Location::hungerTick);
    }

    public void moveTick() {
        if (locations.length == 1 || locations[0].length == 1) {
            return;
        }

        final Position minPos = new Position(0, 0);
        final Position maxPos = new Position(locations.length - 1, locations[0].length - 1);

        List<Callable<Void>> tasks = new ArrayList<>();

        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                final int x = i;
                final int y = j;
                tasks.add(() -> {
                    Location currentLocation = locations[x][y];
                    List<Animal> animalsToMove = currentLocation.getAnimalsReadyToMove();

                    for (Animal animal : animalsToMove) {
                        if (!animal.isReadyToMove()) continue;

                        Position basePos = new Position(x, y);
                        Vector moveVector = MyRandom.getRandomVector(animal.getMaxSpeed());
                        Position newPos = basePos.addVector(moveVector).limit(minPos, maxPos);

                        Location targetLocation = locations[newPos.getX()][newPos.getY()];

                        if (targetLocation.tryAddAnimal(animal)) {
                            currentLocation.removeAnimal(animal);
                            animal.getTired(Settings.MOVE_TIRED);
                            Statistics.getInstance().addMove();
                        }
                    }
                    return null;
                });
            }
        }

        try {
            fixedTP.invokeAll(tasks);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void cleanDeadEntities() {
        executeForAllLocations(Location::removeDeadEntities);
    }

    public int getAveragePlantFullness() {
        return calculateAverage(Location::getPlantFullness);
    }

    public int getAverageAnimalFullness() {
        return calculateAverage(Location::getAnimalFullness);
    }

    public Location[][] getLocations() {
        return locations;
    }

    private int calculateAverage(Function<Location, Integer> fullnessFunction) {
        if (locations.length == 0 || locations[0].length == 0) {
            return 0;
        }

        AtomicInteger sum = new AtomicInteger(0);
        AtomicInteger count = new AtomicInteger(0);
        List<Callable<Void>> tasks = new ArrayList<>();

        for (Location[] row : locations) {
            for (Location location : row) {
                tasks.add(() -> {
                    sum.addAndGet(fullnessFunction.apply(location));
                    count.incrementAndGet();
                    return null;
                });
            }
        }
        try {
            fixedTP.invokeAll(tasks);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return 0;
        }

        return count.get() > 0 ? sum.get() / count.get() : 0;
    }

    private void executeForAllLocations(Consumer<Location> action) {
        List<Callable<Void>> tasks = new ArrayList<>();
        for (Location[] row : locations) {
            for (Location loc : row) {
                tasks.add(() -> {
                    action.accept(loc);
                    return null;
                });
            }
        }
        try {
            fixedTP.invokeAll(tasks);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
