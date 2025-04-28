package simulation.entities;

import simulation.utils.AnimalCharacteristics;


public abstract class Animal extends Entity {
    private final AnimalCharacteristics characteristics;

    public Animal(AnimalCharacteristics characteristics) {
        this.characteristics = characteristics;
    }

    public int getSize() {
        return characteristics.getSize();
    }

    public int getWeight() {
        return characteristics.getWeight();
    }
}
