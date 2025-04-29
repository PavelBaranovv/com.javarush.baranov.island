import simulation.island.Island;
import simulation.utils.Renderer;
import simulation.utils.Settings;
import simulation.utils.Statistics;

public class Main {
    public static void main(String[] args) {
        Island island = new Island(Settings.ISLAND_WIDTH, Settings.ISLAND_HEIGHT,
                Settings.START_ANIMAL_FULLNESS, Settings.START_PLANT_FULLNESS);
        island.growPlantsRandom(20);

        Renderer renderer = new Renderer();
        renderer.printFullStatistics(1, Statistics.getInstance());

        island.eatingTick();

        System.out.println();
        renderer.printFullStatistics(1, Statistics.getInstance());


        island.reproducingTick();

        System.out.println();
        renderer.printFullStatistics(1, Statistics.getInstance());


        island.moveTick();

        System.out.println();
        renderer.printFullStatistics(1, Statistics.getInstance());


    }
}
