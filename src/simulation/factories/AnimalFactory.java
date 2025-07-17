package simulation.factories;

import simulation.entities.Animal;
import simulation.entities.animals.herbivores.*;
import simulation.entities.animals.predators.*;
import simulation.exceptions.EntityProductionException;
import simulation.utils.AnimalCharacteristics;
import simulation.utils.MyRandom;
import simulation.utils.Settings;

import java.util.Map;

public class AnimalFactory {
    public Animal produceAnimal(String animalType) {
        if (animalType == null || animalType.isEmpty()) {
            throw new EntityProductionException("Invalid animal type");
        }

        AnimalCharacteristics characteristics = Settings.ANIMAL_CHARACTERISTICS.get(animalType);

        int minSaturation = characteristics.getSaturationWeight() * Settings.MIN_START_SATURATION / 100;
        int saturation = MyRandom.getRandomInt(minSaturation, characteristics.getSaturationWeight() + 1);

        return switch (animalType.toLowerCase()) {
            case "wolf" -> new Wolf(characteristics, saturation);
            case "fox" -> new Fox(characteristics, saturation);
            case "bear" -> new Bear(characteristics, saturation);
            case "eagle" -> new Eagle(characteristics, saturation);
            case "horse" -> new Horse(characteristics, saturation);
            case "deer" -> new Deer(characteristics, saturation);
            case "rabbit" -> new Rabbit(characteristics, saturation);
            case "mouse" -> new Mouse(characteristics, saturation);
            case "goat" -> new Goat(characteristics, saturation);
            case "sheep" -> new Sheep(characteristics, saturation);
            case "boar" -> new Boar(characteristics, saturation);
            case "buffalo" -> new Buffalo(characteristics, saturation);
            case "duck" -> new Duck(characteristics, saturation);
            case "caterpillar" -> new Caterpillar(characteristics, saturation);
            case "boa" -> new Boa(characteristics, saturation);
            default -> throw new EntityProductionException("Unknown animal type: " + animalType);
        };
    }

    public Animal produceRandomAnimal(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }
        Map.Entry<String, AnimalCharacteristics> randomAnimal = MyRandom.chooseRandomElement(
                Settings.ANIMAL_CHARACTERISTICS.entrySet().stream()
                        .filter((entry) -> entry.getValue().getSize() <= size)
                        .toList()
        );
        return randomAnimal == null ? null : produceAnimal(randomAnimal.getKey());
    }
}