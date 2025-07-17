package simulation.utils;

import simulation.island.Island;

import java.util.Map;

public class StatisticPrinter {
    public StatisticPrinter() {
    }

    public void printFullStatistics(Statistics statistics, Island island) {
        System.out.print("\uD83C\uDF31" + statistics.getPlantsCount());
        Map<String, Integer> animalsCounts = statistics.getAnimalsCounts();
        animalsCounts.forEach((emoji, count) -> System.out.print(" " + emoji + count));
        System.out.println();

        System.out.println("Animals count: " + statistics.getAnimalsCount());
        System.out.println("Herbivorous:   " + statistics.getHerbivoresCount());
        System.out.println("Predators:     " + statistics.getPredatorsCount());
        System.out.println("Plants count:  " + statistics.getPlantsCount());
        System.out.println("Plant eats:    " + statistics.getPlantEats());
        System.out.println("Animal eats:   " + statistics.getAnimalEats());
        System.out.println("Animal births: " + statistics.getAnimalBirths());
        System.out.println("Animal moves:  " + statistics.getAnimalMoves());
        System.out.println("Hungry deaths: " + statistics.getAnimalHungryDeaths());

        System.out.println("\nAverage plants fullness:  " + island.getAveragePlantFullness() +"%");
        System.out.println("Average animals fullness: " + island.getAverageAnimalFullness() + "%");
        System.out.println();
    }
}