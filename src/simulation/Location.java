package simulation;

import simulation.entities.*;
import simulation.factories.*;
import simulation.utils.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Location {
    private AtomicInteger availableAnimalCapacity = new AtomicInteger();
    private AtomicInteger availablePlantCapacity = new AtomicInteger();
    private List<Entity> entities;

    public Location(int animalCapacity, int plantCapacity) {
        this.availableAnimalCapacity.set(animalCapacity);
        this.availablePlantCapacity.set(plantCapacity);
        entities = new CopyOnWriteArrayList<>();
    }


    public void spawnRandomAnimals(int fullness) {
        if (fullness < 0 || fullness > 100) {
            throw new IllegalArgumentException("Fullness must be from 0 to zero");
        }
        int needToFill = availableAnimalCapacity.get() * fullness / 100;
        AnimalFactory factory = new AnimalFactory();
        while (needToFill > 0) {
            Animal newAnimal = factory.produceRandomAnimal(needToFill);
            entities.add(newAnimal);
            needToFill -= newAnimal.getSize();
            availableAnimalCapacity.addAndGet(-newAnimal.getSize());
            Statistics.getInstance().addAnimalsCount();

        }
    }

    public void growPlants(int count) {
        if (count < 0) throw new IllegalArgumentException("Count must be positive");
        PlantFactory factory = new PlantFactory();
        for (int i = 0; i < count; i++) {
            Plant plant = factory.producePlant();
            if (availablePlantCapacity.get() < plant.getSize()) {
                break;
            }
            entities.add(plant);
            availablePlantCapacity.addAndGet(-plant.getSize());
            Statistics.getInstance().addPlantsCount();
        }
    }

    public void randomPlantsGrow(int maxCount) {
        int count = MyRandom.getRandomInt(0, maxCount + 1);
        growPlants(count);
    }

    public void eatingTick() {
        for (Entity e : entities) {
            if (e instanceof Animal animal) {
                Map<String, Integer> animalFood = animal.getFood();
                List<Entity> potentialFood = entities.stream()
                        .filter((entity) -> entity.isAlive() && animalFood.containsKey(entity.getClass().getSimpleName()))
                        .collect(Collectors.toList());

                Iterator<Entity> iterator = potentialFood.iterator();
                while (!animal.isFull() && iterator.hasNext()) {
                    Entity victim = iterator.next();
                    int probability = animalFood.get(victim.getClass().getSimpleName());
                    if (MyRandom.eventExecution(probability)) {
                        animal.eat(victim);
                        iterator.remove();
                        if (victim instanceof Plant) availablePlantCapacity.addAndGet(victim.getWeight());
                        else availableAnimalCapacity.addAndGet(victim.getWeight());
                        Statistics.getInstance().addEating(victim);
                    }

                }
            }
        }
    }

    public void reproducingTick() {
        Map<String, Long> animalCounting = entities.stream()
                .filter(e -> e instanceof Animal && e.isAlive())
                .map(e -> e.getClass().getSimpleName())
                .collect(Collectors.groupingBy(
                        (x) -> x,
                        Collectors.counting()
                ));
        AnimalFactory factory = new AnimalFactory();
        for (Map.Entry<String, Long> entry : animalCounting.entrySet()) {
            if (entry.getValue() >= 2) {
                for (int i = 0; i < entry.getValue() / 2; i++) {
                    Animal animal = factory.produceAnimal(entry.getKey());
                    if (availableAnimalCapacity.get() < animal.getSize()) {
                        break;
                    }
                    entities.add(animal);
                    availableAnimalCapacity.addAndGet(-animal.getSize());
                    Statistics.getInstance().addBirth();
                }
            }
        }
    }
}
