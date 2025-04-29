package simulation.utils;

import simulation.entities.Animal;
import simulation.entities.Entity;
import simulation.entities.Plant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Statistics {
    private static Statistics statistics;

    private final AtomicInteger animalsCount = new AtomicInteger();
    private final AtomicInteger plantsCount = new AtomicInteger();
    private final Map<String, Integer> animalsCounts = new ConcurrentHashMap<>();

    private final AtomicInteger plantEats = new AtomicInteger();
    private final AtomicInteger animalEats = new AtomicInteger();
    private final AtomicInteger animalBirths = new AtomicInteger();
    private final AtomicInteger animalMoves = new AtomicInteger();


    private Statistics() {                             // Singleton
    }

    public static Statistics getInstance() {
        if (statistics == null) {
            statistics = new Statistics();
        }
        return statistics;
    }

    public void addAnimalsCount(Animal animal) {
        String emoji = animal.getEmoji();
        Integer count = animalsCounts.get(emoji);
        animalsCounts.put(emoji, count == null ? 1 : ++count);
        animalsCount.incrementAndGet();
    }

    public void addPlantsCount() {
       plantsCount.incrementAndGet();
    }

    public void addEating(Entity victim) {
        if (victim instanceof Animal) {
            animalEats.incrementAndGet();
            animalsCount.decrementAndGet();
            String emoji = victim.getEmoji();
            animalsCounts.put(emoji, animalsCounts.get(emoji) - 1);
        } else if (victim instanceof Plant) {
            plantEats.incrementAndGet();
            plantsCount.decrementAndGet();
        }
    }

    public void addBirth(Animal animal) {
        animalBirths.incrementAndGet();
        addAnimalsCount(animal);
    }

    public void addMoves() {
        animalMoves.incrementAndGet();
    }

    public int getAnimalsCount() {
        return animalsCount.get();
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
}
