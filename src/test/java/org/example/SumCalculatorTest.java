package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SumCalculatorTest {

    private SumCalculator sumCalculator;

    @BeforeEach
    void setup() {
        sumCalculator = new SumCalculator();
    }

    @Test
    void testSumOne() {
        int n = 1;
        int expectedSum = 1;
        assertEquals(expectedSum, sumCalculator.sum(n));
    }

    @Test
    void testSumThree() {
        int n = 3;
        int expectedSum = 6;
        assertEquals(expectedSum, sumCalculator.sum(n));
    }

    @Test
    void testSumException() {
        int n = 0;
        assertThrows(IllegalArgumentException.class, () -> {
            sumCalculator.sum(n);
        });

    }
}