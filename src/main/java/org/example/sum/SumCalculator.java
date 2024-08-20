package org.example.sum;

public class SumCalculator {
    public int sum(int n) {
        long number = n;
        if (number > 0) {
            return (int)(number * (number + 1) / 2);
        } else {
            throw new IllegalArgumentException("Invalid input");
        }
    }
}
