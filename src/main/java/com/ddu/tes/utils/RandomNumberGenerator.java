package com.ddu.tes.utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomNumberGenerator {

    private static long generatorLastNum = 0;

    public static String generateNumber(int length) {

        long generatorLimit = new Double(Math.pow(10, Math.floor((length - 1) / 2))).longValue();
        int randomMin = new Double(Math.pow(10, length / 2)).intValue();
        int randomMax = new Double(Math.pow(10, length / 2 + 1)).intValue();

        long id = System.currentTimeMillis() % generatorLimit;
        if (id <= generatorLastNum) {
            id = (generatorLastNum + 1) % generatorLimit;
        }
        long time = (generatorLastNum = id);

        int randomInt = ThreadLocalRandom.current().nextInt(randomMin, randomMax);

        String random = String.valueOf(randomInt) + String.valueOf(time);

        while (random.length() < length) {
            random += ThreadLocalRandom.current().nextInt(0, 10);
        }

        return random;
    }
}
