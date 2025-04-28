package simulation.entities;

import simulation.utils.AnimalCharacteristics;

import java.util.Map;

public abstract class Animal extends Entity {
    private final AnimalCharacteristics characteristics;

    public Animal(AnimalCharacteristics characteristics) {
        this.characteristics = characteristics;
    }

    public int getSize() {
        return characteristics.getSize();
    }
}
