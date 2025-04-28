package simulation.utils;

import java.util.Map;

public class AnimalCharacteristics {
    private final int SIZE;                     // Размер животного, в условных единицах
    private final int WEIGHT;                   // Вес одного животного, кг
    private final int MAX_SPEED;                // Скорость перемещения, не более чем, клеток за ход
    private final int SATURATION_WEIGHT;        // Сколько килограммов пищи нужно животному для полного насыщения
    private final Map<String, Integer> FOOD;    // С какой вероятностью животное съедает "пищу"

    public AnimalCharacteristics(int size, int weight, int maxSpeed, int saturationWeight, Map<String, Integer> food) {
        if (size > Settings.LOCATION_CAPACITY) {
            throw new IllegalArgumentException("Too big size for this simulation: " + size);
        }
        this.SIZE = size;
        this.WEIGHT = weight;
        this.MAX_SPEED = maxSpeed;
        this.SATURATION_WEIGHT = saturationWeight;
        this.FOOD = food;
    }

    public int getSize() {
        return SIZE;
    }

    public int getWeight() {
        return WEIGHT;
    }

    public int getMaxSpeed() {
        return MAX_SPEED;
    }

    public int getSaturationWeight() {
        return SATURATION_WEIGHT;
    }

    public Map<String, Integer> getFood() {
        return FOOD;
    }

}
