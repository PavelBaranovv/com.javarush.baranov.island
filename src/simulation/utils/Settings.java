package simulation.utils;

import java.util.HashMap;
import java.util.Map;

public class Settings {
    public static final int ISLAND_WIDTH = 5;
    public static final int ISLAND_HEIGHT = 5;
    public static final int LOCATION_CAPACITY = 1000;

    private static final Map<String, Integer> wolfFood = new HashMap<>() {{
        put("Horse", 10);
        put("Deer", 15);
        put("Rabbit", 60);
        put("Mouse", 80);
        put("Goat", 60);
        put("Sheep", 70);
        put("Boar", 15);
        put("Buffalo", 10);
        put("Duck", 40);
    }};

    private static final Map<String, Integer> boaFood = new HashMap<>() {{
        put("Fox", 15);
        put("Rabbit", 20);
        put("Mouse", 40);
        put("Duck", 10);
    }};

    private static final Map<String, Integer> foxFood = new HashMap<>() {{
        put("Rabbit", 70);
        put("Mouse", 90);
        put("Duck", 60);
        put("Caterpillar", 40);
    }};

    private static final Map<String, Integer> bearFood = new HashMap<>() {{
        put("Wolf", 80);
        put("Horse", 40);
        put("Deer", 80);
        put("Rabbit", 80);
        put("Mouse", 90);
        put("Goat", 70);
        put("Sheep", 70);
        put("Boar", 50);
        put("Buffalo", 20);
        put("Duck", 10);
    }};

    private static final Map<String, Integer> eagleFood = new HashMap<>() {{
        put("Fox", 10);
        put("Rabbit", 90);
        put("Mouse", 90);
        put("Duck", 80);
    }};

    private static final Map<String, Integer> horseFood = new HashMap<>() {{
        put("Plant", 100);
    }};

    private static final Map<String, Integer> deerFood = new HashMap<>() {{
        put("Plant", 100);
    }};

    private static final Map<String, Integer> rabbitFood = new HashMap<>() {{
        put("Plant", 100);
    }};

    private static final Map<String, Integer> mouseFood = new HashMap<>() {{
        put("Caterpillar", 90);
        put("Plant", 100);
    }};

    private static final Map<String, Integer> goatFood = new HashMap<>() {{
        put("Plant", 100);
    }};

    private static final Map<String, Integer> sheepFood = new HashMap<>() {{
        put("Plant", 100);
    }};

    private static final Map<String, Integer> boarFood = new HashMap<>() {{
        put("Mouse", 50);
        put("Caterpillar", 90);
        put("Plant", 100);
    }};

    private static final Map<String, Integer> buffaloFood = new HashMap<>() {{
        put("Plant", 100);
    }};

    private static final Map<String, Integer> duckFood = new HashMap<>() {{
        put("Caterpillar", 90);
        put("Plant", 100);
    }};

    private static final Map<String, Integer> caterpillarFood = new HashMap<>() {{
        put("Plant", 100);
    }};

    public static final Map<String, AnimalCharacteristics> animalSizes = new HashMap<>() {{
        put("Wolf", new AnimalCharacteristics(50, 30, 3, 8, wolfFood));
        put("Boa", new AnimalCharacteristics(15, 30, 1, 3, boaFood));
        put("Fox", new AnimalCharacteristics(10, 30, 2, 2, foxFood));
        put("Bear", new AnimalCharacteristics(100, 5, 2, 80, bearFood));
        put("Eagle", new AnimalCharacteristics(6, 20, 3, 1, eagleFood));
        put("Horse", new AnimalCharacteristics(90, 20, 4, 60, horseFood));
        put("Deer", new AnimalCharacteristics(90, 20, 4, 50, deerFood));
        put("Rabbit", new AnimalCharacteristics(8, 150, 2, 1, rabbitFood));
        put("Mouse", new AnimalCharacteristics(2, 500, 1, 1, mouseFood));
        put("Goat", new AnimalCharacteristics(60, 140, 3, 10, goatFood));
        put("Sheep", new AnimalCharacteristics(70, 140, 3, 15, sheepFood));
        put("Boar", new AnimalCharacteristics(90, 50, 2, 50, boarFood));
        put("Buffalo", new AnimalCharacteristics(100, 10, 3, 100, buffaloFood));
        put("Duck", new AnimalCharacteristics(3, 200, 4, 1, duckFood));
        put("Caterpillar", new AnimalCharacteristics(1, 1000, 0, 0, caterpillarFood));
        put("Plants", new AnimalCharacteristics(1, 200, 0, 0, new HashMap<>()));
    }};
}
