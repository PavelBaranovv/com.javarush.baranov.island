package simulation.utils.statistics;

import simulation.entities.Animal;
import simulation.entities.Entity;
import simulation.entities.Plant;
import simulation.entities.animals.Herbivorous;
import simulation.entities.animals.Predator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Statistics {
    private static Statistics statistics;

    private final AtomicInteger herbivoresCount = new AtomicInteger();
    private final AtomicInteger predatorsCount = new AtomicInteger();
    private final AtomicInteger plantsCount = new AtomicInteger();
    private final Map<String, Integer> animalsCounts = new ConcurrentHashMap<>();

    private final AtomicInteger plantEats = new AtomicInteger();
    private final AtomicInteger animalEats = new AtomicInteger();
    private final AtomicInteger animalBirths = new AtomicInteger();
    private final AtomicInteger animalMoves = new AtomicInteger();
    private final AtomicInteger animalHungryDeaths = new AtomicInteger();


    private Statistics() {            // Singleton
    }


    public static synchronized Statistics getInstance() {
        if (statistics == null) {
            statistics = new Statistics();
        }
        return statistics;
    }

    public void addAnimal(Animal animal) {
        String emoji = animal.getEmoji();
        animalsCounts.merge(emoji, 1, Integer::sum);
        if (animal instanceof Herbivorous) {
            herbivoresCount.incrementAndGet();
        } else if (animal instanceof Predator) {
            predatorsCount.incrementAndGet();
        }
    }

    public void removeAnimal(Animal animal) {
        String emoji = animal.getEmoji();
        animalsCounts.merge(emoji, -1, Integer::sum);
        if (animal instanceof Herbivorous) {
            herbivoresCount.decrementAndGet();
        } else if (animal instanceof Predator) {
            predatorsCount.decrementAndGet();
        }
    }

    public void addPlant() {
        plantsCount.incrementAndGet();
    }

    public void addEating(Entity victim) {
        if (victim instanceof Animal) {
            animalEats.incrementAndGet();
            removeAnimal((Animal) victim);
        } else if (victim instanceof Plant) {
            plantEats.incrementAndGet();
            plantsCount.decrementAndGet();
        }
    }

    public void addBirth(Animal animal) {
        animalBirths.incrementAndGet();
        addAnimal(animal);
    }

    public void addMove() {
        animalMoves.incrementAndGet();
    }

    public void addHungryDeath(Animal animal) {
        animalHungryDeaths.incrementAndGet();
        removeAnimal(animal);
    }

    public int getAnimalsCount() {
        return herbivoresCount.get() + predatorsCount.get();
    }

    public int getHerbivoresCount() {
        return herbivoresCount.get();
    }

    public int getPredatorsCount() {
        return predatorsCount.get();
    }

    public int getPlantsCount() {
        return plantsCount.get();
    }

    public Map<String, Integer> getAnimalsCounts() {
        return animalsCounts;
    }

    public int getPlantEats() {
        return plantEats.get();
    }

    public int getAnimalEats() {
        return animalEats.get();
    }

    public int getAnimalBirths() {
        return animalBirths.get();
    }

    public int getAnimalMoves() {
        return animalMoves.get();
    }

    public int getAnimalHungryDeaths() {
        return animalHungryDeaths.get();
    }

    public Map<String, Integer> getExtinctAnimals() {
        return animalsCounts.entrySet()
                .stream()
                .filter(e -> e.getValue() <= 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
