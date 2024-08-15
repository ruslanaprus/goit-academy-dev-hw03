package org.example.sum;

import java.util.Random;

public class RandomInt implements NumberGetter {
    private final Random random;
    int min = 1;
    int max = 100;

    public RandomInt() {
        random = new Random();
    }

    @Override
    public int get() {
        return random.nextInt(max - min) + min;
    }
}
