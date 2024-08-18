package org.example.sum;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class RandomIntTest {

    @Test
    void testRandomNumberInRangeDefault() {
        RandomInt randomInt = new RandomInt();
        int result = randomInt.get();
        assertTrue(result >= 1 && result < 100,
                "The returned value should be within the range 1 and 100 (inclusive)");
    }

    @Test
    void testRandomNumbersInCustomRange() {
        RandomInt randomInt = new RandomInt(50);
        int result = randomInt.get();
        assertTrue(result >= 1 && result <= 50,
                "The returned value should always be within the range 1 and 50 (inclusive)");
    }

    @Test
    void testMaxEqualsOne() {
        RandomInt randomInt = new RandomInt(1);
        int result = randomInt.get();
        assertEquals(1, result, "The returned calue should always be 1 when max is 1");
    }

    @Test
    void testMockedRandomOutput() {
        Random mockRandom = Mockito.mock(Random.class);
        when(mockRandom.nextInt(100)).thenReturn(42);

        RandomInt randomIntWithMock = new RandomInt(mockRandom, 100);
        int result = randomIntWithMock.get();
        assertEquals(43, result,
                "The get() method should return 43 when the mock Random returns 42");
    }

    @Test
    void testMultipleCallsWithMock() {
        Random mockRandom = Mockito.mock(Random.class);
        when(mockRandom.nextInt(100)).thenReturn(0, 99);

        RandomInt randomInt = new RandomInt(mockRandom, 100);

        assertEquals(1, randomInt.get(), "The first call should return 1");
        assertEquals(100, randomInt.get(), "The second call should return 100");
    }

    @Test
    void testMaxLessThanOneThrowsException() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new RandomInt(0),
                "Expected constructor to throw IllegalArgumentException when max is less than 1"
        );

        assertEquals("max must be greater than or equal to 1", thrown.getMessage());
    }

    @Test
    public void testGet_ExceptionThrown() {
        Random mockRandom = Mockito.mock(Random.class);
        when(mockRandom.nextInt(anyInt())).thenThrow(new RuntimeException("Mocked Exception"));
        RandomInt randomInt = new RandomInt(mockRandom, 10);
        int result = randomInt.get();

        assertEquals(0, result, "Expected RuntimeException when failed generating random number");
    }
}