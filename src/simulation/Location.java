package simulation;

import simulation.entities.Animal;
import simulation.entities.Entity;
import simulation.exceptions.EntityPlaceException;
import simulation.utils.AnimalFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Location {
    private AtomicInteger availableCapacity = new AtomicInteger();
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

    public int getAvailableCapacity() {
        return availableCapacity.get();
    }
}
