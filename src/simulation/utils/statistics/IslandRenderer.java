package simulation.utils.statistics;

import simulation.island.Island;
import simulation.island.Location;
import simulation.utils.Settings;

public class IslandRenderer {
    Island island;

    public IslandRenderer(Island island) {
        if (island == null) {
            throw new IllegalArgumentException("island cannot be null");
        }
        this.island = island;
    }

    public void drawIsland() {
        Location[][] locations = island.getLocations();
        final int lineLength = 15 * locations[0].length;
        String line = "═".repeat(lineLength);
        System.out.println(line);

        for (Location[] row : locations) {
            for (Location location : row) {
                int plantFullness = location.getPlantFullness();
                int animalFullness = location.getAnimalFullness();
                String animalEmoji = location.getMostPopulatedAnimal()
                        .map(animalType -> Settings.ANIMAL_CHARACTERISTICS.get(animalType).getEmoji())
                        .orElse("Beat");
                System.out.printf("║\uD83C\uDF31%3d%% %s%3d%% ", plantFullness, animalEmoji, animalFullness);
            }
            System.out.println("║");
            System.out.println(line);
        }
    }
}

