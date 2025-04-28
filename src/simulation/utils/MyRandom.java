package simulation.utils;

import java.util.List;
import java.util.Map;
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
}
