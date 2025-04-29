package simulation;

import simulation.island.Island;
import simulation.utils.Settings;
import simulation.utils.Statistics;

public class Simulation {
    private Island island;
    private Statistics statistics = Statistics.getInstance();

    public Simulation(Island island) {
        this.island = island;
    }

    private void startSimulation() {
        Island island = new Island(Settings.ISLAND_WIDTH, Settings.ISLAND_HEIGHT,
                Settings.START_ANIMAL_FULLNESS, Settings.START_PLANT_FULLNESS);

        
    }
}
