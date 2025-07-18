package simulation.entities;

import simulation.exceptions.AnimalEatingException;
import simulation.utils.AnimalCharacteristics;
import simulation.utils.Settings;
import simulation.utils.statistics.Statistics;

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

    @Override
    public String getEmoji() {
        return characteristics.getEmoji();
    }

    public void eat(Entity other) {
        if (other == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        if (!other.isAlive()) {
            throw new AnimalEatingException("Can not eat death entity");
        }
        saturation += other.getWeight();
        other.die();
    }

    public void getTired(int delta) {
        if (delta < 0) throw new IllegalArgumentException("Invalid delta saturation");
        this.saturation = Math.max(0, this.saturation - delta);
        if (saturation == 0) {
            this.die();
            Statistics.getInstance().addHungryDeath(this);
        }
    }

    public boolean isReadyToReproduce() {
        return saturation >= characteristics.getSaturationWeight() * Settings.REPRODUCE_SATURATION / 100;
    }

    public boolean isReadyToMove() {
        return isAlive() && saturation >= Settings.MOVE_TIRED;
    }
}
