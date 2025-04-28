package simulation.factories;

import simulation.entities.Plant;
import simulation.utils.Settings;

public class PlantFactory {
    public Plant producePlant() {
        Plant plant = new Plant(Settings.PLANT_SIZE, Settings.PLANT_WEIGHT);
        return plant;
    }
}
