package simulation.utils;

import simulation.utils.position.Vector;

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

    public static Vector getRandomVector(int maxLength) {
        if (maxLength < 0) {
            throw new IllegalArgumentException("Max length can't be negative");
        }

        double angle = random.nextDouble() * 2 * Math.PI;
        double length = Math.max(1, random.nextDouble() * maxLength);

        int dx = (int) Math.round(length * Math.cos(angle));
        int dy = (int) Math.round(length * Math.sin(angle));

        return new Vector(dx, dy);
    }
}
