package simulation.utils;

import java.util.HashMap;
import java.util.Map;

public class Settings {
    public static final int ISLAND_WIDTH = 5;                  // Ширина острова
    public static final int ISLAND_HEIGHT = 5;                 // Высота острова

    public static final int LOCATION_ANIMALS_CAPACITY = 1000;   // Вместимость животных на одной локации
    public static final int LOCATION_PLANTS_CAPACITY = 1000;    // Вместимость растений на одной локации

    public static final int MIN_START_SATURATION = 50;         // Мин. насыщение животного при рождении (0% - 100%)
    public static final int START_ANIMAL_FULLNESS = 50;        // Заполненность животными в начале симуляции (0% - 100%)
    public static final int START_PLANT_FULLNESS = 80;         // Заполненность растениями в начале симуляции (0% - 100%)

    public static final int REPRODUCE_CHANCE = 90;             // Шанс животного размножится (при наличии партнера)
    public static final int MOVE_CHANCE = 50;                  // Шанс животного перейти в другую локацию

    public static final int MAX_DAY_GROWING_COUNT = 5;          // Максимальный прирост растений в одной локации за 1 день

    // Ниже заданы основные характеристики всех сущностей

    public static final int PLANT_SIZE = 1;
    public static final int PLANT_WEIGHT = 2;

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
        put("Wolf", new AnimalCharacteristics(50, 30, 3, 8, WOLF_FOOD, "\uD83D\uDC3A"));
        put("Boa", new AnimalCharacteristics(15, 30, 1, 3, BOA_FOOD, "\uD83D\uDC0D"));
        put("Fox", new AnimalCharacteristics(10, 30, 2, 2, FOX_FOOD, "\uD83E\uDD8A"));
        put("Bear", new AnimalCharacteristics(100, 5, 2, 80, BEAR_FOOD, "\uD83D\uDC3B"));
        put("Eagle", new AnimalCharacteristics(6, 20, 3, 1, EAGLE_FOOD, "\uD83E\uDD85"));
        put("Horse", new AnimalCharacteristics(90, 20, 4, 60, HORSE_FOOD, "\uD83D\uDC34"));
        put("Deer", new AnimalCharacteristics(90, 20, 4, 50, DEER_FOOD, "\uD83E\uDECE"));
        put("Rabbit", new AnimalCharacteristics(8, 150, 2, 1, RABBIT_FOOD, "\uD83D\uDC30"));
        put("Mouse", new AnimalCharacteristics(2, 500, 1, 1, MOUSE_FOOD, "\uD83D\uDC01"));
        put("Goat", new AnimalCharacteristics(60, 140, 3, 10, GOAT_FOOD, "\uD83D\uDC10"));
        put("Sheep", new AnimalCharacteristics(70, 140, 3, 15, SHEEP_FOOD, "\uD83D\uDC0F"));
        put("Boar", new AnimalCharacteristics(90, 50, 2, 50, BOAR_FOOD, "\uD83D\uDC17"));
        put("Buffalo", new AnimalCharacteristics(100, 10, 3, 100, BUFFALO_FOOD, "\uD83E\uDDAC"));
        put("Duck", new AnimalCharacteristics(3, 5, 4, 1, DUCK_FOOD, "\uD83E\uDD86"));
        put("Caterpillar", new AnimalCharacteristics(1, 2, 0, 0, CATERPILLAR_FOOD, "\uD83D\uDC1B"));
    }};
}
