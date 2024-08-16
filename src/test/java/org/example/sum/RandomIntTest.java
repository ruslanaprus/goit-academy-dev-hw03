package org.example.sum;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RandomIntTest {

    @Test
    public void testRandomNumberInRangeDefault() {
        RandomInt randomInt = new RandomInt();
        int result = randomInt.get();
        assertTrue(result >= 1 && result < 100,
                "The returned value should be within the range 1 and 100 (inclusive)");
    }

    @Test
    public void testRandomNumbersInCustomRange() {
        RandomInt randomInt = new RandomInt(50);
        int result = randomInt.get();
        assertTrue(result >= 1 && result <= 50,
                "The returned value should always be within the range 1 and 50 (inclusive)");
    }

    @Test
    public void testMockedRandomOutput() {
        Random mockRandom = Mockito.mock(Random.class);
        Mockito.when(mockRandom.nextInt(100)).thenReturn(42);

        RandomInt randomIntWithMock = new RandomInt(mockRandom, 100);
        int result = randomIntWithMock.get();
        assertEquals(43, result,
                "The get() method should return 43 when the mock Random returns 42");
    }

    @Test
    public void testMaxLessThanOneThrowsException() {
        IllegalArgumentException thrown = org.junit.jupiter.api.Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new RandomInt(0),
                "Expected constructor to throw IllegalArgumentException when max is less than 1"
        );

        assertEquals("max must be greater than or equal to 1", thrown.getMessage());
    }

}