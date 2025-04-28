package simulation.utils;

import java.util.Map;

public class AnimalCharacteristics {
    private final int size;                    // Размер животного, в условных единицах
    private final int weight;                  // Вес одного животного, кг
    private final int maxSpeed;                // Скорость перемещения, не более чем, клеток за ход
    private final int saturationWeight;        // Сколько килограммов пищи нужно животному для полного насыщения
    private final Map<String, Integer> food;         // С какой вероятностью животное съедает "пищу"

    public AnimalCharacteristics(int size, int weight, int maxSpeed, int saturationWeight, Map<String, Integer> food) {
        if (size > Settings.LOCATION_CAPACITY) {
            throw new IllegalArgumentException("Too big size for this simulation: " + size);
        }
        this.size = size;
        this.weight = weight;
        this.maxSpeed = maxSpeed;
        this.saturationWeight = saturationWeight;
        this.food = food;
    }

    public int getSize() {
        return size;
    }

    public int getWeight() {
        return weight;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getSaturationWeight() {
        return saturationWeight;
    }

    public Map<String, Integer> getFood() {
        return food;
    }

}
