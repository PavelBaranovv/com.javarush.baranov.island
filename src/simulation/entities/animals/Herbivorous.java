package simulation.entities.animals;

import simulation.entities.Animal;
import simulation.utils.AnimalCharacteristics;

public abstract class Herbivorous extends Animal {
    public Herbivorous(AnimalCharacteristics characteristics, int saturation) {
        super(characteristics, saturation);
    }
}
