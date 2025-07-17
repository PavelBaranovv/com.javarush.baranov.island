package simulation.island;

import simulation.entities.*;
import simulation.entities.animals.Herbivorous;
import simulation.entities.animals.Predator;
import simulation.exceptions.EntityPlaceException;
import simulation.factories.*;
import simulation.utils.*;
import simulation.utils.statistics.Statistics;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Location {
    private final AtomicInteger availableAnimalCapacity = new AtomicInteger();
    private final AtomicInteger availablePlantCapacity = new AtomicInteger();
    private final List<Entity> entities;

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
            if (newAnimal == null) break;
            entities.add(newAnimal);
            needToFill -= newAnimal.getSize();
            availableAnimalCapacity.addAndGet(-newAnimal.getSize());
            Statistics.getInstance().addAnimal(newAnimal);
        }
    }

    public void fillPlants(int fullness) {
        if (fullness < 0) throw new IllegalArgumentException("Fullness must be positive");
        int count = availablePlantCapacity.get() * fullness / 100;
        growPlants(count);
    }

    public void randomPlantsGrow(int minCount, int maxCount) {
        if (minCount > maxCount || minCount < 0) {
            throw new IllegalArgumentException("Illegal bounds: " + minCount + ", " + maxCount);
        }
        int count = MyRandom.getRandomInt(minCount, maxCount + 1);
        growPlants(count);
    }

    public void eatingTick() {
        for (Entity e : entities) {
            if (e instanceof Animal animal) {
                Map<String, Integer> animalFood = animal.getFood();
                List<Entity> potentialFood = entities.stream().filter((entity) -> entity.isAlive() && animalFood.containsKey(entity.getClass().getSimpleName())).collect(Collectors.toList());

                while (!animal.isFull() && !potentialFood.isEmpty()) {
                    Entity victim = MyRandom.chooseRandomElement(potentialFood);
                    int probability = animalFood.get(victim.getClass().getSimpleName());
                    if (MyRandom.eventExecution(probability) && victim.isAlive()) {
                        animal.eat(victim);
                        potentialFood.remove(victim);
                        Statistics.getInstance().addEating(victim);
                    }
                }
            }
        }
    }

    public void reproducingTick() {
        Map<String, Long> readyToReproduce = entities.stream().filter(e -> e instanceof Animal && e.isAlive() && ((Animal) e).isReadyToReproduce()).map(e -> e.getClass().getSimpleName()).collect(Collectors.groupingBy((x) -> x, Collectors.counting()));
        AnimalFactory factory = new AnimalFactory();
        for (Map.Entry<String, Long> entry : readyToReproduce.entrySet()) {
            if (entry.getValue() >= 2) {
                for (int i = 0; i < entry.getValue() / 2; i++) {
                    Animal animal = factory.produceAnimal(entry.getKey());
                    if (availableAnimalCapacity.get() < animal.getSize()) {
                        break;
                    }
                    if (animal instanceof Predator && MyRandom.eventExecution(Settings.PREDATORS_REPRODUCE_CHANCE) || animal instanceof Herbivorous && MyRandom.eventExecution(Settings.HERBIVOROUS_REPRODUCE_CHANCE)) {
                        addAnimal(animal);
                        Statistics.getInstance().addBirth(animal);
                    }
                }
            }
        }
    }

    public void addAnimal(Animal animal) {
        if (animal.getSize() > availableAnimalCapacity.get()) {
            throw new EntityPlaceException("Too big animal for this location");
        }
        entities.add(animal);
        availableAnimalCapacity.addAndGet(-animal.getSize());
    }

    public List<Animal> getAnimalsReadyToMove() {
        return entities.stream().filter(e -> e instanceof Animal && e.isAlive() && MyRandom.eventExecution(Settings.MOVE_CHANCE)).map(e -> (Animal) e).filter(Animal::isReadyToMove).collect(Collectors.toList());
    }

    public synchronized boolean tryAddAnimal(Animal animal) {
        if (availableAnimalCapacity.get() >= animal.getSize()) {
            entities.add(animal);
            availableAnimalCapacity.addAndGet(-animal.getSize());
            return true;
        }
        return false;
    }

    public synchronized void removeAnimal(Animal animal) {
        if (entities.remove(animal)) {
            availableAnimalCapacity.addAndGet(animal.getSize());
        } else {
            throw new IllegalArgumentException("Cannot remove animal " + animal.getClass().getSimpleName());
        }
    }

    public void hungerTick() {
        for (Entity entity : entities) {
            if (entity instanceof Animal animal && entity.isAlive()) {
                if (MyRandom.eventExecution(Settings.DAILY_HUNGER_CHANCE)) {
                    animal.getTired(Settings.DAILY_HUNGER_AMOUNT);
                }
            }
        }
    }

    public void removeDeadEntities() {
        List<Entity> toRemove = new ArrayList<>();
        for (Entity e : entities) {
            if (!e.isAlive()) {
                toRemove.add(e);
                incrementCapacity(e);
            }
        }
        entities.removeAll(toRemove);
    }

    public int getAnimalFullness() {
        int usedCapacity = Settings.LOCATION_ANIMALS_CAPACITY - availableAnimalCapacity.get();
        return (usedCapacity * 100) / Settings.LOCATION_ANIMALS_CAPACITY;
    }

    public int getPlantFullness() {
        int usedCapacity = Settings.LOCATION_PLANTS_CAPACITY - availablePlantCapacity.get();
        return (usedCapacity * 100) / Settings.LOCATION_PLANTS_CAPACITY;
    }

    public Optional<String> getMostPopulatedAnimal() {
        Map<String, Long> animalCounts = entities.stream()
                .filter(e -> e instanceof Animal && e.isAlive())
                .map(e -> e.getClass().getSimpleName())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return animalCounts.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey);
    }

    private void growPlants(int count) {
        if (count < 0) throw new IllegalArgumentException("Count must be positive");
        PlantFactory factory = new PlantFactory();
        int realCount = Math.min(count, availablePlantCapacity.get() / Settings.PLANT_SIZE);
        for (int i = 0; i < realCount; i++) {
            Plant plant = factory.producePlant();
            entities.add(plant);
            availablePlantCapacity.addAndGet(-plant.getSize());
            Statistics.getInstance().addPlant();
        }
    }

    private void incrementCapacity(Entity e) {
        if (e instanceof Animal) {
            availableAnimalCapacity.addAndGet(e.getSize());
        } else if (e instanceof Plant) {
            availablePlantCapacity.addAndGet(e.getSize());
        }
    }
}
