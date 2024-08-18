package org.example.sum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class RandomInt implements NumberGetter {
    private static final Logger logger = LoggerFactory.getLogger(RandomInt.class);
    private final Random random;
    private final int max;
    private static final int DEFAULT_MAX = 100;

    public RandomInt() {
        this(DEFAULT_MAX);
    }

    public RandomInt(int max) {
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
        try {
            return random.nextInt(max) + 1;
        } catch (Exception e) {
            logger.error("Error generating random number", e);
            return 0;
        }
    }
}
