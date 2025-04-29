package simulation.utils;

import simulation.entities.Animal;
import simulation.entities.Entity;
import simulation.entities.Plant;
import java.util.concurrent.atomic.AtomicInteger;

public class Statistics {
    private static Statistics statistics;

    private final AtomicInteger animalsCount = new AtomicInteger();
    private final AtomicInteger plantsCount = new AtomicInteger();
    private final AtomicInteger plantEatings = new AtomicInteger();
    private final AtomicInteger animalEatings = new AtomicInteger();
    private final AtomicInteger animalBirths = new AtomicInteger();


    private Statistics() {                             // Singleton
    }

    public static Statistics getInstance() {
        if (statistics == null) {
            statistics = new Statistics();
        }
        return statistics;
    }

    public void addAnimalsCount() {
        animalsCount.incrementAndGet();
    }

    public void addPlantsCount() {
       plantsCount.incrementAndGet();
    }

    public void printStatistics() {
        System.out.println("Animal count: " + animalsCount);
        System.out.println("Plant count: " + plantsCount);
        System.out.println("Plant eatings: " + plantEatings.get());
        System.out.println("Animal eatings: " + animalEatings.get());
        System.out.println("Animal births: " + animalBirths.get());

    }

    public void addEating(Entity victim) {
        if (victim instanceof Animal) {
            animalEatings.incrementAndGet();
            animalsCount.decrementAndGet();
        } else if (victim instanceof Plant) {
            plantEatings.incrementAndGet();
            plantsCount.decrementAndGet();
        }
    }

    public void addBirth() {
        animalBirths.incrementAndGet();
        addAnimalsCount();
    }
}
