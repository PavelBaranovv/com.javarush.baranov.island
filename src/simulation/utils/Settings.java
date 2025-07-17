package simulation.utils;

import java.util.HashMap;
import java.util.Map;

public class Settings {
    public static final int DAY_DURATION = 100;                 // Длительность одного дня (в миллисекундах)
    public static final int PRINTING_INTERVAL = 5;              // Печатать статистику каждые n дней

    public static final int ISLAND_WIDTH = 10;                 // Ширина острова
    public static final int ISLAND_HEIGHT = 15;                 // Высота острова

    public static final int LOCATION_ANIMALS_CAPACITY = 5000;    // Вместимость одной локации для животных
    public static final int LOCATION_PLANTS_CAPACITY = 1000;      // Вместимость одной локации для растений

    public static final int MIN_START_SATURATION = 75;          // Мин насыщение животного при рождении (0% - 100%)
    public static final int START_ANIMAL_FULLNESS = 20;         // Заполненность животными в начале симуляции (0% - 100%)
    public static final int START_PLANT_FULLNESS = 100;          // Заполненность растениями в начале симуляции (0% - 100%)

    public static final int PREDATORS_REPRODUCE_CHANCE = 10;     // Шанс хищника размножится (при наличии партнера)
    public static final int HERBIVOROUS_REPRODUCE_CHANCE = 40;   // Шанс травоядного размножится (при наличии партнера)

    public static final int MOVE_CHANCE = 40;                    // Шанс животного перейти в другую локацию
    public static final int MOVE_TIRED = 4;                      // Насколько животное устает за одно перемещение
    public static final int REPRODUCE_SATURATION = 90;           // Насколько сытым должно быть животное для размножения (0% - 100%)

    public static final int MIN_DAY_GROWING_COUNT = 30;           // Минимальный прирост растений в одной локации за 1 день
    public static final int MAX_DAY_GROWING_COUNT = 50;           // Максимальный прирост растений в одной локации за 1 день

    public static final int DAILY_HUNGER_AMOUNT = 1;              // Сколько единиц сытости животное теряет за день
    public static final int DAILY_HUNGER_CHANCE = 40;             // и с какой вероятностью

    // ========== УСЛОВИЯ ЗАВЕРШЕНИЯ СИМУЛЯЦИИ: =========
    public static final int MIN_ANIMALS_COUNT = 10;          // Если осталось слишком мало животных
    public static final int MIN_HERBIVOROUS_COUNT = 10;      // Если осталось слишком мало травоядных
    public static final int MIN_PREDATORS_COUNT = 10;        // Если осталось слишком мало хищников
    public static final int MAX_SIMULATION_DURATION = 1000;  // Если симуляция продлилась не менее чем n дней
    public static final int MAX_EXTINCT_ANIMALS = 5;         // Если исчезло не менее n видов животных

    // ========== ХАРАКТЕРИСТИКИ СУЩНОСТЕЙ: =========
    public static final int PLANT_SIZE = 1;
    public static final int PLANT_WEIGHT = 1;

    private static final Map<String, Integer> WOLF_FOOD = new HashMap<>() {{
        put("Horse", 10);
        put("Deer", 15);
        put("Rabbit", 60);
        put("Mouse", 15);
        put("Goat", 60);
        put("Sheep", 70);
        put("Boar", 50);
        put("Buffalo", 30);
        put("Duck", 15);
    }};

    private static final Map<String, Integer> BOA_FOOD = new HashMap<>() {{
        put("Fox", 15);
        put("Rabbit", 20);
        put("Mouse", 30);
        put("Duck", 10);
        put("Horse", 50);
    }};

    private static final Map<String, Integer> FOX_FOOD = new HashMap<>() {{
        put("Boar", 75);
        put("Rabbit", 50);
        put("Mouse", 40);
        put("Duck", 60);
        put("Caterpillar", 40);
    }};

    private static final Map<String, Integer> BEAR_FOOD = new HashMap<>() {{
        put("Boa", 80);
        put("Horse", 70);
        put("Deer", 80);
        put("Rabbit", 80);
        put("Mouse", 50);
        put("Goat", 70);
        put("Sheep", 70);
        put("Boar", 50);
        put("Buffalo", 20);
        put("Duck", 10);
    }};

    private static final Map<String, Integer> EAGLE_FOOD = new HashMap<>() {{
        put("Fox", 10);
        put("Rabbit", 90);
        put("Mouse", 50);
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
        put("Caterpillar", 80);
        put("Plant", 80);
    }};

    private static final Map<String, Integer> GOAT_FOOD = new HashMap<>() {{
        put("Plant", 100);
    }};

    private static final Map<String, Integer> SHEEP_FOOD = new HashMap<>() {{
        put("Plant", 100);
    }};

    private static final Map<String, Integer> BOAR_FOOD = new HashMap<>() {{
        put("Mouse", 20);
        put("Caterpillar", 50);
        put("Plant", 70);
    }};

    private static final Map<String, Integer> BUFFALO_FOOD = new HashMap<>() {{
        put("Plant", 100);
    }};

    private static final Map<String, Integer> DUCK_FOOD = new HashMap<>() {{
        put("Caterpillar", 100);
        put("Plant", 100);
    }};

    private static final Map<String, Integer> CATERPILLAR_FOOD = new HashMap<>() {{
        put("Plant", 100);
    }};

    public static final Map<String, AnimalCharacteristics> ANIMAL_CHARACTERISTICS = new HashMap<>() {{
        put("Wolf", new AnimalCharacteristics(10, 30, 3, 16, WOLF_FOOD, "\uD83D\uDC3A"));
        put("Boa", new AnimalCharacteristics(2, 40, 1, 10, BOA_FOOD, "\uD83D\uDC0D"));
        put("Fox", new AnimalCharacteristics(4, 30, 2, 2, FOX_FOOD, "\uD83E\uDD8A"));
        put("Bear", new AnimalCharacteristics(40, 5, 2, 70, BEAR_FOOD, "\uD83D\uDC3B"));
        put("Eagle", new AnimalCharacteristics(2, 20, 3, 10, EAGLE_FOOD, "\uD83E\uDD85"));
        put("Horse", new AnimalCharacteristics(20, 20, 4, 50, HORSE_FOOD, "\uD83D\uDC34"));
        put("Deer", new AnimalCharacteristics(30, 20, 4, 25, DEER_FOOD, "\uD83E\uDECE"));
        put("Rabbit", new AnimalCharacteristics(5, 20, 2, 3, RABBIT_FOOD, "\uD83D\uDC30"));
        put("Mouse", new AnimalCharacteristics(1, 15, 1, 3, MOUSE_FOOD, "\uD83D\uDC01"));
        put("Goat", new AnimalCharacteristics(20, 60, 3, 30, GOAT_FOOD, "\uD83D\uDC10"));
        put("Sheep", new AnimalCharacteristics(20, 60, 3, 15, SHEEP_FOOD, "\uD83D\uDC0F"));
        put("Boar", new AnimalCharacteristics(30, 50, 2, 20, BOAR_FOOD, "\uD83D\uDC17"));
        put("Buffalo", new AnimalCharacteristics(45, 10, 3, 20, BUFFALO_FOOD, "\uD83E\uDDAC"));
        put("Duck", new AnimalCharacteristics(4, 5, 4, 2, DUCK_FOOD, "\uD83E\uDD86"));
        put("Caterpillar", new AnimalCharacteristics(1, 2, 1, 2, CATERPILLAR_FOOD, "\uD83D\uDC1B"));
    }};
}
