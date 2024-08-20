package org.example.sum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.example.constants.Constants.MAX_VALUE;
import static org.example.constants.Constants.MIN_VALUE;
import static org.junit.jupiter.api.Assertions.*;

class SumCalculatorTest {

    private SumCalculator sumCalculator;

    @BeforeEach
    void setup() {
        sumCalculator = new SumCalculator();
    }

    @Test
    void testSumOne() {
        int n = MIN_VALUE;
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

    @Test
    void testLargestNumber(){
        int n = MAX_VALUE;
        int expectedSum = 2_147_450_880;
        assertEquals(expectedSum, sumCalculator.sum(n));
    }
}