package org.example.sum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.example.constants.Constants.MAX_VALUE;
import static org.example.constants.Constants.MIN_VALUE;
import static org.junit.jupiter.api.Assertions.*;

class UserInputTest {
    private UserInput userInput;

    @BeforeEach
    public void setUp() {
        userInput = new UserInput();
    }

    @Test
    void testValidInput() {
        String simulatedInput = "7\n";
        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(testIn);

        int result = userInput.get();
        assertEquals(7, result, "The method should return the valid positive integer input");
    }

    @Test
    void testInvalidThenValidInput() {
        String simulatedInput = "meow\n-11\n0\n7\n";
        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(testIn);

        int result = userInput.get();
        assertEquals(7, result, "The method should skip invalid inputs and continue asking for input until a valid positive integer is provided");
    }

    @Test
    void testMinimumValidInput() {
        String simulatedInput = String.valueOf(MIN_VALUE);
        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(testIn);

        int result = userInput.get();
        assertEquals(1, result, "The method should accept the smallest positive integer input (1)");

    }

    @Test
    void testMultipleValidInput() {
        String simulatedInput = "3\n4\n5\n";
        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(testIn);

        int result = userInput.get();
        assertEquals(3, result, "The method should return the first valid positive integer input");
    }

    @Test
    void testLargeNumberInput(){
        String simulatedInput = String.valueOf(MAX_VALUE);
        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(testIn);

        int result = userInput.get();
        assertEquals(MAX_VALUE, result, "The method should return the large valid positive integer input");
    }

    @Test
    void testZeroAsInvalidInput(){
        String simulatedInput = "0\n5\n";
        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(testIn);

        int result = userInput.get();
        assertEquals(5, result, "The method should reject zero as an invalid input and continue asking for input until a valid positive integer is provided");
    }

    @Test
    void testNegativeThenPositiveInput(){
        String simulatedInput = "-5\n8\n";
        ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(testIn);

        int result = userInput.get();
        assertEquals(8, result, "The method should skip negative inputs and continue asking for input until a valid positive integer is provided");
    }
}