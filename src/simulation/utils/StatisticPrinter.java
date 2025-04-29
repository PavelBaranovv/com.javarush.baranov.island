package simulation.utils;

import java.util.Map;

public class StatisticPrinter {
    public StatisticPrinter() {
    }

    public void printFullStatistics(Statistics statistics) {
        System.out.print("\uD83C\uDF31" + statistics.getPlantsCount());
        Map<String, Integer> animalsCounts = statistics.getAnimalsCounts();
        animalsCounts.forEach((emoji, count) -> System.out.print(" " + emoji + count));
        System.out.println();

        System.out.println("Animal count:  " + statistics.getAnimalsCount());
        System.out.println("Plant count:   " + statistics.getPlantsCount());
        System.out.println("Plant eats:    " + statistics.getPlantEats());
        System.out.println("Animal eats:   " + statistics.getAnimalEats());
        System.out.println("Animal births: " + statistics.getAnimalBirths());
        System.out.println("Animal moves:  " + statistics.getAnimalMoves());
    }
}