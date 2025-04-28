package simulation.utils;

import java.util.List;
import java.util.Random;

public class MyRandom {
    private static final Random random = new Random();

    public static <T> T chooseRandomElement(List<T> list) {
        if (list.isEmpty()) {
            return null;
        }
        int index = random.nextInt(list.size());
        return list.get(index);
    }

    public static int getRandomInt(int left, int right) {
        return random.nextInt(left, right);
    }

    public static boolean eventExecution(int probability) {
        if (probability > 100 || probability < 0) {
            throw new IllegalArgumentException("Probability must be from 0 to 100");
        }
        return random.nextInt(100) + 1 <= probability;
    }
}
