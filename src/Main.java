import simulation.Island;
import simulation.utils.Statistics;

public class Main {
    public static void main(String[] args) {
        Island island = new Island(1, 1, 80, 80);
        island.growPlantsRandom(20);

        Statistics.getInstance().printStatistics();
        island.eatingTick();
        System.out.println();
        Statistics.getInstance().printStatistics();
    }
}
