package simulation.utils;

import simulation.entities.Animal;
import simulation.entities.animals.herbivores.*;
import simulation.entities.animals.predators.*;
import simulation.exceptions.EntityProductionException;

import java.util.Map;
import java.util.Objects;

public class AnimalFactory {
    public Animal produceAnimal(String animalType) {
        AnimalCharacteristics characteristics = Settings.ANIMAL_CHARACTERISTICS.get(animalType);
        return switch (animalType.toLowerCase()) {
            case "wolf" -> new Wolf(characteristics);
            case "fox" -> new Fox(characteristics);
            case "bear" -> new Bear(characteristics);
            case "eagle" -> new Eagle(characteristics);
            case "horse" -> new Horse(characteristics);
            case "deer" -> new Deer(characteristics);
            case "rabbit" -> new Rabbit(characteristics);
            case "mouse" -> new Mouse(characteristics);
            case "goat" -> new Goat(characteristics);
            case "sheep" -> new Sheep(characteristics);
            case "boar" -> new Boar(characteristics);
            case "buffalo" -> new Buffalo(characteristics);
            case "duck" -> new Duck(characteristics);
            case "caterpillar" -> new Caterpillar(characteristics);
            case "boa" -> new Boa(characteristics);
            default -> throw new EntityProductionException("Unknown animal type: " + animalType);
        };
    }

    public Animal produceRandomAnimal(int size) {
        Map.Entry<String, AnimalCharacteristics> randomAnimal = MyRandom.chooseRandomElement(
                Settings.ANIMAL_CHARACTERISTICS.entrySet().stream()
                        .filter((entry) -> entry.getValue().getSize() <= size)
                        .toList()
        );
        return randomAnimal == null ? null : produceAnimal(randomAnimal.getKey());
    }
}