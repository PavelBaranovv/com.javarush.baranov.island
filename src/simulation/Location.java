package simulation;

import simulation.entities.Animal;
import simulation.entities.Entity;
import simulation.exceptions.EntityPlaceException;
import simulation.factories.AnimalFactory;
import simulation.factories.PlantFactory;
import simulation.utils.MyRandom;
import simulation.utils.Settings;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Location {
    private AtomicInteger availableCapacity = new AtomicInteger();
    private AtomicInteger plantsCount = new AtomicInteger();
    private List<Entity> entities;

    public Location(int capacity) {
        this.availableCapacity.set(capacity);
        entities = new CopyOnWriteArrayList<>();
    }

    public void addAnimal(Animal animal) {
        if (animal.getSize() > getAvailableCapacity()) {
            throw new EntityPlaceException("Entity is too big for this location");
        }
        entities.add(animal);
        availableCapacity.addAndGet(-animal.getSize());
    }

    public void spawnRandomAnimals(int fullness) {
        if (fullness < 0 || fullness > 100) {
            throw new IllegalArgumentException("Fullness must be from 0 to zero");
        }
        int needToFill = availableCapacity.get() * fullness / 100;
        AnimalFactory factory = new AnimalFactory();
        while (needToFill > 0) {
            Animal newAnimal = factory.produceRandomAnimal(needToFill);
            entities.add(newAnimal);
            needToFill -= newAnimal.getSize();
            availableCapacity.addAndGet(-newAnimal.getSize());
        }
    }

    public void growPlants(int count) {
        if (count < 0) throw new IllegalArgumentException("Count must be positive");
        if (plantsCount.get() < Settings.MAX_PLANT_COUNT) {
            PlantFactory factory = new PlantFactory();
            for (int i = 0; i < count; i++) {
                entities.add(factory.producePlant());
                plantsCount.incrementAndGet();
            }
        }
    }

    public void randomPlantsGrow(int maxCount) {
        int count = MyRandom.getRandomInt(0, maxCount + 1);
        growPlants(count);
    }

    public int getAvailableCapacity() {
        return availableCapacity.get();
    }
}
