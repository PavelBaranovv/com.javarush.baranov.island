package simulation.entities.animals;

import simulation.entities.Animal;
import simulation.utils.AnimalCharacteristics;

public abstract class Predator extends Animal {
    public Predator(AnimalCharacteristics characteristics, int saturation) {
        super(characteristics, saturation);
    }
}
