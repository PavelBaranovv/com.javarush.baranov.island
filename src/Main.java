import simulation.Simulation;
import simulation.island.Island;
import simulation.utils.Settings;

public class Main {
    public static void main(String[] args) {
        Island island = new Island(Settings.ISLAND_WIDTH, Settings.ISLAND_HEIGHT,
                Settings.START_ANIMAL_FULLNESS, Settings.START_PLANT_FULLNESS);
        Simulation simulation = new Simulation(island);
        simulation.start();
    }
}
