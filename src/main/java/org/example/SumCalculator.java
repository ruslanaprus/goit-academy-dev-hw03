package org.example;

public class SumCalculator {
    public int sum(int n) {
        if (n > 0) {
            return n * (n + 1) / 2;
        } else {
            throw new IllegalArgumentException("Invalid input");
        }
    }
}
