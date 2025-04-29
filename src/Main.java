import simulation.Island;
import simulation.utils.Settings;
import simulation.utils.Statistics;

public class Main {
    public static void main(String[] args) {
        Island island = new Island(Settings.ISLAND_WIDTH, Settings.ISLAND_HEIGHT,
                Settings.START_ANIMAL_FULLNESS, Settings.START_PLANT_FULLNESS);
        island.growPlantsRandom(20);

        Statistics.getInstance().printStatistics();

        island.eatingTick();

        System.out.println();
        Statistics.getInstance().printStatistics();

        island.reproducingTick();

        System.out.println();
        Statistics.getInstance().printStatistics();

    }
}
