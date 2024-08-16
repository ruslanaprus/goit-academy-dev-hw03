package org.example.sum;

import java.util.Random;

public class RandomInt implements NumberGetter {
    private final Random random;
    private final int max;
    private static final int DEFAULT_MAX = 100;

    public RandomInt() {
        this(DEFAULT_MAX);
    }

    public RandomInt(int max){
        this(new Random(), max);
    }

    public RandomInt(Random random, int max) {
        if (max < 1) {
            throw new IllegalArgumentException("max must be greater than or equal to 1");
        }
        this.random = random;
        this.max = max;
    }

    @Override
    public int get() {
        return random.nextInt(max) + 1;
    }
}
