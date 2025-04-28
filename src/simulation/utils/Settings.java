package simulation.utils;

import java.util.HashMap;
import java.util.Map;

public class Settings {
    public static final int ISLAND_WIDTH = 5;
    public static final int ISLAND_HEIGHT = 5;
    public static final int LOCATION_CAPACITY = 1000;

    private static final Map<String, Integer> WOLF_FOOD = new HashMap<>() {{
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

    private static final Map<String, Integer> BOA_FOOD = new HashMap<>() {{
        put("Fox", 15);
        put("Rabbit", 20);
        put("Mouse", 40);
        put("Duck", 10);
    }};

    private static final Map<String, Integer> FOX_FOOD = new HashMap<>() {{
        put("Rabbit", 70);
        put("Mouse", 90);
        put("Duck", 60);
        put("Caterpillar", 40);
    }};

    private static final Map<String, Integer> BEAR_FOOD = new HashMap<>() {{
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

    private static final Map<String, Integer> EAGLE_FOOD = new HashMap<>() {{
        put("Fox", 10);
        put("Rabbit", 90);
        put("Mouse", 90);
        put("Duck", 80);
    }};

    private static final Map<String, Integer> HORSE_FOOD = new HashMap<>() {{
        put("Plant", 100);
    }};

    private static final Map<String, Integer> DEER_FOOD = new HashMap<>() {{
        put("Plant", 100);
    }};

    private static final Map<String, Integer> RABBIT_FOOD = new HashMap<>() {{
        put("Plant", 100);
    }};

    private static final Map<String, Integer> MOUSE_FOOD = new HashMap<>() {{
        put("Caterpillar", 90);
        put("Plant", 100);
    }};

    private static final Map<String, Integer> GOAT_FOOD = new HashMap<>() {{
        put("Plant", 100);
    }};

    private static final Map<String, Integer> SHEEP_FOOD = new HashMap<>() {{
        put("Plant", 100);
    }};

    private static final Map<String, Integer> BOAR_FOOD = new HashMap<>() {{
        put("Mouse", 50);
        put("Caterpillar", 90);
        put("Plant", 100);
    }};

    private static final Map<String, Integer> BUFFALO_FOOD = new HashMap<>() {{
        put("Plant", 100);
    }};

    private static final Map<String, Integer> DUCK_FOOD = new HashMap<>() {{
        put("Caterpillar", 90);
        put("Plant", 100);
    }};

    private static final Map<String, Integer> CATERPILLAR_FOOD = new HashMap<>() {{
        put("Plant", 100);
    }};

    public static final Map<String, AnimalCharacteristics> ANIMAL_CHARACTERISTICS = new HashMap<>() {{
        put("Wolf", new AnimalCharacteristics(50, 30, 3, 8, WOLF_FOOD));
        put("Boa", new AnimalCharacteristics(15, 30, 1, 3, BOA_FOOD));
        put("Fox", new AnimalCharacteristics(10, 30, 2, 2, FOX_FOOD));
        put("Bear", new AnimalCharacteristics(100, 5, 2, 80, BEAR_FOOD));
        put("Eagle", new AnimalCharacteristics(6, 20, 3, 1, EAGLE_FOOD));
        put("Horse", new AnimalCharacteristics(90, 20, 4, 60, HORSE_FOOD));
        put("Deer", new AnimalCharacteristics(90, 20, 4, 50, DEER_FOOD));
        put("Rabbit", new AnimalCharacteristics(8, 150, 2, 1, RABBIT_FOOD));
        put("Mouse", new AnimalCharacteristics(2, 500, 1, 1, MOUSE_FOOD));
        put("Goat", new AnimalCharacteristics(60, 140, 3, 10, GOAT_FOOD));
        put("Sheep", new AnimalCharacteristics(70, 140, 3, 15, SHEEP_FOOD));
        put("Boar", new AnimalCharacteristics(90, 50, 2, 50, BOAR_FOOD));
        put("Buffalo", new AnimalCharacteristics(100, 10, 3, 100, BUFFALO_FOOD));
        put("Duck", new AnimalCharacteristics(3, 200, 4, 1, DUCK_FOOD));
        put("Caterpillar", new AnimalCharacteristics(1, 1000, 0, 0, CATERPILLAR_FOOD));
    }};
}
