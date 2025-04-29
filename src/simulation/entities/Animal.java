package simulation.entities;

import simulation.exceptions.AnimalEatingException;
import simulation.utils.AnimalCharacteristics;

import java.util.Map;


public abstract class Animal extends Entity {
    private final AnimalCharacteristics characteristics;
    private int saturation;

    public Animal(AnimalCharacteristics characteristics, int saturation) {
        this.characteristics = characteristics;
        this.saturation = saturation;
    }

    public int getSize() {
        return characteristics.getSize();
    }

    public int getWeight() {
        return characteristics.getWeight();
    }

    public boolean isFull() {
        return saturation >= characteristics.getSaturationWeight();
    }

    public Map<String, Integer> getFood() {
        return characteristics.getFood();
    }

    public int getMaxSpeed() {
        return characteristics.getMaxSpeed();
    }

    public void eat(Entity other) {
        if (!other.isAlive()) {
            throw new AnimalEatingException("Can not eat death entity");
        }
        saturation += other.getWeight();
        other.kill();
    }

    public void decrementSaturation() {
        saturation--;
    }
}
