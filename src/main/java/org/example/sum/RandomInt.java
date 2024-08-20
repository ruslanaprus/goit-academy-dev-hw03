package org.example.sum;

import org.example.number.NumberValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

import static org.example.constants.Constants.MAX_VALUE;

public class RandomInt implements NumberGetter {
    private static final Logger logger = LoggerFactory.getLogger(RandomInt.class);
    private final NumberValidator numberValidator;
    private final Random random;
    private final int max;

    public RandomInt() {
        this(MAX_VALUE);
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
        this.numberValidator = new NumberValidator();
    }

    @Override
    public int get() {
        try {
            int randomNumber;
            do{
                randomNumber = random.nextInt(max) + 1;
            } while (!numberValidator.isValidNumber(randomNumber));
             return randomNumber;
        } catch (Exception e) {
            logger.error("Error generating random number", e);
            return 0;
        }
    }
}
