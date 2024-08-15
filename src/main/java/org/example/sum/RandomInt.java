package org.example.sum;

import java.util.Random;

public class RandomInt implements Number {
    private Random random;

    public RandomInt() {
        random = new Random();
    }

    @Override
    public int get() {
        return random.nextInt();
    }
}
